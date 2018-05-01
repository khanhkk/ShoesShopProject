package tdc.edu.vn.shoesshop.Sang;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.ClientInformationAfterOrder;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;

public class ChangePassword extends AppCompatActivity {

    private TextInputLayout textInputTenDangnhap;
    private TextInputLayout textInputMatkhauhientai;
    private TextInputLayout textInputMatkhaumoi;
    private TextInputLayout textInputNhapmatkhaumoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_activity);

        textInputTenDangnhap = findViewById(R.id.id_tendangnhap);
        textInputMatkhauhientai = findViewById(R.id.id_matkhauhientai);
        textInputMatkhaumoi = findViewById(R.id.id_matkhaumoi);
        textInputNhapmatkhaumoi = findViewById(R.id.id_nhaplaimatkhau);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
            startActivity(intent);
        }
        });
    }

    private boolean validateTenDangnhap() {
        String TenDangnhapInput = textInputTenDangnhap.getEditText().getText().toString().trim();
        if (TenDangnhapInput.isEmpty()) {
            textInputTenDangnhap.setError("Không thể để trống");
            return false;
        } else {

            textInputTenDangnhap.setError(null);
            return true;
        }
    }

    private boolean validateMatkhauhientai() {
        String MatkhauhientaiInput = textInputMatkhauhientai.getEditText().getText().toString().trim();
        if (MatkhauhientaiInput.isEmpty()) {
            textInputMatkhauhientai.setError("Không thể để trống");
            return false;
        } else {
            if((MatkhauhientaiInput.length() < 5))
            {
                textInputMatkhauhientai.setError("Mật khẩu quá ngắn");
                return false;
            }else {
                if((MatkhauhientaiInput.length() > 19))
                {
                    textInputMatkhauhientai.setError("Mật khẩu quá dài");
                    return false;
                }
                else
                {
                    textInputMatkhauhientai.setError(null);
                    return true;
                }
            }
        }
    }

    private boolean validateMatkhaumoi() {
        String MatkhaumoiInput = textInputMatkhaumoi.getEditText().getText().toString().trim();

        if (MatkhaumoiInput.isEmpty()) {
            textInputMatkhaumoi.setError("Không thể để trống");
            return false;
        } else {
            if((MatkhaumoiInput.length() < 5))
            {
                textInputMatkhaumoi.setError("Mật khẩu quá ngắn");
                return false;
            }
                if((MatkhaumoiInput.length() > 19))
                {
                    textInputMatkhaumoi.setError("Mật khẩu quá dài");
                    return false;
                }
            textInputMatkhaumoi.setError(null);
            return true;
            }
        }
    private boolean validateNhaplaimatkhau() {

        String Nhapmatkhaumoi = textInputNhapmatkhaumoi.getEditText().getText().toString().trim();
        String MatkhaumoiInput = textInputMatkhaumoi.getEditText().getText().toString().trim();
        if (Nhapmatkhaumoi.isEmpty()) {
            textInputNhapmatkhaumoi.setError("Không thể để trống");
            return false;
        }  else {
            if((MatkhaumoiInput.length() < 5 ))
            {
                textInputNhapmatkhaumoi.setError("Mật khẩu quá ngắn");
                return false;
            }
            if((MatkhaumoiInput.length() > 19))
            {
                textInputNhapmatkhaumoi.setError("Mật khẩu quá dài");
                return false;
            }
            if (!Nhapmatkhaumoi.equals(MatkhaumoiInput))
            {
                textInputNhapmatkhaumoi.setError("Mật khẩu không khớp");
                return false;
            }
                textInputNhapmatkhaumoi.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateTenDangnhap() | !validateMatkhauhientai() | !validateMatkhaumoi() | !validateNhaplaimatkhau()) {
            return;
        }

        Intent intent = new Intent(ChangePassword.this, HomeForClient.class);
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
