package tdc.edu.vn.shoesshop;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import Controls.TabarControl;

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
        tabarControl.setImageButton1(R.mipmap.product);
        tabarControl.setImageButton2(R.mipmap.history_icon);
        tabarControl.setImageButton3(R.mipmap.notify);
        tabarControl.setImageButton4(R.mipmap.shop);
        tabarControl.setTabarFunctions(functions);
    }
}
