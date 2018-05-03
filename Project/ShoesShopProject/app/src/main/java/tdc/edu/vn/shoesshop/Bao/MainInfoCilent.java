package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.EdittingClientInformation;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;

public class MainInfoCilent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_personal);
        ImageButton button = (ImageButton) findViewById(R.id.btnedit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInfoCilent.this, EdittingClientInformation.class);
                startActivity (intent);
                Toast.makeText(MainInfoCilent.this, "  " , Toast.LENGTH_SHORT).show();
            }
        });
        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInfoCilent.this, HomeForClient.class);
                startActivity(intent);
            }
        });
    }
}
