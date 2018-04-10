package tdc.edu.vn.shoesshop;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.custumAdapterHistory;


public class ListHistoryTransaction extends AppCompatActivity {
    ListView lvContact;
    custumAdapterHistory adapter;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout22);
        lvContact = (ListView) findViewById(R.id.listview);

//        ArrayList<contactList22>  arrayList = new ArrayList<>();
//        contactList22 contact1 = new contactList22(null,"Nguyen Van A",);
//        contactList22 contact2 = new contactList22(null,"Nguyen Van B","092xx xxxx");
//        contactList22 contact3 = new contactList22(null,"Nguyen Van C","092xxx xxx");
//        contactList22 contact4 = new contactList22(null,"Nguyen Van D","092xxx xxx");


//        arrayList.add(contact1);
//        arrayList.add(contact2);
//        arrayList.add(contact3);
//        arrayList.add(contact4);

        list.add("dfadfa");
        list.add("dfadfa");
        list.add("dfadfa");
        list.add("dfadfa");

        custumAdapterHistory customAdapter = new custumAdapterHistory(this,R.layout.listview_layout22,list);
        lvContact.setAdapter(customAdapter);

    }
}

