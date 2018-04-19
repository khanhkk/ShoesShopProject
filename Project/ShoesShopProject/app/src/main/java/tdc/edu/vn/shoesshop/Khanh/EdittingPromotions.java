package tdc.edu.vn.shoesshop.Khanh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.Calendar;

import Controls.DateTimePicker;
import Controls.General;
import Models.Promotion;
import tdc.edu.vn.shoesshop.R;

public class EdittingPromotions extends AppCompatActivity {
    Button btnSave;
    ImageButton btnBack;
    DateTimePicker dtTimeStart;
    DateTimePicker dtTimeEnd;
    EditText etName, etContent;
    Intent intent;
    Bundle bundle = null;
    Promotion promotion = null;
    //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_promotions_activity);

        // Write data to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        dtTimeEnd = (DateTimePicker)findViewById(R.id.dateEnd);
        dtTimeStart = (DateTimePicker)findViewById(R.id.dateStart);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        etName = (EditText) findViewById(R.id.edtNameProgram);
        etContent = (EditText) findViewById(R.id.edtContent);

        General.setupUI(findViewById(R.id.llPromotionsLayout), EdittingPromotions.this);

        intent = getIntent();
        bundle = intent.getBundleExtra("member");
        if(bundle != null)
        {
            String str = bundle.getString("data");
            for (int i = 0; i < Promotions.listParent.size() ; i++)
            {
                if(Promotions.listParent.get(i).getId() == Integer.parseInt(str))
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
                    myRef.child("Promotions").child("id").equalTo(promotion.getId());

                    intent.setClass(EdittingPromotions.this, Promotions.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else
                {
                    promotion = new Promotion();
                    promotion.setTitle(etName.getText()+"");
                    promotion.setDateStart(DateTimePicker.simpleDateFormat.format(dtTimeStart.getDate()));
                    promotion.setDateEnd(DateTimePicker.simpleDateFormat.format(dtTimeEnd.getDate()));
                    promotion.setContent(etContent.getText() + "");
                    if(Promotions.listParent.size()>0) {
                        promotion.setId(Promotions.listParent.get(Promotions.listParent.size() - 1).getId() + 1);
                    }
                    else
                    {
                        promotion.setId(0);
                    }

                    myRef.child("Promotions").push().setValue(promotion);
                    intent.setClass(EdittingPromotions.this, Promotions.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
