package tdc.edu.vn.shoesshop.Toan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import tdc.edu.vn.shoesshop.Bao.PersonalOfShopFragment;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.TransactionOfShopFragment;
import tdc.edu.vn.shoesshop.Son.NotificationFragment;

public class HomeForShop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_shop_activity);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeLayout()).commit();
        }
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
