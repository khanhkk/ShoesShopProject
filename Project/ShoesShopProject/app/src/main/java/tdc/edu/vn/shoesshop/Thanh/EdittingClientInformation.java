package tdc.edu.vn.shoesshop.Thanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;

public class EdittingClientInformation extends AppCompatActivity {
    Button btnSave;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_client_information_activity);

        btnSave = (Button) findViewById(R.id.btnSaveClientInformation);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = getIntent();
                intent.setClass(EdittingClientInformation.this, HomeForClient.class);
                startActivity(intent);
            }
        });
    }
}
