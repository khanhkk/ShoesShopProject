package tdc.edu.vn.shoesshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import Controls.DateTimePicker;
import Models.Promotion;

public class EdittingPromotions extends AppCompatActivity {
    Button btnSave;
    ImageButton btnBack;
    DateTimePicker dtTimeStart;
    DateTimePicker dtTimeEnd;
    EditText etName, etContent;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_promotions_activity);
        Promotions.TakeData();

        dtTimeEnd = (DateTimePicker)findViewById(R.id.dateEnd);
        dtTimeStart = (DateTimePicker)findViewById(R.id.dateStart);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        etName = (EditText) findViewById(R.id.edtNameProgram);
        etContent = (EditText) findViewById(R.id.edtContent);

        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("member");
        String str = bundle.getString("data");
        Log.d("ma", str);
        Promotion promotion = null;
        for (int i = 0; i < Promotions.list.size() ; i++)
        {
            if(Promotions.list.get(i).getId() == Integer.parseInt(str))
            {
                promotion = Promotions.list.get(i);
                break;
            }
        }

        if(promotion != null)
        {
            etName.setText(promotion.getTitle());
            dtTimeStart.setDate(promotion.getDateStart());
            dtTimeEnd.setDate(promotion.getDateEnd());
            etContent.setText(promotion.getContent());
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = getIntent();
                intent.setClass(EdittingPromotions.this, Promotions.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
