package tdc.edu.vn.shoesshop.Thanh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tdc.edu.vn.shoesshop.Bao.MainInfoCilent;
import tdc.edu.vn.shoesshop.R;

public class EdittingClientInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_client_information_activity);

        Button save = (Button) findViewById(R.id.btnluu);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdittingClientInformation.this, MainInfoCilent.class);
                startActivity(intent);

            }
        });

    }
}
