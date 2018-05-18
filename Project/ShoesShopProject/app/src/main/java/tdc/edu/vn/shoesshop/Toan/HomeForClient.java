package tdc.edu.vn.shoesshop.Toan;

import android.content.Intent;
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

import tdc.edu.vn.shoesshop.Bao.PersonalOfClientLoginedFragment;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.NotificationClientFragment;

public class HomeForClient extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    Intent intent;
    BottomNavigationView bottomNav;
    BottomNavigationItemView itemView;
    View badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_client_activity);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        intent = getIntent();

        String s = intent.getStringExtra("action");
        //Toast.makeText(this, s + "---", Toast.LENGTH_SHORT);
        if (s != null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new Cart()).commit();
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
//        }

        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNav.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(1);
        itemView = (BottomNavigationItemView) v;
        badge = LayoutInflater.from(this).inflate(R.layout.notifi_badge, bottomNavigationMenuView, false);

        }

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (users == null) {
                    startActivity(new Intent(HomeForClient.this, LoginActivity.class));
                    finish();
                }
            }
        };

        database.child("Clients").child(users.getUid()).child("Cart").addValueEventListener(new ValueEventListener() {
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

                                selectedFragment = new Home_Client_Fragment();
                            } else {

                                selectedFragment = new Home_User_Fragment();
                            }
                            break;
                        case R.id.nav_notification:
                            itemView.removeView(badge);
                            selectedFragment = new NotificationClientFragment();
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
