package tdc.edu.vn.shoesshop;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;

import java.util.ArrayList;

import Adapters.CustumAdapterHistory;


public class ListHistoryTransaction extends AppCompatActivity {
    ListView lvContact;
    CustumAdapterHistory adapter;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layouthistorytransaction22);
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

        list.add("Đơn hàng đã giao");
        list.add("Đơn hàng đang + vận chuyển");
        list.add("Đơn hàng đã hủy");
        list.add("đơn hàng chờ xử lí");

        CustumAdapterHistory customAdapter = new CustumAdapterHistory(this,R.layout.listview_layouthistorytransaction22,list);
        lvContact.setAdapter(customAdapter);

    }
}

