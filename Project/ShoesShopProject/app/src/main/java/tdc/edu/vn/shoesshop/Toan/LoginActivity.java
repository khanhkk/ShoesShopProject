package tdc.edu.vn.shoesshop.Toan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Controls.General;
import Models.Account;
import tdc.edu.vn.shoesshop.R;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference();
    Query allAccount = myRef.child("Accounts");
    Query allClient = myRef.child("Clients");
    Query allShop = myRef.child("Shops");
    public static final String BUNDLE = "bundel";
    public ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeForClient.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_login);


        check(this);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        General.setupUI(findViewById(R.id.login), LoginActivity.this);
        //Get Firebase auth instance

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkInternetConenction();
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Loading ......");
                dialog.show();


                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {

                                    // there was an error
                                    if (password.length() < 8) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                    dialog.dismiss();
                                } else {
                                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user.isEmailVerified()) {
                                        myRef.child("Accounts").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Intent intent;
                                                final Bundle bundle = new Bundle();
                                                Account account = dataSnapshot.getValue(Account.class);
                                                if (account.getLevel() == 0) {
                                                    bundle.putString("key", user.getUid());
                                                    intent = new Intent(LoginActivity.this, HomeForShop.class);
                                                    intent.putExtra(BUNDLE, bundle);
                                                    startActivity(intent);
                                                } else {
                                                    bundle.putString("key", user.getUid());
                                                    intent = new Intent(LoginActivity.this, HomeForClient.class);
                                                    intent.putExtra(BUNDLE, bundle);
                                                    startActivity(intent);
                                                }
                                                dialog.dismiss();
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });


                                    } else {
                                        Toast.makeText(LoginActivity.this, "Check your emails", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }

                                    // finish();
                                }

                            }
                        });
            }
        });
        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
//                Intent intent = new Intent(LoginActivity.this, HomeForClient.class);
//                startActivity(intent);
                onBackPressed();
            }
        });
    }

    private static long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 3000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Nhấn back thêm lần nữa để thoát",
                    Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();

        }

    }

    public void check(LoginActivity view) {
        boolean ret = ServerConnectInternet.isConnected();
        String msg;
        if (ret == false) {
            msg = "Thiết bị chưa kết nối internet";
        } else {
            return;
            //msg = "Thiết bị chưa kết nối internet";
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}