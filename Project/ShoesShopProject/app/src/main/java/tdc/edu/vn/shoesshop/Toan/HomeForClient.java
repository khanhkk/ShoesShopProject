package tdc.edu.vn.shoesshop.Toan;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Controls.TabarControl;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;

public class HomeForClient extends AppCompatActivity {
    private FragmentTransaction fragment;
    Cart cart = null;
    Home_Client_Fragment b = null;
    TabarControl tabarControl;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_client_activity);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
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
        tabarControl.setImageButton1(R.mipmap.home);
        tabarControl.setImageButton2(R.mipmap.notify);
        tabarControl.setImageButton3(R.mipmap.cart2);
        tabarControl.setImageButton4(R.mipmap.personal);
        tabarControl.setTabarFunctions(functions);


//        ibCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(HomeForClient.this,"fff",Toast.LENGTH_SHORT).show();
//                fragment.replace(R.id.llParent, cart);
//                fragment.commit();
//            }
//        });
    }
    TabarControl.TabarFunctions functions = new TabarControl.TabarFunctions() {
        @Override
        public void onButton1Clicked() {
            fragment = getFragmentManager().beginTransaction();
            b = new Home_Client_Fragment();
            fragment.replace(R.id.llParent, b);
            fragment.commit();        }

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
    LinearLayout llContainer;
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
