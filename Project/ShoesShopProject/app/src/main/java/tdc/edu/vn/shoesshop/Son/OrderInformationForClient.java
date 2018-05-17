package tdc.edu.vn.shoesshop.Son;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.OrderClientAdapter;
import Models.OrderClient;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ListOder;

public class OrderInformationForClient extends AppCompatActivity {

    Button huy;
    TextView tinhtrang;
    ListView listView;
    ArrayList<OrderClient> arritem;
    public static TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order_information_for_client_activity);
        tvTotal = (TextView) findViewById(R.id.tvMoney);
        huy = (Button) findViewById(R.id.id_huy);
        tinhtrang = (TextView)findViewById(R.id.txt_tinhtrang);
        listView = (ListView) findViewById(R.id.lv_item);
        arritem = new ArrayList<OrderClient>();

        arritem.add(new OrderClient(R.mipmap.giay, "0036", 199000, 249000, 1));
        arritem.add(new OrderClient(R.mipmap.giay1, "0035", 199000, 249000, 1));
        arritem.add(new OrderClient(R.mipmap.giay1, "0035", 199000, 599000, 2));
        arritem.add(new OrderClient(R.mipmap.giay, "0035", 229000, 599000, 1));

        OrderClientAdapter adapter = new OrderClientAdapter(OrderInformationForClient.this, R.layout.order_information_for_client_activity_custom, arritem);
        listView.setAdapter(adapter);

        huy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                tinhtrang.setText("Đã hủy");
                huy.setBackground(getDrawable(R.drawable.button1_style));
            }
        });

//        back = (ImageButton) findViewById(R.id.btnBack);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(OrderInformationForClient.this, ListOder.class);
//                startActivity(intent);
//            }
//        });
//  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderInformationForClient.this, ListOder.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }
}
