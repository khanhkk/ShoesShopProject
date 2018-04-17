package tdc.edu.vn.shoesshop.Thanh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Controls.General;
import tdc.edu.vn.shoesshop.Khanh.Promotions;
import tdc.edu.vn.shoesshop.Khanh.SelectionProductToEditting;
import tdc.edu.vn.shoesshop.R;

public class EditingPromotionDetail extends AppCompatActivity {

    Button btnSave;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editing_promotion_detail_activity);

        General.setupUI(findViewById(R.id.promotion_detail), EditingPromotionDetail.this);
        btnSave = (Button) findViewById(R.id.btnSavePromotionsDetail);

        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle != null)
        {

        }



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(EditingPromotionDetail.this, Promotions.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }
}
