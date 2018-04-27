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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Controls.General;
import Controls.TabarControl;
import tdc.edu.vn.shoesshop.Bao.PersonalOfClientLoginedFragment;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.NotificationFragment;

public class HomeForClient extends AppCompatActivity {
    /*private FragmentTransaction fragment = null;
    //private Fragment currentFragment;
    TabarControl tabarControl;

    NotificationFragment notify = null;
    Cart cart = null;
    Home_Client_Fragment b = null;
    PersonalOfClientLoginedFragment clientLoginedFragment = null; */


    private FirebaseAuth.AuthStateListener authListener;


    //private FirebaseAuth auth;

//TabarControl
   /* TabarControl.TabarFunctions functions = new TabarControl.TabarFunctions() {
        @Override
        public void onButton1Clicked() {
            fragment = getFragmentManager().beginTransaction();
            b = new Home_Client_Fragment();
            fragment.replace(R.id.llParent, b);
            fragment.commit();
        }

        @Override
        public void onButton2Clicked() {
            fragment = getFragmentManager().beginTransaction();
            notify = new NotificationFragment();
            fragment.replace(R.id.llParent, notify);
            fragment.commit();
        }

        @Override
        public void onButton3Clicked() {
            fragment = getFragmentManager().beginTransaction();
            cart = new Cart();
            fragment.replace(R.id.llParent, cart);
            fragment.commit();
        }


        @Override
        public void onButton4Clicked() {
            fragment = getFragmentManager().beginTransaction();
            clientLoginedFragment = new PersonalOfClientLoginedFragment();
            fragment.replace(R.id.llParent, clientLoginedFragment);
            fragment.commit();
        }
    };

//    main
    LinearLayout llContainer; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_client_activity);


//        get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeLayout()).commit();
        }



        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(HomeForClient.this, LoginActivity.class));
                    finish();
                }
            }
        };



     /*  llContainer = (LinearLayout) findViewById(R.id.llParent);
        tabarControl = (TabarControl) findViewById(R.id.tcTabarClient);

        tabarControl.setImageButton1(R.drawable.home);
        tabarControl.setImageButton2(R.drawable.notification);
        tabarControl.setImageButton3(R.drawable.cart);
        tabarControl.setImageButton4(R.drawable.account);
        tabarControl.setTabarFunctions(functions);
        //        default fragment
        functions.onButton1Clicked();
*/
//        data

        //Intent intent = getIntent();

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
                        case R.id.nav_notification:
                            selectedFragment = new NotificationFragment();
                            break;
                        case R.id.nav_cart:
                            selectedFragment = new Cart();
                            Intent intent1 = getIntent();
                            Bundle bundle1 = intent1.getBundleExtra(LoginActivity.BUNDLE);
                            if(bundle1!=null){
                                Toast.makeText(getApplicationContext(),"key: "+ bundle1.getString("key"),Toast.LENGTH_LONG).show();
                            }
                            Bundle bundle = intent1.getBundleExtra("chuyen");
                            if (bundle != null) {
                                if (bundle.getInt("chuyen") == 1) {
                                }
                            }
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
