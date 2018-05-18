package tdc.edu.vn.shoesshop.Toan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tdc.edu.vn.shoesshop.Bao.PersonalOfClientLoginedFragment;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.NotificationClientFragment;

public class HomeForClient extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    //private FirebaseAuth auth;
    FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_client_activity);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        intent = getIntent();

        String s = intent.getStringExtra("action");
        if (s != null) {
            bottomNav.setSelectedItemId(R.id.nav_cart);
        } else {

            //get current user
            if (users == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home_Client_Fragment()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home_User_Fragment()).commit();
            }
        }

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //FirebaseUser user = firebaseAuth.getCurrentUser();
                if (users == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(HomeForClient.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            if (users == null) {
                                selectedFragment = new Home_Client_Fragment();
                            } else {
                                selectedFragment = new Home_User_Fragment();
                            }
                            break;
                        case R.id.nav_notification:
                            selectedFragment = new NotificationClientFragment();
                            //Info_product.setType(2);
                            break;
                        case R.id.nav_cart:
                            selectedFragment = new Cart();
                            break;
                        case R.id.nav_account:
                            selectedFragment = new PersonalOfClientLoginedFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();


                    return true;
                }
            };
}
