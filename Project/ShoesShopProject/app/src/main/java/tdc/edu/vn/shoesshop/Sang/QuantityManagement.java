package tdc.edu.vn.shoesshop.Sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tdc.edu.vn.shoesshop.Khanh.SelectionProductToEditting;
import tdc.edu.vn.shoesshop.R;

public class QuantityManagement extends AppCompatActivity {
    Button btnSave;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutquantitymanagement17);
        btnSave = (Button) findViewById(R.id.btnSaveQuantityInformation);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(QuantityManagement.this, SelectionProductToEditting.class);
                startActivity(intent);
            }
        });
    }
}
