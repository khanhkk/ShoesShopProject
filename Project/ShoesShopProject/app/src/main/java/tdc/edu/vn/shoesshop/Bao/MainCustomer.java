package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;


public class MainCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_customer);

        Button btndangnhap = (Button) findViewById(R.id.btnlogin);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainCustomer.this, LoginActivity.class);
                startActivity (intent);
                Toast.makeText(MainCustomer.this, " Đăng nhập ", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imgthognbao = (ImageView) findViewById(R.id.imgthongbao);
        TextView txtthongbao = (TextView) findViewById(R.id.txtthongbao);
        txtthongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainCustomer.this, " Thông báo ", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgdonhang = (ImageView) findViewById(R.id.imgdonhang);
        TextView txtdonhang = (TextView) findViewById(R.id.txtdonhang);
        txtdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainCustomer.this, " Đơn hàng của tôi ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
