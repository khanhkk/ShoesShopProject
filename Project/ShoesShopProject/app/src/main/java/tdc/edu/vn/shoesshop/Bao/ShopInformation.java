package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import Adapters.CustomAdapterShop;
import Models.Contact;
import Models.Promotion;
import tdc.edu.vn.shoesshop.Khanh.Promotions;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ChangePassword;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;
import tdc.edu.vn.shoesshop.Toan.HomeLayout;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;

public class ShopInformation extends AppCompatActivity {
    private FirebaseAuth auth;

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
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(ShopInformation.this, HomeForShop.class);
                        startActivity(intent);
                    }
                    break;
                    case 1: {
                        Intent intent = new Intent(ShopInformation.this, Promotions.class);
                        startActivity(intent);
                    }
                    break;
                    case 2: {
                        Intent intent = new Intent(ShopInformation.this, MainInfoShop.class);
                        startActivity(intent);
                    }
                    break;
                    case 3: {
                        Intent intent = new Intent(ShopInformation.this, ChangePassword.class);
                        startActivity(intent);
                    }
                    break;
                    case 4: {
                        //auth.signOut();
                        Intent intent = new Intent(ShopInformation.this, MainCustomer.class);
                        startActivity(intent);
                    }
                    break;

                }
                Object o = listView.getItemAtPosition(position);
                Contact country = (Contact) o;
                Toast.makeText(ShopInformation.this, " " + " " + country, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Contact> getListData() {
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
