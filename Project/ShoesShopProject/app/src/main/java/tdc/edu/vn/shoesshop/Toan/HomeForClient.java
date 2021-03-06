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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.Notification;
import tdc.edu.vn.shoesshop.Bao.PersonalOfClientLoginedFragment;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.NotificationClientFragment;

public class HomeForClient extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    Intent intent;
    public static BottomNavigationView bottomNav;
    BottomNavigationItemView itemView;
    BottomNavigationMenuView bottomNavigationMenuView;
    View badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_for_client_activity);
        check(this);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        final BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
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
//        }

            bottomNavigationMenuView = (BottomNavigationMenuView) bottomNav.getChildAt(0);
            View v = bottomNavigationMenuView.getChildAt(1);
            itemView = (BottomNavigationItemView) v;


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
        if (users != null) {
            database.child("Clients").child(users.getUid()).child("Notifications").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Notification notification = dataSnapshot.getValue(Notification.class);
                    if (notification != null) {
                        if (notification.isStatus() == false) {
                            badge = LayoutInflater.from(HomeForClient.this).inflate(R.layout.notifi_badge, bottomNavigationMenuView, false);
                            itemView.addView(badge);
                        }

                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Notification notification = dataSnapshot.getValue(Notification.class);
                    if (notification != null) {
                        if (notification.isStatus() == false) {
                            badge = LayoutInflater.from(HomeForClient.this).inflate(R.layout.notifi_badge, bottomNavigationMenuView, false);
                            itemView.addView(badge);
                        }

                    }
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
                            if (badge != null) {
                                itemView.removeView(badge);
                            }
                            database.child("Clients").child(users.getUid()).child("Notifications").orderByChild("status").equalTo(false).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    Notification notification = dataSnapshot.getValue(Notification.class);
                                    notification.setStatus(true);
                                    database.child("Clients").child(users.getUid()).child("Notifications").child(dataSnapshot.getKey()).setValue(notification);
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

    public void check(HomeForClient view) {
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
