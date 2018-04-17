package tdc.edu.vn.shoesshop.Toan;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import Controls.General;
import Controls.TabarControl;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;

public class HomeForClient extends AppCompatActivity {
    private FragmentTransaction fragment;
    Cart cart = null;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_client_activity);
        llContainer = (LinearLayout) findViewById(R.id.llParent);
        tabarControl = (TabarControl) findViewById(R.id.tcTabarClient);
        tabarControl.setImageButton1(General.loadSampleResource(this, R.mipmap.home, 80, 80));
        tabarControl.setImageButton2(General.loadSampleResource(this, R.mipmap.notify, 80, 80));
        tabarControl.setImageButton3(General.loadSampleResource(this, R.mipmap.cart, 80, 80));
        tabarControl.setImageButton4(General.loadSampleResource(this, R.mipmap.personal, 80, 80));
        tabarControl.setTabarFunctions(functions);



        Intent intent = getIntent();

            Bundle bundle = intent.getBundleExtra("chuyen");
            if(bundle != null) {
                if (bundle.getInt("chuyen") == 1) {
                    fragment = getFragmentManager().beginTransaction();
                    cart = new Cart();
                    fragment.replace(R.id.llParent, cart);
                    fragment.commit();
                }
            }


//        ibCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(HomeForClient.this,"fff",Toast.LENGTH_SHORT).show();
//                fragment.replace(R.id.llParent, cart);
//                fragment.commit();
//            }
//        });
    }
}
