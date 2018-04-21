package tdc.edu.vn.shoesshop.Toan;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import Controls.General;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Controls.TabarControl;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;

public class HomeForClient extends AppCompatActivity {
    private FragmentTransaction fragment;
    private Fragment currentFragment;
    Cart cart = null;
    Home_Client_Fragment b = null;
    TabarControl tabarControl;
    private FirebaseAuth.AuthStateListener authListener;
    //private FirebaseAuth auth;

//TabarControl
    TabarControl.TabarFunctions functions = new TabarControl.TabarFunctions() {
        @Override
        public void onButton1Clicked() {
            fragment = getFragmentManager().beginTransaction();
            b = new Home_Client_Fragment();
            fragment.replace(R.id.llParent, b);
            fragment.commit();
        }

        @Override
        public void onButton2Clicked() {

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

        }
    };

//    main
    LinearLayout llContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_client_activity);
//        default fragment
        functions.onButton1Clicked();

//        get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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

        llContainer = (LinearLayout) findViewById(R.id.llParent);
        tabarControl = (TabarControl) findViewById(R.id.tcTabarClient);
        tabarControl.setImageButton1(General.loadSampleResource(this, R.mipmap.home, 80, 80));
        tabarControl.setImageButton2(General.loadSampleResource(this, R.mipmap.notify, 80, 80));
        tabarControl.setImageButton3(General.loadSampleResource(this, R.mipmap.cart, 80, 80));
        tabarControl.setImageButton4(General.loadSampleResource(this, R.mipmap.personal, 80, 80));
        tabarControl.setTabarFunctions(functions);
//        data

        Intent intent = getIntent();
        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra(LoginActivity.BUNDLE);
        if(bundle1!=null){
            Toast.makeText(getApplicationContext(),"key: "+ bundle1.getString("key"),Toast.LENGTH_LONG).show();
        }
        Bundle bundle = intent.getBundleExtra("chuyen");
        if (bundle != null) {
            if (bundle.getInt("chuyen") == 1) {
                fragment = getFragmentManager().beginTransaction();
                cart = new Cart();
                fragment.replace(R.id.llParent, cart);
                fragment.commit();
            }
        }
    }
}
