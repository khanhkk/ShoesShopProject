package tdc.edu.vn.shoesshop.Khanh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    //CircleImageView btnChange;

    String image = null;

    // Write data to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_promotions_activity);

        //back trang truoc
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //anh xa
        dtTimeEnd = (DateTimePicker)findViewById(R.id.dateEnd);
        dtTimeStart = (DateTimePicker)findViewById(R.id.dateStart);
        btnSave = (Button) findViewById(R.id.btnSave);
        etName = (EditText) findViewById(R.id.edtNameProgram);
        etContent = (EditText) findViewById(R.id.edtContent);
        btnChange = (ImageButton) findViewById(R.id.btnChangeImage);

        //btnChange = (CircleImageView) findViewById(R.id.btnChangeImage);

        General.setupUI(findViewById(R.id.llPromotionsLayout), EdittingPromotions.this);

        //nhan du lieu tu activity truoc
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
            //hien thi du lieu cua chuong trinh khuyen mai hien tai
            try {
                etName.setText(promotion.getTitle());
                dtTimeStart.setDate(DateTimePicker.simpleDateFormat.parse(promotion.getDateStart()));
                dtTimeEnd.setDate(DateTimePicker.simpleDateFormat.parse(promotion.getDateEnd()));
                etContent.setText(promotion.getContent());
                if(promotion.getImage() != null)
                {
                    try {
                        image = promotion.getImage();
                        Bitmap bitmap = General.decodeFromFirebaseBase64(promotion.getImage());
//                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//                        roundedBitmapDrawable.setCircular(true);
                        RoundedBitmapDrawable roundedBitmapDrawable = General.setCircleImage(bitmap);

                        Glide.with(EdittingPromotions.this).load(roundedBitmapDrawable).into(btnChange);
                        //btnChange.setImageDrawable(roundedBitmapDrawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (ParseException ex)
            {
                Toast.makeText(EdittingPromotions.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //catch erro
                    if (dtTimeStart.getDate() == null || dtTimeEnd.getDate() == null) {
                        Toast.makeText(EdittingPromotions.this, "Chưa chọn thời gian!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String s1 = DateTimePicker.simpleDateFormat.format(dtTimeStart.getDate());
                    String s2 = DateTimePicker.simpleDateFormat.format(dtTimeEnd.getDate());
                    try {
                        if (etName.getText().length() == 0 || DateTimePicker.simpleDateFormat.parse(s1).compareTo(DateTimePicker.simpleDateFormat.parse(s2)) >= 0 || dtTimeEnd.getDate().compareTo(Calendar.getInstance().getTime()) <= 0) {
                            Toast.makeText(EdittingPromotions.this, "Thông tin nhập chưa đúng!", Toast.LENGTH_SHORT).show();
                            return;
                        }




                    } catch (Exception ex) {
                    }
                    if (promotion != null) {
                        //sua thong tin cua chuong trinh khuyen mai
                        promotion.setTitle(etName.getText() + "");
                        if(DateTimePicker.simpleDateFormat.parse(promotion.getDateStart()).compareTo(Calendar.getInstance().getTime()) > 0)
                        {
                            promotion.setDateStart(s1);
                        }
                        if(DateTimePicker.simpleDateFormat.parse(promotion.getDateEnd()).compareTo(Calendar.getInstance().getTime()) > 0)
                        {
                            promotion.setDateEnd(s2);
                        }
                        //promotion.setDateStart(s1);
                        //promotion.setDateEnd(s2);
                        promotion.setContent(etContent.getText() + "");
                        promotion.setShop(user.getUid());
                        if (image != null) {
                            promotion.setImage(image);
                        }
                        myRef.child("Promotions").orderByChild("id").equalTo(promotion.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    child.getRef().setValue(promotion);
                                    break;
                                }
                                Toast.makeText(EdittingPromotions.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                intent.setClass(EdittingPromotions.this, Promotions.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    } else {
                        //them mot chuong trinh khuyen mai moi
                        try {
                            if (dtTimeStart.getDate().compareTo(Calendar.getInstance().getTime()) <= 0) {
                                Toast.makeText(EdittingPromotions.this, "Kiểm tra thời gian bắt đầu!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception ex) {
                            Toast.makeText(EdittingPromotions.this, "Lỗi thời gian!!!", Toast.LENGTH_SHORT).show();
                        }
                        promotion = new Promotion();
                        promotion.setTitle(etName.getText() + "");
                        promotion.setDateStart(s1);
                        promotion.setDateEnd(s2);
                        promotion.setContent(etContent.getText() + "");
                        promotion.setShop(user.getUid());
                        if (image != null) {
                            promotion.setImage(image);
                        }
                        String s = myRef.child("Promotions").push().getKey();
                        promotion.setId(s);
                        myRef.child("Promotions").push().setValue(promotion);

                        Toast.makeText(EdittingPromotions.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        intent.setClass(EdittingPromotions.this, Promotions.class);
                        startActivity(intent);
                    }
                }catch(Exception ex){ Toast.makeText(EdittingPromotions.this, "Lỗi!",Toast.LENGTH_SHORT).show();}
            }
        });

        //thay doi avatar của chuong trinh khuyen mai
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EdittingPromotions.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Bạn muốn lấy ảnh từ?");

                alertDialog.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        General.chooseFromCamera(EdittingPromotions.this);
                    }
                });

                alertDialog.setNegativeButton("Thư viện", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        General.chooseFromGallery(EdittingPromotions.this);
                    }
                });

                alertDialog.show();
            }
        });
    }

    //back trang truoc
//    @Override
//    public void onBackPressed() {
//        EdittingPromotions.super.onBackPressed();
//    }


    //nhan image tu camera/thu vien
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == General.REQUEST_IMAGE_CAPTURE && resultCode == EdittingPromotions.this.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            RoundedBitmapDrawable roundedBitmapDrawable = General.setCircleImage(imageBitmap);

            btnChange.setImageDrawable(roundedBitmapDrawable);
            image = General.encodeBitmap(imageBitmap);
        }
        else if (resultCode == EdittingPromotions.this.RESULT_OK && requestCode == General.CAM_REQUEST) {
            Uri picUri = data.getData();
            Bitmap bitmap;
            try {
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Chọn ảnh đại diện");
                Context applicationContext = dialog.getContext();
                bitmap = BitmapFactory.decodeStream(applicationContext.getContentResolver().openInputStream(picUri));

                RoundedBitmapDrawable roundedBitmapDrawable = General.setCircleImage(bitmap);

                btnChange.setImageDrawable(roundedBitmapDrawable);
                image = General.encodeBitmap(bitmap);
                dialog.dismiss();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

}
