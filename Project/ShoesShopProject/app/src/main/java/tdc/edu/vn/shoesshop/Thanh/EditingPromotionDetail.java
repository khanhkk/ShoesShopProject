package tdc.edu.vn.shoesshop.Thanh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import Controls.General;
import tdc.edu.vn.shoesshop.R;

public class EditingPromotionDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editing_promotion_detail_activity);
        General.setupUI(findViewById(R.id.promotion_detail), EditingPromotionDetail.this);
    }
}
