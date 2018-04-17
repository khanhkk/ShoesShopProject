package tdc.edu.vn.shoesshop.Bao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import tdc.edu.vn.shoesshop.R;

public class MainInfoCilent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_personal);
        ImageButton button = (ImageButton) findViewById(R.id.btnedit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
