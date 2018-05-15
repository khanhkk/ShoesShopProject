package tdc.edu.vn.shoesshop.Khanh;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

import Adapters.BillAdapter;
import Controls.DateTimePicker;
import Models.Bill;
import Models.BillDetail;
import Models.Client;
import Models.Notification;
import Models.Product;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.ClientInformationAfterOrder;

/**
 * Created by kk on 05/04/2018.
 */

public class Cart extends Fragment {

    ListView listView;
    BillAdapter billAdapter;
    ArrayList<BillDetail> list = new ArrayList<BillDetail>();
    public static TextView tvMoney;
    Button btnThanhToan;
    Intent intent;
    final ArrayList<String> ListShop = new ArrayList<>();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.cart_fragment, container, false);

        tvMoney = (TextView) view.findViewById(R.id.tvMoney);
        btnThanhToan = (Button) view.findViewById(R.id.btnPay);

//        database.child("Products").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Product product = dataSnapshot.getValue(Product.class);
//                if(product.getSex() == 2)
//                {
//                    BillDetail billDetail = new BillDetail();
//                    //billDetail.setCodeOfProduct(product.getId());
//                    billDetail.setId(database.child("Cart").push().getKey());
//                    billDetail.setQuantity(1);
//                    //billDetail.setPrice(product.getSalePrice());
//                    billDetail.setProduct(product.getId());
//                    billDetail.setPrice(product.getSalePrice());
//                    list.add(billDetail);
//                    database.child("Clients").child(user.getUid()).child("Cart").push().setValue(billDetail);
//                    billAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        database.child("Clients").child(user.getUid()).child("Cart").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BillDetail bill = dataSnapshot.getValue(BillDetail.class);
                list.add(bill);
                billAdapter.notifyDataSetChanged();
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

        listView = (ListView) view.findViewById(R.id.lvList);
        billAdapter = new BillAdapter(getActivity(), R.layout.bill_item, list);
        listView.setAdapter(billAdapter);

//        double money = 0;
//        for(BillDetail item : list)
//        {
//            money += (item.getQuantity() * item.getProduct().getSalePrice());
//            tvMoney.setText(money + "");
//        }

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListShop.clear();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Thông báo");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Địa chỉ trong hóa đơn được lấy từ đâu?");

                alertDialog.setPositiveButton("Mặc định", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.child("Clients").child(user.getUid()).child("Cart").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                final BillDetail billDetail = dataSnapshot.getValue(BillDetail.class);
                                database.child("Products").orderByChild("id").equalTo(billDetail.getProduct()).addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        Product product = dataSnapshot.getValue(Product.class);
                                        if (list.size() == 0) {
                                            UpBillFirebase(product.getShop());
                                            ListShop.add(product.getShop());
                                        } else {
                                            int check = 0;
                                            for (String ss : ListShop) {
                                                if (ss.equals(product.getShop())) {
                                                    check++;
                                                    break;
                                                }
                                            }
                                            if (check == 0) {
                                                UpBillFirebase(product.getShop());
                                                ListShop.add(product.getShop());
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
                                database.child("Clients").child(user.getUid()).child("Cart").setValue(null);

                                Toast.makeText(getContext(), "Tạo đơn hàng thành công!", Toast.LENGTH_SHORT).show();
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
                });

                alertDialog.setNegativeButton("Nhập mới", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        intent = new Intent();
                        intent.setClass(getActivity(), ClientInformationAfterOrder.class);
                        startActivity(intent);
                    }
                });
                 alertDialog.show();
            }
        });
        return view;
    }

    private void UpBillFirebase(final String shop) {
        database.child("Clients").orderByKey().equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Client client = dataSnapshot.getValue(Client.class);

                //them hoa don cho shop
                Bill bill = new Bill();
                bill.setAddress(client.getAddress());
                bill.setEmail(client.getEmail());
                bill.setNameClient(client.getName());
                bill.setPhone(client.getPhone());
                bill.setShop(shop);
                bill.setStatus(0);
                Calendar calendar = Calendar.getInstance();
                bill.setTime(DateTimePicker.simpleDateFormat.format(calendar.getTime()));
                bill.setId(database.child("Clients").child(user.getUid()).child("Transactions").push().getKey());

                database.child("Clients").child(user.getUid()).child("Transactions").push().setValue(bill);
                database.child("Shops").child(shop).child("Transactions").push().setValue(bill);

                //them notify cho shop
                Notification notification = new Notification();
                notification.setClient(user.getUid());
                notification.setHoatdong(client.getName() + Notification.STR_DAT_HANG);
                notification.setStatus(false);
                if(client.getImages() != null)
                {
                    notification.setHinh(client.getImages());
                }
                notification.setThoiGian(DateTimePicker.simpleDateFormat.format(calendar.getTime()));
                notification.setBill(bill.getId());

                database.child("Clients").child(shop).child("Notifications").push().setValue(notification);
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
