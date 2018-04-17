package tdc.edu.vn.shoesshop.Son;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.OrderClientAdapter;
import Models.OrderClient;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ListOder;

public class OrderInformationForClient extends AppCompatActivity {

    ImageButton back;
    ListView listView;
    ArrayList<OrderClient> arritem;
    public static TextView tvTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order_information_for_client_activity);
        tvTotal = (TextView) findViewById(R.id.tvMoney) ;

        listView = (ListView) findViewById(R.id.lv_item);
        arritem = new ArrayList<OrderClient>();

        arritem.add(new OrderClient(R.mipmap.giay,"0035",199000,249000,1));
        arritem.add(new OrderClient(R.mipmap.giay1,"0035",199000,249000,1));
        arritem.add(new OrderClient(R.mipmap.giay1,"0035",199000,599000,2));
        arritem.add(new OrderClient(R.mipmap.giay,"0035",229000,599000,1));


        OrderClientAdapter adapter = new OrderClientAdapter(OrderInformationForClient.this,R.layout.order_information_for_client_activity_custom,arritem);
        listView.setAdapter(adapter);

        back = (ImageButton) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderInformationForClient.this,ListOder.class);
                startActivity(intent);
            }
        });

    }
}
