package tdc.edu.vn.shoesshop.Bao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import Adapters.CustomAdapterCilent;
import Models.Contact;
import tdc.edu.vn.shoesshop.R;

public class MainCustomer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cilent);
        final Button button = (Button) findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        List<Contact> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.lv_cilent);
        listView.setAdapter(new CustomAdapterCilent(MainCustomer.this, image_details));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Contact country = (Contact) o;
                Toast.makeText(MainCustomer.this, " " + " " + country, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private List<Contact> getListData() {
        List<Contact> list = new ArrayList<>();
        Contact nofi = new Contact(R.mipmap.notification, "Thông báo");
        Contact donhang = new Contact(R.mipmap.donhang, "Đơn hàng của tôi");


        list.add(nofi);
        list.add(donhang);

        return list;
    }
}
