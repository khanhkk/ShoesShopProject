package tdc.edu.vn.shoesshop.Son;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ListOder;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;

public class ClientInformationAfterOrder extends AppCompatActivity {

    ImageButton back;
    Button btnOrder;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_information_after_order_activity);

        btnOrder = (Button) findViewById(R.id.btnDatHang);
        back = (ImageButton) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientInformationAfterOrder.this,HomeForClient.class);
                Bundle bundle = new Bundle();
                bundle.putInt("chuyen",1);
                intent.putExtra("chuyen",bundle);
                startActivity(intent);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientInformationAfterOrder.this,HomeForClient.class);
                startActivity(intent);
            }
        });

    }
}
