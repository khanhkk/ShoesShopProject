package tdc.edu.vn.shoesshop.Toan;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import Controls.General;
import Models.Account;
import Models.Promotion;
import Models.PromotionsDetail;
import tdc.edu.vn.shoesshop.Khanh.EdittingPromotions;
import tdc.edu.vn.shoesshop.R;

/**
 * A login screen that offers login via email/password.
 */
public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inpuRe_pass, inputName, inputPhone_number, inputAddress;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private RadioButton client, shop;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    static HashMap<Account, ArrayList<Account>> list = new HashMap<>();
    ArrayList<Account> listParent = new ArrayList<>(), listCopy = new ArrayList<>();
    int level = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

// Write data to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
// Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
// Button
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
// EditText
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inpuRe_pass = (EditText) findViewById(R.id.pre_password);
        inputName = (EditText) findViewById(R.id.name);
        inputPhone_number = (EditText) findViewById(R.id.phone_number);
        inputAddress = (EditText) findViewById(R.id.address);
// Radio
        client = (RadioButton) findViewById(R.id.level_client);
        shop = (RadioButton) findViewById(R.id.level_shop);
// Control editText turn off keyboard
        General.setupUI(findViewById(R.id.sign_up), SignupActivity.this);

// ClickListener.

// Button reset pass
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

// Button Sign in
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

// Button Sign up
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name_ac = inputName.getText().toString().trim();
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String pre_pass = inpuRe_pass.getText().toString().trim();
                final String phone_number_ac = inputPhone_number.getText().toString().trim();
                final String address_ac = inputAddress.getText().toString().trim();
                if(client.isChecked()){
                    level = 1;
                }
                if(shop.isChecked()){
                    level = 0;
                }
//  Test
                if (TextUtils.isEmpty(name_ac)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pre_pass)) {
                    Toast.makeText(getApplicationContext(), "Enter pre_password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(pre_pass)) {
                    Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_SHORT).show();
                    inpuRe_pass.setText("");
                    inputPassword.setText("");
                    inputPassword.requestFocus();
                    return;
                }
                if (password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 8 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone_number_ac)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone_number_ac.length() < 10 || phone_number_ac.length() > 11) {
                    Toast.makeText(getApplicationContext(), "Please enter phone number!", Toast.LENGTH_SHORT).show();
                    inputPhone_number.setText("");
                    inputPhone_number.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(address_ac)) {
                    Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    user.sendEmailVerification();
                                    Toast.makeText(SignupActivity.this, "Verify in your email." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
//firebase dat
                                    Account account = new Account(name_ac, email, address_ac, phone_number_ac, level);
                                    myRef.child("Accounts").push().setValue(account);
                                }

                                    /////////
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    finish();
                                }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static class EditingPromotionDetail extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.editing_promotion_detail_activity);
        }
    }
}