package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ChangePassword;

public class MainShop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shop);

        ImageView imgthognbao = (ImageView) findViewById(R.id.imgthongbao);
        TextView txtthongbao = (TextView) findViewById(R.id.txtthongbao);
        txtthongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainShop.this, " Thông báo " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgkhuyenmai = (ImageView) findViewById(R.id.imgkhuyenmai);
        TextView txtkhuyenmai = (TextView) findViewById(R.id.txtkhuyenmai);
        txtkhuyenmai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainShop.this, " Chương trình khuyến mãi " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgthongtin = (ImageView) findViewById(R.id.imgthongtin);
        TextView txtthongtin = (TextView) findViewById(R.id.txtthongtin);
        txtthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainShop.this, MainInfoShop.class);
                startActivity (intent);
                Toast.makeText(MainShop.this, " Thông tin của tôi " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgdoimk = (ImageView) findViewById(R.id.imgdoimk);
        TextView txtdoimk = (TextView) findViewById(R.id.txtdoimk);
        txtdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainShop.this, ChangePassword.class);
                startActivity (intent);
                Toast.makeText(MainShop.this, " Đổi mật khẩu " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imglogout = (ImageView) findViewById(R.id.imglogout);
        TextView txtlogout = (TextView) findViewById(R.id.txtlogout);
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainShop.this, " Đăng xuất " , Toast.LENGTH_SHORT).show();
            }
        });


    }
}
