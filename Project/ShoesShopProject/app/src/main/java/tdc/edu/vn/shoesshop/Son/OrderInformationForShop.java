package tdc.edu.vn.shoesshop.Son;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
    Button vanchuyen;
    TextView tinhtrang;
    ArrayList<OrderShop> arritem;
    public static TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_information_for_shop_activity);

        back = (ImageButton) findViewById(R.id.btnBack);
        tvTotal = (TextView) findViewById(R.id.tvMoney);
        listView = (ListView) findViewById(R.id.lv_item);
        tinhtrang = (TextView) findViewById(R.id.txt_tinhtrang);
        vanchuyen = (Button) findViewById(R.id.id_vanchuyen);
        arritem = new ArrayList<OrderShop>();

        arritem.add(new OrderShop(R.mipmap.giay,"converse classic",199000,249000,1));
        arritem.add(new OrderShop(R.mipmap.giay,"0035",199000,349000,2));
        arritem.add(new OrderShop(R.mipmap.giay,"0035",199000,249000,1));
        arritem.add(new OrderShop(R.mipmap.giay,"0035",199000,249000,2));
        OrderShopAdapter adapter = new OrderShopAdapter(OrderInformationForShop.this, R.layout.order_information_for_shop_activity_custom,arritem);
        listView.setAdapter(adapter);

        vanchuyen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (vanchuyen.getText().equals("Vận chuyển")) {

                    tinhtrang.setText("Đang vận chuyển");
                    vanchuyen.setBackground(getDrawable(R.drawable.button2_style));
                    vanchuyen.setText("Hủy");
                }
                else {
                    tinhtrang.setText("Đã hủy");
                    vanchuyen.setBackground(getDrawable(R.drawable.button_style));
                    vanchuyen.setText("Vận chuyển");
                }
            }
        });

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
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderInformationForShop.this, ListOder.class);
                startActivity(intent);
            }
        });

    }



}
