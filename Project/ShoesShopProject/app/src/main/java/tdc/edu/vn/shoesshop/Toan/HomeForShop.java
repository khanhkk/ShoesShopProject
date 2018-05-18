package tdc.edu.vn.shoesshop.Toan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Controls.ServerConnectInternet;
import tdc.edu.vn.shoesshop.Bao.PersonalOfShopFragment;
import tdc.edu.vn.shoesshop.Khanh.HSActivity;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.TransactionOfShopFragment;
import tdc.edu.vn.shoesshop.Son.NotificationShopFragment;

public class HomeForShop extends AppCompatActivity {

    private FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
    private HSActivity hsActivity;
    private Home_Client_Fragment home_client_fragment;
    private TransactionOfShopFragment transaction;
    private NotificationShopFragment notificationFragment;
    private PersonalOfShopFragment personal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_shop_activity);
        check(this);
        //khoi tao fragment
        hsActivity = new HSActivity();
        home_client_fragment = new Home_Client_Fragment();
        transaction = new TransactionOfShopFragment();
        notificationFragment = new NotificationShopFragment();
        personal = new PersonalOfShopFragment();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (users == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    home_client_fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    hsActivity).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            if (users == null) {
                                //Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
                                selectedFragment = home_client_fragment;
                            } else {
                                //Toast.makeText(getApplicationContext(), "!null", Toast.LENGTH_SHORT).show();
                                selectedFragment = hsActivity;
                            }
                            break;
                        case R.id.nav_history:
                            selectedFragment = transaction;
                            break;

                        case R.id.nav_notification:
                            selectedFragment = new NotificationShopFragment();
                            break;

                        case R.id.nav_account:
                            selectedFragment = personal;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };

    public void check(HomeForShop view) {
        boolean ret = ServerConnectInternet.isConnected();
        String msg;
        if (ret == false) {
            msg = "Thiết bị chưa kết nối internet";
        } else {
            return;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
