package tdc.edu.vn.shoesshop.Toan;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import Controls.General;
import Controls.TabarControl;
import tdc.edu.vn.shoesshop.Bao.PersonalOfClientLoginedFragment;
import tdc.edu.vn.shoesshop.Bao.PersonalOfShopFragment;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.TransactionOfShopFragment;
import tdc.edu.vn.shoesshop.Son.NotificationFragment;

public class HomeForShop extends AppCompatActivity {

   /* private FragmentTransaction fragment;
    TabarControl tabarControl;

    NotificationFragment notify = null;
    PersonalOfShopFragment shopFragment = null;
    TransactionOfShopFragment transaction = null;

    TabarControl.TabarFunctions functions = new TabarControl.TabarFunctions() {
        @Override
        public void onButton1Clicked() {

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
    LinearLayout llContainer; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_shop_activity);
        // show home shop fragment
       /* functions.onButton1Clicked();

        llContainer = (LinearLayout) findViewById(R.id.llParent);
        tabarControl = (TabarControl) findViewById(R.id.tcTabarClient);

        tabarControl.setImageButton1(R.drawable.home);
        tabarControl.setImageButton2(R.drawable.icon_history);
        tabarControl.setImageButton3(R.drawable.cart);
        tabarControl.setImageButton4(R.drawable.account);
        tabarControl.setTabarFunctions(functions); */

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeLayout()).commit();
        }

       /* Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra(LoginActivity.BUNDLE);
        if(bundle1!=null){
            Toast.makeText(getApplicationContext(),"key: "+ bundle1.getString("key"),Toast.LENGTH_LONG).show();
        }*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeLayout();
                            break;
                        case R.id.nav_history:
                            selectedFragment = new TransactionOfShopFragment();
                            break;

                        case R.id.nav_notification:
                            selectedFragment = new NotificationFragment();
                            break;

                        case R.id.nav_account:
                            selectedFragment = new PersonalOfShopFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;

                }
            };


}
