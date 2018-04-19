package tdc.edu.vn.shoesshop.Son;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import Adapters.OrderShopAdapter;;
import Models.OrderShop;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ListOder;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;

public class OrderInformationForShop extends AppCompatActivity {
    ListView listView;
    ImageButton back;
    ArrayList<OrderShop> arritem;
    public static TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_information_for_shop_activity);

        back = (ImageButton) findViewById(R.id.btnBack);
        tvTotal = (TextView) findViewById(R.id.tvMoney);
        listView = (ListView) findViewById(R.id.lv_item);
        arritem = new ArrayList<OrderShop>();

        arritem.add(new OrderShop(R.mipmap.giay,"converse classic",199000,249000,1));
        arritem.add(new OrderShop(R.mipmap.giay,"0035",199000,349000,2));
        arritem.add(new OrderShop(R.mipmap.giay,"0035",199000,249000,1));
        arritem.add(new OrderShop(R.mipmap.giay,"0035",199000,249000,2));
        OrderShopAdapter adapter = new OrderShopAdapter(OrderInformationForShop.this, R.layout.order_information_for_shop_activity_custom,arritem);
        listView.setAdapter(adapter);

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(OrderInformationForShop.this,ListOder.class);
//                startActivity(intent);
//            }
//        });
        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderInformationForShop.this, ListOder.class);
                startActivity(intent);
            }
        });

    }


}
