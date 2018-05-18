package tdc.edu.vn.shoesshop.Son;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Adapters.OrderShopAdapter;
import Models.Bill;
import Models.BillDetail;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ListOder;

;

public class OrderInformationForShop extends AppCompatActivity {
    ListView listView;
    ImageButton back;
    Button vanchuyen;
    TextView tinhtrang;
    public static TextView tvTotal;
    Intent intent;
    ArrayList<BillDetail> ListDetail;
    OrderShopAdapter adapter;
    String bill_id;
    TextView tvCode, tvClient, tvAddress, tvPhone, tvEmail;
    Bill bill;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    NumberFormat nf = NumberFormat.getInstance();
    DecimalFormat df = (DecimalFormat) nf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_information_for_shop_activity);
        df.applyPattern("#,### đ");

        back = (ImageButton) findViewById(R.id.btnBack);
        tvTotal = (TextView) findViewById(R.id.tvMoney);
        listView = (ListView) findViewById(R.id.lv_item);
        tinhtrang = (TextView) findViewById(R.id.txt_tinhtrang);
        vanchuyen = (Button) findViewById(R.id.id_vanchuyen);
        tvCode = (TextView) findViewById(R.id.txt_mahoadon);
        tvAddress = (TextView) findViewById(R.id.txt_diachinhanhang);
        tvClient = (TextView) findViewById(R.id.txt_nguoidat);
        tvPhone = (TextView) findViewById(R.id.txt_sdt);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        //arritem = new ArrayList<OrderShop>();
        ListDetail = new ArrayList<>();

        intent = getIntent();
        bill_id = intent.getStringExtra("bill");
        if (bill_id != null) {
            database.child("Shops").child(user.getUid()).child("Transactions").orderByChild("id").equalTo(bill_id).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    bill = dataSnapshot.getValue(Bill.class);
                    if (bill != null) {
                        tvCode.setText(bill_id);
                        tvAddress.setText(bill.getAddress());
                        tvClient.setText(bill.getNameClient());
                        tvPhone.setText(bill.getPhone());
                        tvEmail.setText(bill.getEmail());
                        vanchuyen.setVisibility(View.GONE);
                        if (bill.getStatus() == 0) {
                            tinhtrang.setText("Đang chờ xử lý");
                            vanchuyen.setVisibility(View.VISIBLE);
                        } else if (bill.getStatus() == 1) {
                            vanchuyen.setVisibility(View.GONE);
                            tinhtrang.setText("Đang vận chuyển");
                        } else if (bill.getStatus() == -1) {
                            vanchuyen.setVisibility(View.GONE);
                            tinhtrang.setText("Đã hủy");
                        } else if (bill.getStatus() == 2) {
                            vanchuyen.setVisibility(View.GONE);
                            tinhtrang.setText("Đã giao dịch");
                        }

                        database.child("Shops").child(user.getUid()).child("Transactions").child(dataSnapshot.getKey()).child("Details").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                BillDetail billDetail = dataSnapshot.getValue(BillDetail.class);
                                ListDetail.add(billDetail);
                                adapter.notifyDataSetChanged();
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

        adapter = new OrderShopAdapter(OrderInformationForShop.this, R.layout.order_information_for_shop_activity_custom, ListDetail);
        listView.setAdapter(adapter);

        vanchuyen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInformationForShop.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Bạn muốn chuyển trạng thái đơn hàng này?");
                alertDialog.setPositiveButton("Thay đổi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (vanchuyen.getText().equals("Vận chuyển")) {
                            tinhtrang.setText("Đang vận chuyển");
                            vanchuyen.setBackground(getDrawable(R.drawable.button2_style));
                            vanchuyen.setText("Hủy");
                            vanchuyen.setVisibility(View.INVISIBLE);
                            database.child("Shops").child(user.getUid()).child("Transactions").orderByChild("id").equalTo(bill.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        child.getRef().child("status").setValue(1);
                                        break;
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            database.child("Clients").child(bill.getClient_id()).child("Transactions").orderByChild("id").equalTo(bill.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        child.getRef().child("status").setValue(1);
                                        break;
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                    }
                });

                alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.show();
            }
        });

        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderInformationForShop.this, ListOder.class);
                startActivity(intent);
            }
        });
    }
}
