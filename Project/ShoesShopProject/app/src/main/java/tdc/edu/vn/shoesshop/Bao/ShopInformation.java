package tdc.edu.vn.shoesshop.Bao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.CustomAdapterShop;
import Models.Contact;
import tdc.edu.vn.shoesshop.R;

public class ShopInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_main_shop);
        List<Contact> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.lv_shop);
        listView.setAdapter(new CustomAdapterShop(ShopInformation.this, image_details));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Contact country = (Contact) o;
                Toast.makeText(ShopInformation.this, " " + " " + country, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  List<Contact> getListData() {
        List<Contact> list = new ArrayList<>();
        Contact nofi = new Contact(R.mipmap.notification, "Thông báo");
        Contact history = new Contact(R.mipmap.b2, "Chương trình khuyến mãi");
        Contact info = new Contact(R.mipmap.c3, "Thông tin của tôi");
        Contact change = new Contact(R.mipmap.c4, "Đổi mật khẩu");
        Contact logout = new Contact(R.mipmap.c5, "Đăng xuất");


        list.add(nofi);
        list.add(history);
        list.add(info);
        list.add(change);
        list.add(logout);

        return list;
    }
}
