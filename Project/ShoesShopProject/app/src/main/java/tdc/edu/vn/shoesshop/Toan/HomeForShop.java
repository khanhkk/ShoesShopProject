package tdc.edu.vn.shoesshop.Toan;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import Controls.General;
import Controls.TabarControl;
import tdc.edu.vn.shoesshop.R;

public class HomeForShop extends AppCompatActivity {

    private FragmentTransaction fragment;
    TabarControl tabarControl;
    TabarControl.TabarFunctions functions = new TabarControl.TabarFunctions() {
        @Override
        public void onButton1Clicked() {

        }

        @Override
        public void onButton2Clicked() {

        }

        @Override
        public void onButton3Clicked() {
        }

        @Override
        public void onButton4Clicked() {

        }
    };
    LinearLayout llContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_shop_activity);

        llContainer = (LinearLayout) findViewById(R.id.llParent);
        tabarControl = (TabarControl) findViewById(R.id.tcTabarShop);

        tabarControl.setImageButton1(General.loadSampleResource(this, R.mipmap.product, 80, 80));
        tabarControl.setImageButton2(General.loadSampleResource(this, R.mipmap.history_icon, 80, 80));
        tabarControl.setImageButton3(General.loadSampleResource(this, R.mipmap.notify, 80, 80));
        tabarControl.setImageButton4(General.loadSampleResource(this, R.mipmap.shop, 80, 80));
        tabarControl.setTabarFunctions(functions);
    }
}
