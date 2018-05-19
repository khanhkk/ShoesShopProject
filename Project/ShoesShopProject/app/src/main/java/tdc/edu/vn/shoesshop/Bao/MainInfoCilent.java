package tdc.edu.vn.shoesshop.Bao;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import Controls.General;
import Models.Client;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.EdittingClientInformation;

public class MainInfoCilent extends AppCompatActivity {
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ImageButton button;
    Bundle bundle;
    Intent intent;
    String client_id;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private TextView txtnanme;
    private TextView txtsdt;
    private TextView txtemail;
    private TextView txtdiachi;
    private ImageView imgcilent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_personal);
        txtnanme = (TextView) findViewById(R.id.txtnameclient);
        txtsdt = (TextView) findViewById(R.id.txtsdtclient);
        txtemail = (TextView) findViewById(R.id.txtemailclient);
        txtdiachi = (TextView) findViewById(R.id.txtdcclient);
        imgcilent = (ImageView) findViewById(R.id.imginfoclient);
        button = (ImageButton) findViewById(R.id.btnedit);

        intent = getIntent();
        // client_id = intent.getStringExtra("shop");
        // data();
        checkRight();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInfoCilent.this, EdittingClientInformation.class);
                startActivity(intent);
                Toast.makeText(MainInfoCilent.this, "  ", Toast.LENGTH_SHORT).show();
            }
        });
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainInfoCilent.this, HomeForClient.class);
//                startActivity(intent);
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        MainInfoCilent.super.onBackPressed();
    }

    public void checkRight() {
        database.child("Clients").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Client client = dataSnapshot.getValue(Client.class);
                txtnanme.setText(client.getName());
                txtsdt.setText("Số điện thoại:  " + client.getPhone());
                txtemail.setText("Email:  " + client.getEmail());
                txtdiachi.setText("Địa chỉ:  " + client.getAddress());
                if(client.getImages() != null)
                {
                    try {
                        Bitmap bitmap = General.decodeFromFirebaseBase64(client.getImages());
                        Glide.with(MainInfoCilent.this).load(bitmap).into(imgcilent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
