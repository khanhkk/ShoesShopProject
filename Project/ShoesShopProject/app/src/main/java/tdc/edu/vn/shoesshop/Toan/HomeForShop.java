package tdc.edu.vn.shoesshop.Toan;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import Controls.TabarControl;
import tdc.edu.vn.shoesshop.Bao.PersonalOfShopFragment;
import tdc.edu.vn.shoesshop.Khanh.SelectionProductToEditting;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.TransactionOfShopFragment;
import tdc.edu.vn.shoesshop.Son.NotificationFragment;

public class HomeForShop extends AppCompatActivity {

    private FragmentTransaction fragment;
    TabarControl tabarControl;

    NotificationFragment notify = null;
    PersonalOfShopFragment shopFragment = null;
    TransactionOfShopFragment transaction = null;

    TabarControl.TabarFunctions functions = new TabarControl.TabarFunctions() {
        @Override
        public void onButton1Clicked() {
            Intent intent = new Intent(HomeForShop.this, SelectionProductToEditting.class);
            startActivity(intent);
        }

        @Override
        public void onButton2Clicked() {
            fragment = getFragmentManager().beginTransaction();
            transaction = new TransactionOfShopFragment();
            fragment.replace(R.id.llParentShop, transaction);
            fragment.commit();
        }

        @Override
        public void onButton3Clicked() {
            fragment = getFragmentManager().beginTransaction();
            notify = new NotificationFragment();
            fragment.replace(R.id.llParentShop, notify);
            fragment.commit();
        }

        @Override
        public void onButton4Clicked() {
            fragment = getFragmentManager().beginTransaction();
            shopFragment = new PersonalOfShopFragment();
            fragment.replace(R.id.llParentShop, shopFragment);
            fragment.commit();
        }
    };
    LinearLayout llContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_shop_activity);
        // show home shop fragment
        //functions.onButton1Clicked();

        llContainer = (LinearLayout) findViewById(R.id.llParentShop);
        tabarControl = (TabarControl) findViewById(R.id.tcTabarShop);
        tabarControl.setTabarFunctions(functions);

        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra(LoginActivity.BUNDLE);
        if(bundle1!=null){
            Toast.makeText(getApplicationContext(),"key: "+ bundle1.getString("key"),Toast.LENGTH_LONG).show();
        }
    }
}
