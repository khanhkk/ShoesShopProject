package tdc.edu.vn.shoesshop.Son;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.NotificationClientAdapter;
import Adapters.OrderClientAdapter;
import Models.Notification;
import Models.OrderClient;
import tdc.edu.vn.shoesshop.Bao.MainCilent;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ListOder;

public class NotificationClientActivity extends AppCompatActivity{
    ImageButton back;
    ListView listView;
    ArrayList<Notification> arritem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notification_client_layout);

        listView = (ListView) findViewById(R.id.lv_notifiC);
        arritem = new ArrayList<Notification>();

        arritem.add(new Notification(R.mipmap.shop, "GoShop", "đang vận chuyển đơn hàng của bạn", "11/5/2017"));
        arritem.add(new Notification(R.mipmap.giay, "GâugâuShop", "đang có khuyến mãi", "11/5/2017"));



        NotificationClientAdapter adapter = new NotificationClientAdapter(NotificationClientActivity.this, R.layout.notification_client_layout_custom, arritem);
        listView.setAdapter(adapter);

        back = (ImageButton) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationClientActivity.this, MainCilent.class);
                startActivity(intent);
            }
        });
    }
    }