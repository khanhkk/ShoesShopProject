package tdc.edu.vn.shoesshop.Toan;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

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
// show home shop fragment
        functions.onButton1Clicked();

        llContainer = (LinearLayout) findViewById(R.id.llParent);
        tabarControl = (TabarControl) findViewById(R.id.tcTabarShop);
        tabarControl.setTabarFunctions(functions);

        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra(LoginActivity.BUNDLE);
        if(bundle1!=null){
            Toast.makeText(getApplicationContext(),"key: "+ bundle1.getString("key"),Toast.LENGTH_LONG).show();
        }
    }
}
