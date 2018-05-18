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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import Adapters.BillAdapter;
import Controls.DateTimePicker;
import Models.Bill;
import Models.BillDetail;
import Models.Client;
import Models.Notification;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.ClientInformationAfterOrder;
import tdc.edu.vn.shoesshop.Toan.Home_User_Fragment;

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
    Client client;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.cart_fragment, container, false);

        tvMoney = (TextView) view.findViewById(R.id.tvMoney);
        btnThanhToan = (Button) view.findViewById(R.id.btnPay);

        //lay danh sach sp trong gio hang
        list.clear();
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

        //adapter of list view
        listView = (ListView) view.findViewById(R.id.lvList);
        billAdapter = new BillAdapter(getActivity(), R.layout.bill_item, list);
        listView.setAdapter(billAdapter);

        //thong tin khach hang
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

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra so luong san pham trong gio hang
                if (list.size() > 0) {
                    final ArrayList<String> listShop = new ArrayList<>();
                    final HashMap<String, ArrayList<BillDetail>> listBill = new HashMap<>();

                    //dung thong tin mac dinh de dat hang
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Thông báo");
                    alertDialog.setIcon(R.mipmap.ic_launcher);
                    alertDialog.setMessage("Địa chỉ trong hóa đơn được lấy từ đâu?");
                    alertDialog.setPositiveButton("Mặc định", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (BillDetail billDetail : list) {
                                if (listShop.size() == 0) {
                                    ArrayList<BillDetail> list2 = new ArrayList<>();
                                    list2.add(billDetail);
                                    listBill.put(billDetail.getShop(), list2);
                                    listShop.add(billDetail.getShop());
                                } else {
                                    int test = 0;
                                    for (String s : listShop) {
                                        if (s.equals(billDetail.getShop())) {
                                            test++;
                                            listBill.get(s).add(billDetail);
                                            break;
                                        }
                                    }
                                    if (test == 0) {
                                        ArrayList<BillDetail> list2 = new ArrayList<>();
                                        list2.add(billDetail);
                                        listBill.put(billDetail.getShop(), list2);
                                        listShop.add(billDetail.getShop());
                                    }
                                }
                            }

                            for (int a = 0; a < listShop.size(); a++) {
                                UpBillFirebase(listShop.get(a), listBill.get(listShop.get(a)));
                            }

                            //thong bao va lam moi gio hang
                            Toast.makeText(getContext(), "Tạo đơn hàng thành công!", Toast.LENGTH_SHORT).show();
                            database.child("Clients").child(user.getUid()).child("Cart").setValue(null);
                            list.clear();
                            billAdapter.notifyDataSetChanged();

                            //tru so luong san pham cua shop
                            for(final BillDetail detail : list) {
                                database.child("ProductDetails").orderByChild("id").equalTo(detail.getDetail()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                                        productDetail.setQuantity(productDetail.getQuantity() - detail.getQuantity());
                                        for(DataSnapshot child : dataSnapshot.getChildren())
                                        {
                                            child.getRef().setValue(productDetail);
                                            break;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new Home_User_Fragment()).commit();
                        }
                    });

                    // nhap thong tin khac voi thong tin dang ky
                    alertDialog.setNegativeButton("Nhập mới", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            intent = new Intent();
                            intent.setClass(getActivity(), ClientInformationAfterOrder.class);
                            startActivity(intent);
                        }
                    });
                    alertDialog.show();
                } else {
                    Toast.makeText(getContext(), "Giỏ hàng đang trống. Không thể thanh toán!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    //update hoa don len firebase
    private void UpBillFirebase(final String shop, final ArrayList<BillDetail> details) {
        //them hoa don cho shop
        final Bill bill = new Bill();
        bill.setAddress(client.getAddress());
        bill.setEmail(client.getEmail());
        bill.setNameClient(client.getName());
        bill.setPhone(client.getPhone());
        bill.setClient_id(user.getUid());
        bill.setShop(shop);
        bill.setStatus(0);
        Calendar calendar = Calendar.getInstance();
        bill.setTime(DateTimePicker.simpleDateFormat.format(calendar.getTime()));
        bill.setId(database.child("Clients").child(user.getUid()).push().getKey());

        String key = database.child("Clients").child(user.getUid()).child("Transactions").push().getKey();
        database.child("Clients").child(user.getUid()).child("Transactions").child(key).setValue(bill);
        String keys = database.child("Shops").child(shop).child("Transactions").push().getKey();
        database.child("Shops").child(shop).child("Transactions").child(keys).setValue(bill);

        //them san pham vao hoa don
        for (BillDetail detail : details) {
            database.child("Clients").child(user.getUid()).child("Transactions").child(key).child("Details").push().setValue(detail);
        }

        for (BillDetail detail : details) {
            database.child("Shops").child(shop).child("Transactions").child(keys).child("Details").push().setValue(detail);
        }

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

        database.child("Shops").child(shop).child("Notifications").push().setValue(notification);
    }

}
