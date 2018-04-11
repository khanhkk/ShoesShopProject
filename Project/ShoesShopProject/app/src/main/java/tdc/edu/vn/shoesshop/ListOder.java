package tdc.edu.vn.shoesshop;


    import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
    import java.util.Calendar;

    import Adapters.CustumAdapterOder;
    import Controls.DateTimePicker;

public class ListOder  extends AppCompatActivity {
    ListView lvContact;
    CustumAdapterOder adapter;
    ArrayList<String> list = new ArrayList<>();
    DateTimePicker dateTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutHistoryTransaction22);
        lvContact = (ListView) findViewById(R.id.listview);
        dateTimePicker = (DateTimePicker)findViewById(R.id.dtDatePicker) ;

        dateTimePicker.setDate(Calendar.getInstance().getTime());


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

        CustumAdapterOder customAdapter = new CustumAdapterOder(this,R.layout.layout_oder,list);
        lvContact.setAdapter(customAdapter);
    }
}


