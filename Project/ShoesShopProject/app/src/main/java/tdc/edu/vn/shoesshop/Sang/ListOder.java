package tdc.edu.vn.shoesshop.Sang;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Adapters.CustumAdapterOder;
import Controls.DatePickerCustom;
import Models.Bill;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.OrderInformationForClient;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;

public class ListOder extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView lvContact;
    CustumAdapterOder adapter;
    ArrayList<Bill> list = new ArrayList<>();
    DatePickerCustom dateTimePicker;
    SearchView svSearchPromotions;
    Intent intent;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_oder);
        lvContact = (ListView) findViewById(R.id.ListOrder);
        dateTimePicker = (DatePickerCustom) findViewById(R.id.dtDatePicker);

        svSearchPromotions = (SearchView) findViewById(R.id.svSearch);
        svSearchPromotions.setOnQueryTextListener(this);

        //back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOder.this, HomeForClient.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                //onBackPressed();
            }
        });

        intent = getIntent();
        String str = intent.getStringExtra("type");
        if(str != null)
        {
            if(str.equals("client"))
            {
                database.child("Clients").child(user.getUid()).child("Transactions").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Bill bill = dataSnapshot.getValue(Bill.class);
                        list.add(bill);
                        adapter.notifyDataSetChanged();
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
            else
            {

            }
        }

        adapter = new CustumAdapterOder(ListOder.this, R.layout.layout_listviewoder, list);
        lvContact.setAdapter(adapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListOder.this, i + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListOder.this, OrderInformationForClient.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}


