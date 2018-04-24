package tdc.edu.vn.shoesshop.Khanh;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Calendar;

import Controls.DateTimePicker;
import Controls.General;
import Models.Promotion;
import tdc.edu.vn.shoesshop.R;

public class EdittingPromotions extends AppCompatActivity {


    Button btnSave;
    ImageButton btnBack, btnChange;
    DateTimePicker dtTimeStart;
    DateTimePicker dtTimeEnd;
    EditText etName, etContent;

    Intent intent;
    Bundle bundle = null;
    Promotion promotion = null;
    private Dialog dialog;

    String image = null;

    // Write data to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_promotions_activity);



        dtTimeEnd = (DateTimePicker)findViewById(R.id.dateEnd);
        dtTimeStart = (DateTimePicker)findViewById(R.id.dateStart);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        etName = (EditText) findViewById(R.id.edtNameProgram);
        etContent = (EditText) findViewById(R.id.edtContent);
        btnChange = (ImageButton) findViewById(R.id.btnChangeImage);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Choose Avatar Image");

        General.setupUI(findViewById(R.id.llPromotionsLayout), EdittingPromotions.this);

        intent = getIntent();
        bundle = intent.getBundleExtra("member");
        if(bundle != null)
        {
            String str = bundle.getString("data");
            Log.d("aaa", str);
            for (int i = 0; i < Promotions.listParent.size() ; i++)
            {
                if(Promotions.listParent.get(i).getId().equals(str))
                {

                    promotion = Promotions.listParent.get(i);
                    break;
                }
            }
        }
        if(promotion != null)
        {
            try {
                etName.setText(promotion.getTitle());
                dtTimeStart.setDate(DateTimePicker.simpleDateFormat.parse(promotion.getDateStart()));
                dtTimeEnd.setDate(DateTimePicker.simpleDateFormat.parse(promotion.getDateEnd()));
                etContent.setText(promotion.getContent());
            }
            catch (ParseException ex)
            {
                Toast.makeText(EdittingPromotions.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = getIntent();
                intent.setClass(EdittingPromotions.this, Promotions.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dtTimeStart.getDate() == null || dtTimeEnd.getDate() == null) {
                    Toast.makeText(EdittingPromotions.this, "Chưa chọn thời gian!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s1 = DateTimePicker.simpleDateFormat.format(dtTimeStart.getDate());
                String s2 = DateTimePicker.simpleDateFormat.format(dtTimeEnd.getDate());
                try {
                    if(etName.getText().length() == 0 || dtTimeStart.getDate().compareTo(Calendar.getInstance().getTime()) <= 0 || DateTimePicker.simpleDateFormat.parse(s1).compareTo(DateTimePicker.simpleDateFormat.parse(s2)) >= 0 ) {
                        Toast.makeText(EdittingPromotions.this, "Thông tin nhập chưa đúng!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                catch (Exception ex){}
                if(promotion != null)
                {
                    promotion.setTitle(etName.getText()+"");
                    promotion.setDateStart(DateTimePicker.simpleDateFormat.format(dtTimeStart.getDate()));
                    promotion.setDateEnd(DateTimePicker.simpleDateFormat.format(dtTimeEnd.getDate()));
                    promotion.setContent(etContent.getText() + "");
                    promotion.setShop(user.getUid());
                    if(image != null)
                    {
                        promotion.setImage(image);
                    }
                    myRef.child("Promotions").orderByChild("id").equalTo(promotion.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child: dataSnapshot.getChildren()) {
                                child.getRef().setValue(promotion);
                            }
                            Toast.makeText(EdittingPromotions.this, "Sua thanh cong!", Toast.LENGTH_SHORT).show();
                            intent.setClass(EdittingPromotions.this, Promotions.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                else
                {
                    promotion = new Promotion();
                    promotion.setTitle(etName.getText()+"");
                    promotion.setDateStart(DateTimePicker.simpleDateFormat.format(dtTimeStart.getDate()));
                    promotion.setDateEnd(DateTimePicker.simpleDateFormat.format(dtTimeEnd.getDate()));
                    promotion.setContent(etContent.getText() + "");
                    promotion.setShop(user.getUid());
                    if(image != null)
                    {
                        promotion.setImage(image);
                    }
                    String s = myRef.child("Promotions").push().getKey();
                    promotion.setId(s);
                    myRef.child("Promotions").push().setValue(promotion);

                    Toast.makeText(EdittingPromotions.this, "Them thanh cong!", Toast.LENGTH_SHORT).show();
                    intent.setClass(EdittingPromotions.this, Promotions.class);
                    startActivity(intent);

                }
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                General.chooseFromGallery(EdittingPromotions.this);
                //General.chooseFromCamera(EdittingPromotions.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == General.REQUEST_IMAGE_CAPTURE && resultCode == EdittingPromotions.this.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            btnChange.setImageBitmap(imageBitmap);
            image = General.encodeBitmap(imageBitmap);
        }

        if (resultCode == EdittingPromotions.this.RESULT_OK && requestCode == General.CAM_REQUEST) {
            Uri picUri = data.getData();
            Bitmap bitmap;
            try {
                Context applicationContext = dialog.getContext();
                bitmap = BitmapFactory.decodeStream(applicationContext.getContentResolver().openInputStream(picUri));
                btnChange.setImageBitmap(bitmap);
                image = General.encodeBitmap(bitmap);
                dialog.dismiss();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
