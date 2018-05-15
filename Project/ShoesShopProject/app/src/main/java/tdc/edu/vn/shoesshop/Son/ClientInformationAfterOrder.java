package tdc.edu.vn.shoesshop.Son;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import Controls.DateTimePicker;
import Models.Bill;
import Models.BillDetail;
import Models.Product;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;

public class ClientInformationAfterOrder extends AppCompatActivity {
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputHoten;
    private TextInputLayout textInputSdt;
    private TextInputLayout textInputDiachi;


    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
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
                Intent intent = new Intent(ClientInformationAfterOrder.this, HomeForClient.class);
                Bundle bundle = new Bundle();
                bundle.putInt("chuyen", 1);
                intent.putExtra("chuyen", bundle);
                startActivity(intent);
            }
            }
        );

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

        final ArrayList<Bill> list = new ArrayList<>();
        database.child("Clients").child(user.getUid()).child("Cart").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final BillDetail billDetail = dataSnapshot.getValue(BillDetail.class);
                //final String[] shops = new String[]{};
                database.child("Products").orderByChild("id").equalTo(billDetail.getProduct()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Product product = dataSnapshot.getValue(Product.class);
                        if(list.size() == 0)
                        {
                            UpBillFirebase(product.getShop(), list);
                        }
                        else
                        {
                            int check = 0;
                            for(Bill bill : list)
                            {
                                if(bill.getShop().equals(product.getShop()))
                                {
                                    check ++;
                                    break;
                                }
                            }
                            if(check == 0)
                            {
                                UpBillFirebase(product.getShop(), list);
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //clear gio hang
                //database.child("Clients").child(user.getUid()).child("Cart").setValue(null);

                Intent intent = new Intent(ClientInformationAfterOrder.this, HomeForClient.class);
                startActivity(intent);

                Toast.makeText(ClientInformationAfterOrder.this, "Tạo đơn hàng thành công!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //clear gio hang
        //database.child("Clients").child(user.getUid()).child("Cart").setValue(null);

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

    private void UpBillFirebase(String shop, ArrayList<Bill> list)
    {
        Bill bill = new Bill();
        bill.setAddress(textInputDiachi.getEditText().getText().toString().trim());
        bill.setEmail(textInputEmail.getEditText().getText().toString().trim());
        bill.setNameClient(textInputHoten.getEditText().getText().toString().trim());
        bill.setPhone(textInputSdt.getEditText().getText().toString().trim());
        bill.setShop(shop);
        Calendar calendar = Calendar.getInstance();
        bill.setTime(DateTimePicker.simpleDateFormat.format(calendar.getTime()));
        list.add(bill);
        database.child("Clients").child(user.getUid()).child("Transactions").push().setValue(bill);
        database.child("Shops").child(shop).child("Transactions").push().setValue(bill);
    }
}
