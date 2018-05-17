package tdc.edu.vn.shoesshop.Sang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;

public class ChangePassword extends AppCompatActivity {

    private TextInputLayout textInputTenDangnhap;
    private TextInputLayout textInputMatkhauhientai;
    private TextInputLayout textInputMatkhaumoi;
    private TextInputLayout textInputNhapmatkhaumoi;
    Button saveChangePass;
    private FirebaseAuth auth;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_activity);
        auth = FirebaseAuth.getInstance();
        textInputTenDangnhap = findViewById(R.id.id_tendangnhap);
        textInputMatkhauhientai = findViewById(R.id.id_matkhauhientai);
        textInputMatkhaumoi = findViewById(R.id.id_matkhaumoi);
        textInputNhapmatkhaumoi = findViewById(R.id.id_nhaplaimatkhau);
        saveChangePass = (Button) findViewById(R.id.btnChangePass);
        auth = FirebaseAuth.getInstance();
        final String email = textInputTenDangnhap.getEditText().getText().toString().trim();
        final String pass = textInputMatkhauhientai.getEditText().getText().toString().trim();
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
        saveChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateTenDangnhap() && validateMatkhauhientai() && validateMatkhaumoi() &&validateNhaplaimatkhau()) {
                    if (user != null) {
                        user.updatePassword(textInputMatkhaumoi.getEditText().getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ChangePassword.this, "Password is updated, sign in with new password!", Toast.LENGTH_LONG).show();
                                            auth.signOut();
                                            Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(ChangePassword.this, "Failed to update password!", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
                    }
                }
            }
        });
    }

    private boolean validateTenDangnhap() {
        String TenDangnhapInput = textInputTenDangnhap.getEditText().getText().toString().trim();
        if(user!= null) {
            if (!TenDangnhapInput.equals(user.getEmail())) {
                Toast.makeText(getApplicationContext(), "Email fail!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
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
            if ((MatkhauhientaiInput.length() < 6)) {
                textInputMatkhauhientai.setError("Mật khẩu quá ngắn");
                return false;
            } else {
                if ((MatkhauhientaiInput.length() > 19)) {
                    textInputMatkhauhientai.setError("Mật khẩu quá dài");
                    return false;
                } else {
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
            if ((MatkhaumoiInput.length() < 6)) {
                textInputMatkhaumoi.setError("Mật khẩu quá ngắn");
                return false;
            }
            if ((MatkhaumoiInput.length() > 19)) {
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
        } else {
            if ((MatkhaumoiInput.length() < 6)) {
                textInputNhapmatkhaumoi.setError("Mật khẩu quá ngắn");
                return false;
            }
            if ((MatkhaumoiInput.length() > 19)) {
                textInputNhapmatkhaumoi.setError("Mật khẩu quá dài");
                return false;
            }
            if (!Nhapmatkhaumoi.equals(MatkhaumoiInput)) {
                textInputNhapmatkhaumoi.setError("Mật khẩu không khớp");
                return false;
            }
            textInputNhapmatkhaumoi.setError(null);
            return true;
        }
    }

}
