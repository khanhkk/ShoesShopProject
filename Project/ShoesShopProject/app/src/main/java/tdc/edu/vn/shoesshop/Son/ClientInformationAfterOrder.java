package tdc.edu.vn.shoesshop.Son;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;

public class ClientInformationAfterOrder extends AppCompatActivity {
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputHoten;
    private TextInputLayout textInputSdt;
    private TextInputLayout textInputDiachi;
    //ImageButton back;
    //Button btnOrder;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_information_after_order_activity);
        textInputEmail = findViewById(R.id.id_email);
        textInputHoten = findViewById(R.id.id_hoten);
        textInputSdt = findViewById(R.id.id_sdt);
        textInputDiachi = findViewById(R.id.id_diachi);

        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientInformationAfterOrder.this,HomeForClient.class);
                Bundle bundle = new Bundle();
                bundle.putInt("chuyen",1);
                intent.putExtra("chuyen",bundle);
                startActivity(intent);
            }
        });

    }
    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Không thể để trống");
            return false;
        } else {
            if(!emailInput.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            {
                textInputEmail.setError("Email không đúng !");
                return false;
            }else
            {
                textInputEmail.setError(null);
                return true;
            }

        }
    }

    private boolean validateHoten() {
        String usernameInput = textInputHoten.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputHoten.setError("Không thể để trống");
            return false;
        } else {
            textInputHoten.setError(null);
            return true;
        }
    }

    private boolean validateSdt() {
        String sdtInput = textInputSdt.getEditText().getText().toString().trim();

        if (sdtInput.isEmpty()) {
            textInputSdt.setError("Không thể để trống");
            return false;
        } else {
            if(sdtInput.length() != 10 && sdtInput.length() != 11)
            {
                textInputSdt.setError("Số điện thoại chưa đúng !");
                return false;
            }else
            {
                textInputSdt.setError(null);
                return true;
            }

        }
    }
    private boolean validateDiachi() {

        String passwordInput = textInputDiachi.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputDiachi.setError("Không thể để trống");
            return false;
        } else {
            textInputDiachi.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validateHoten() | !validateSdt() | !validateDiachi()) {
            return;
        }




        Intent intent = new Intent(ClientInformationAfterOrder.this, HomeForClient.class);
        startActivity(intent);

//        String input = "Email: " + textInputEmail.getEditText().getText().toString();
//        input += "\n";
//        input += "Họ Tên: " + textInputHoten.getEditText().getText().toString();
//        input += "\n";
//        input += "Sdt: " + textInputSdt.getEditText().getText().toString();
//        input += "\n";
//        input += "Địa chỉ: " + textInputDiachi.getEditText().getText().toString();
//
//        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
