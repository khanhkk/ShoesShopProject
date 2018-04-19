package tdc.edu.vn.shoesshop.Thanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controls.General;
import tdc.edu.vn.shoesshop.Bao.MainInfoCilent;
import tdc.edu.vn.shoesshop.R;

public class EdittingClientInformation extends AppCompatActivity {

    private EditText edtten, edtsdt, edtdiachi, edtemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_client_information_activity);

        Button save = (Button) findViewById(R.id.btnLuu);
        EditText ten = (EditText) findViewById(R.id.ten);
        EditText sdt = (EditText) findViewById(R.id.sdt);
        EditText diachi = (EditText) findViewById(R.id.diachi);
        EditText email = (EditText) findViewById(R.id.email);

        General.setupUI(findViewById(R.id.editting_client_information), EdittingClientInformation.this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtten.getText().toString().trim();
                int sdt = Integer.parseInt(edtsdt.getText().toString().trim());
                String diachi = edtdiachi.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                if(TextUtils.isEmpty(ten)){
                    Toast.makeText(getApplicationContext(), "Please enter name!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(edtsdt.getText() + "")){
                    Toast.makeText(getApplicationContext(), "Enter phone nuber", Toast.LENGTH_LONG).show();
                    return;
                 }
                if (TextUtils.isEmpty(diachi)){
                    Toast.makeText(getApplicationContext(),"Please enter Address", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if ((edtsdt.getText()+" ").length() < 10 || (edtsdt.getText()+ "").length()>11){
                    Toast.makeText(getApplicationContext(), "Please Characters Between 10-11", Toast.LENGTH_LONG).show();
                    edtsdt.setText("");
                    edtsdt.findFocus();
                    return;
                }
                Intent intent = new Intent(EdittingClientInformation.this, MainInfoCilent.class);
                startActivity(intent);

            }
        });

    }
}
