package tdc.edu.vn.shoesshop.Sang;


    import android.content.Intent;
    import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.SearchView;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.Toast;

    import java.util.ArrayList;

import Adapters.CustumAdapterOder;
import Controls.DatePickerCustom;
    import Models.Bill;
    import tdc.edu.vn.shoesshop.R;
    import tdc.edu.vn.shoesshop.Son.OrderInformationForClient;

public class ListOder  extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ListView lvContact;
    CustumAdapterOder adapter;
    ArrayList<Bill> list = new ArrayList<>();
    DatePickerCustom dateTimePicker;

  //  SearchView svSearchPromotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_oder);
        lvContact = (ListView) findViewById(R.id.ListOrder);
        dateTimePicker = (DatePickerCustom)findViewById(R.id.dtDatePicker) ;

//        svSearchPromotions = (SearchView) findViewById(R.id.Search);
//        svSearchPromotions.setOnQueryTextListener(this);

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

        list.add(new Bill("nhom5@gmail.com","12/05/2018"));
        list.add(new Bill("nhom5@gmail.com","12/05/2018"));

        CustumAdapterOder customAdapter = new CustumAdapterOder(ListOder.this,R.layout.layout_listviewoder,list);
        lvContact.setAdapter(customAdapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListOder.this, i+ "",Toast.LENGTH_SHORT).show();
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


