package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Models.Account;
import Models.Shop;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.EdittingShopInformation;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;

public class MainInfoShop extends AppCompatActivity {
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView name, phone, email, address;
    ImageView image;
    ImageButton button;
    Bundle bundle;
    Intent intent;
    String shop_id;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_shop);
        name = (TextView) findViewById(R.id.txtnameshop);
        phone = (TextView) findViewById(R.id.txtsdtshop);
        email = (TextView) findViewById(R.id.txtemailshop);
        address = (TextView) findViewById(R.id.txtdcshop);
        button = (ImageButton) findViewById(R.id.btneditshop);
        intent = getIntent();
        shop_id = intent.getStringExtra("shop");
        data();
        checkRight();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInfoShop.this, EdittingShopInformation.class);
                startActivity(intent);
            }
        });
        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainInfoShop.this, HomeForShop.class);
//                startActivity(intent);
                onBackPressed();
            }
        });
    }

    //back trang truoc
    @Override
    public void onBackPressed() {
        MainInfoShop.super.onBackPressed();

    }

    public void data() {
        if (shop_id != null) {
            database.child("Shops").orderByKey().equalTo(shop_id).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Shop shop = dataSnapshot.getValue(Shop.class);
                    name.setText(shop.getName());
                    phone.setText("Số điện thoại:  " + shop.getPhone());
                    email.setText("Email:  " + shop.getEmail());
                    address.setText("Địa chỉ:  " + shop.getAddress());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    public void checkRight()
    {

        database.child("Accounts").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Account account = dataSnapshot.getValue(Account.class);
                if ((account.getLevel() == 1) || (user == null))
                    button.setVisibility(View.INVISIBLE);
                else
                    button.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
