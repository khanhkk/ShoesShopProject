package tdc.edu.vn.shoesshop.Sang;


    import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.CustumAdapterOder;
import Controls.DatePickerCustom;
import tdc.edu.vn.shoesshop.R;

public class ListOder  extends AppCompatActivity {
    ListView lvContact;
    CustumAdapterOder adapter;
    ArrayList<String> list = new ArrayList<>();
    DatePickerCustom dateTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_oder);
        lvContact = (ListView) findViewById(R.id.ListOrder);
        dateTimePicker = (DatePickerCustom)findViewById(R.id.dtDatePicker) ;

        //dateTimePicker.setDate(Calendar.getInstance().getTime());


//        ArrayList<contactList22>  arrayList = new ArrayList<>();
//        contactList22 contact1 = new contactList22(null,"Nguyen Van A",);
//        contactList22 contact2 = new contactList22(null,"Nguyen Van B","092xx xxxx");
//        contactList22 contact3 = new contactList22(null,"Nguyen Van C","092xxx xxx");
//        contactList22 contact4 = new contactList22(null,"Nguyen Van D","092xxx xxx");


//        arrayList.add(contact1);
//        arrayList.add(contact2);
//        arrayList.add(contact3);
//        arrayList.add(contact4);

        list.add("Don hang");
        list.add("don hang");

        CustumAdapterOder customAdapter = new CustumAdapterOder(ListOder.this,R.layout.layout_listviewoder,list);
        lvContact.setAdapter(customAdapter);
    }
}


