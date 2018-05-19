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
import java.util.HashMap;

import Controls.DateTimePicker;
import Models.Bill;
import Models.BillDetail;
import Models.Client;
import Models.Notification;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;

public class ClientInformationAfterOrder extends AppCompatActivity {
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputHoten;
    private TextInputLayout textInputSdt;
    private TextInputLayout textInputDiachi;

    private Client client;
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

        database.child("Clients").orderByKey().equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                client = dataSnapshot.getValue(Client.class);
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

    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Không thể để trống");
            return false;
        } else {
            if (!emailInput.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                textInputEmail.setError("Email không đúng !");
                return false;
            } else {
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
            if (sdtInput.length() != 10 && sdtInput.length() != 11) {
                textInputSdt.setError("Số điện thoại chưa đúng !");
                return false;
            } else {
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
        final HashMap<String, ArrayList<String>> listBill = new HashMap<>();

        database.child("Clients").child(user.getUid()).child("Cart").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final BillDetail billDetail = dataSnapshot.getValue(BillDetail.class);
                if (list.size() == 0) {
                    UpBillFirebase(list, billDetail, listBill);
                } else {
                    int check = 0;
                    for (Bill bill : list) {
                        if (bill.getShop().equals(billDetail.getShop())) {
                            check++;
                            String kc = listBill.get(bill.getShop()).get(0);
                            String ks = listBill.get(bill.getShop()).get(1);

                            database.child("Clients").child(user.getUid()).child("Transactions").child(kc).child("Details").push().setValue(billDetail);

                            database.child("Shops").child(billDetail.getShop()).child("Transactions").child(ks).child("Details").push().setValue(billDetail);

                            break;
                        }
                    }
                    if (check == 0) {
                        UpBillFirebase(list, billDetail, listBill);
                    }
                }

                database.child("ProductDetails").orderByChild("id").equalTo(billDetail.getDetail()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                        if(productDetail != null) {
                            productDetail.setQuantity(productDetail.getQuantity() - billDetail.getQuantity());
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                child.getRef().setValue(productDetail);
                                break;
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

        Intent intent = new Intent(ClientInformationAfterOrder.this, HomeForClient.class);
        startActivity(intent);
        Toast.makeText(ClientInformationAfterOrder.this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();

        //clear gio hang
        database.child("Clients").child(user.getUid()).child("Cart").setValue(null);
    }

    private void UpBillFirebase(ArrayList<Bill> list, BillDetail detail , HashMap<String, ArrayList<String>> bills) {
        Bill bill = new Bill();
        bill.setAddress(textInputDiachi.getEditText().getText().toString().trim());
        bill.setEmail(textInputEmail.getEditText().getText().toString().trim());
        bill.setNameClient(textInputHoten.getEditText().getText().toString().trim());
        bill.setPhone(textInputSdt.getEditText().getText().toString().trim());
        bill.setClient_id(user.getUid());
        bill.setShop(detail.getShop());
        bill.setStatus(0);
        Calendar calendar = Calendar.getInstance();
        bill.setTime(DateTimePicker.simpleDateFormat.format(calendar.getTime()));
        bill.setId(database.child("Clients").child(user.getUid()).push().getKey());
        list.add(bill);

        String key = database.child("Clients").child(user.getUid()).child("Transactions").push().getKey();
        database.child("Clients").child(user.getUid()).child("Transactions").child(key).setValue(bill);
        String keys = database.child("Shops").child(detail.getShop()).child("Transactions").push().getKey();
        database.child("Shops").child(detail.getShop()).child("Transactions").child(keys).setValue(bill);

        ArrayList<String> ss = new ArrayList<>();
        ss.add(key);
        ss.add(keys);
        bills.put(detail.getShop(), ss);

        database.child("Clients").child(user.getUid()).child("Transactions").child(key).child("Details").push().setValue(detail);

        database.child("Shops").child(detail.getShop()).child("Transactions").child(keys).child("Details").push().setValue(detail);

        //them notify cho shop
        Notification notification = new Notification();
        notification.setClient(user.getUid());
        notification.setHoatdong(client.getName() + Notification.STR_DAT_HANG);
        notification.setStatus(false);
        if (client.getImages() != null) {
            notification.setHinh(client.getImages());
        }
        notification.setThoiGian(DateTimePicker.simpleDateFormat.format(calendar.getTime()));
        notification.setBill(bill.getId());

        database.child("Shops").child(detail.getShop()).child("Notifications").push().setValue(notification);
    }
}
