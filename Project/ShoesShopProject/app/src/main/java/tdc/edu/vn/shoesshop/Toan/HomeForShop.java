package tdc.edu.vn.shoesshop.Toan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tdc.edu.vn.shoesshop.Bao.PersonalOfShopFragment;
import tdc.edu.vn.shoesshop.Khanh.HSActivity;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.TransactionOfShopFragment;
import tdc.edu.vn.shoesshop.Son.NotificationShopFragment;

public class HomeForShop extends AppCompatActivity {

    private FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    private HSActivity hsActivity;
    private Home_Client_Fragment home_client_fragment;
    private TransactionOfShopFragment transaction;
    private NotificationShopFragment notificationFragment;
    private PersonalOfShopFragment personal;

    BottomNavigationItemView itemView;
    View badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_shop_activity);

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

        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNav.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        itemView = (BottomNavigationItemView) v;
        badge = LayoutInflater.from(this).inflate(R.layout.notifi_badge, bottomNavigationMenuView, false);

        database.child("Shops").child(users.getUid()).child("Notifications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemView.addView(badge);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
                            itemView.removeView(badge);
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

}
