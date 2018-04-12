package tdc.edu.vn.shoesshop.Thanh;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import tdc.edu.vn.shoesshop.R;

public class EdittingClientInformation extends AppCompatActivity {
    Button button;
    EditText Ten ;
    EditText SDT;
    EditText DiaChi;
    EditText Email;
    private String sdt;
    private String diachi;
    private String email;
    private String ten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_client_information_activity);

        button = (Button) findViewById(R.id.btnLuu);
        Ten = (EditText) findViewById(R.id.Ten);
        SDT = (EditText) findViewById(R.id.SDT);
        DiaChi = (EditText) findViewById(R.id.diachi);
        Email = (EditText) findViewById(R.id.email);

        ten = Ten.getText().toString();
        sdt = SDT.getText().toString();
        diachi = DiaChi.getText().toString();
        email = Email.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ten.length() == 0 || !isString(ten))
                {
                    Toast.makeText(EdittingClientInformation.this, "ten khong hop le", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(ten)){
                    Ten.setError("Khong duoc bo trong");
                    return;
                }
                else if(TextUtils.isEmpty(sdt)){
                    SDT.setError("Khong duoc bo trong");
                }else
                if(TextUtils.isEmpty(diachi)){
                    DiaChi.setError("Khong duoc bo trong");
                    return;
                }
                else if(TextUtils.isEmpty(email)){
                    Email.setError("Khong duoc bo trong");
                }else
                {
                    button.isShown();
                }
            }
        });
    }
    public boolean isString(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
