package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.EdittingShopInformation;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;

public class MainInfoShop extends AppCompatActivity {
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private TextView txtnanmeshop;
    private TextView txtsdtshop;
    private TextView txtndd;
    private TextView txtemailshop;
    private TextView txtdiachishop;
    private TextView txtfbshop;
    private TextView txtstkshop;
    private ImageView imgshop;
    public static  final String Bundle = "bundle";
    public static final String NameShop = "nameshop";
    public static final String SdtShop = "sodienthoaishop";
    public static final String Ndd = "nguoidaidien";
    public static final String EmailShop = "emailshop";
    public static final String FbShop = "facebookshop";
    public static final String DiaChiShop = "diachishop";
    public static final String StkShop = "sotaikhoanshop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_shop);
        ImageButton button = (ImageButton) findViewById(R.id.btneditshop);
        if (user == null)
            button.setVisibility(View.GONE);
        else
            button.setVisibility(View.VISIBLE);
        txtnanmeshop= (TextView) findViewById(R.id.txtnameshop);
        txtsdtshop = (TextView) findViewById(R.id.txtsdtshop) ;
        txtndd= (TextView) findViewById(R.id.txtndd) ;
        txtemailshop = (TextView) findViewById(R.id.txtemailshop) ;
        txtfbshop = (TextView) findViewById(R.id.txtfbshop) ;
        txtdiachishop = (TextView) findViewById(R.id.txtdcshop) ;
        txtstkshop = (TextView) findViewById(R.id.txtstkshop) ;
        imgshop = (ImageView) findViewById(R.id.imgshop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInfoShop.this, EdittingShopInformation.class);
                startActivity(intent);
                Toast.makeText(MainInfoShop.this, "  ", Toast.LENGTH_SHORT).show();
            }
        });
        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInfoShop.this, HomeForShop.class);
                startActivity(intent);
            }
        });
    }
    public void byBundle() {
        Intent intent = new Intent(MainInfoShop.this, EdittingShopInformation.class);
        Bundle bundle = new Bundle();

        bundle.putString(NameShop, txtnanmeshop.getText().toString());
        bundle.putString(SdtShop, txtsdtshop.getText().toString());
        bundle.putString(Ndd, txtndd.getText().toString());
        bundle.putString(EmailShop, txtemailshop.getText().toString());
        bundle.putString(FbShop, txtfbshop.getText().toString());
        bundle.putString(DiaChiShop, txtdiachishop.getText().toString());
        bundle.putString(StkShop, txtstkshop.getText().toString());
        intent.putExtra(Bundle, bundle);
        startActivity(intent);
    }
}
