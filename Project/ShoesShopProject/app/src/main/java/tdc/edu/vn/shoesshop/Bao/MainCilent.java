package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Field;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ChangePassword;
import tdc.edu.vn.shoesshop.Sang.ListHistoryTransaction;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;


public class MainCilent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cilent);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        ImageView imgthognbao = (ImageView) findViewById(R.id.imgthongbao);
        TextView txtthongbao = (TextView) findViewById(R.id.txtthongbao);
        txtthongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainCilent.this, " Thông báo " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imglichsu = (ImageView) findViewById(R.id.imglichsu);
        TextView txtlichsu = (TextView) findViewById(R.id.txtlichsu);
        txtlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainCilent.this, ListHistoryTransaction.class);
                startActivity (intent);
                Toast.makeText(MainCilent.this, " Lịch sử mua hàng " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgthongtin = (ImageView) findViewById(R.id.imgthongtin);
        TextView txtthongtin = (TextView) findViewById(R.id.txtthongtin);
        txtthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainCilent.this, MainInfoCilent.class);
                startActivity (intent);
                Toast.makeText(MainCilent.this, " Thông tin của tôi " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgdoimk = (ImageView) findViewById(R.id.imgdoimk);
        TextView txtdoimk = (TextView) findViewById(R.id.txtdoimk);
        txtdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainCilent.this, ChangePassword.class);
                startActivity (intent);
                Toast.makeText(MainCilent.this, " Đổi mật khẩu " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imglogout = (ImageView) findViewById(R.id.imglogout);
        TextView txtlogout = (TextView) findViewById(R.id.txtlogout);
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(MainCilent.this, LoginActivity.class);
                startActivity (intent);
                Toast.makeText(MainCilent.this, " Đăng xuất " , Toast.LENGTH_SHORT).show();
            }
        });


    }
}
