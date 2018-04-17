package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.CustomAdapter;
import Models.Contact;
import tdc.edu.vn.shoesshop.Khanh.Promotions;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ChangePassword;
import tdc.edu.vn.shoesshop.Sang.ListHistoryTransaction;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;

public class ClientInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        List<Contact> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(ClientInformation.this, image_details));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                switch (position)
                {
                    case 0: {
                        Intent intent = new Intent(ClientInformation.this, HomeForShop.class);
                        startActivity(intent);
                    }
                    break;
                    case 1: {
                        Intent intent = new Intent(ClientInformation.this, ListHistoryTransaction.class);
                        startActivity(intent);
                    }
                    break;
                    case 2: {
                        Intent intent = new Intent(ClientInformation.this, MainInfoCilent.class);
                        startActivity(intent);
                    }
                    break;
                    case 3: {
                        Intent intent = new Intent(ClientInformation.this, ChangePassword.class);
                        startActivity(intent);
                    }
                    break;
                    case 4: {
                        //auth.signOut();
                        Intent intent = new Intent(ClientInformation.this, MainCustomer.class);
                        startActivity(intent);
                    }
                    break;

                }
                if(position == 2){
                    Intent intent = new Intent(ClientInformation.this, MainInfoCilent.class);
                    startActivity(intent);
                }
                Object o = listView.getItemAtPosition(position);
                Contact country = (Contact) o;
                Toast.makeText(ClientInformation.this, " " + " " + country, Toast.LENGTH_SHORT).show();

            }
        });
    }
    private  List<Contact> getListData() {
        List<Contact> list = new ArrayList<>();
        Contact nofi = new Contact(R.mipmap.notification, "Thông báo");
        Contact history = new Contact(R.mipmap.c2, "Lịch sử mua hàng");
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
