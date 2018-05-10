package tdc.edu.vn.shoesshop.Khanh;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import Adapters.BillAdapter;
import Models.BillDetail;
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

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.cart_fragment, container, false);

        tvMoney = (TextView) view.findViewById(R.id.tvMoney);
        btnThanhToan = (Button) view.findViewById(R.id.btnPay);
        //btnBack = (Button)findViewById(R.id.btnBack);

        //createData();
        database.child("Products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                if(product.getSex() == 1)
                {
                    BillDetail billDetail = new BillDetail();
                    //billDetail.setCodeOfProduct(product.getId());
                    billDetail.setId(database.child("Products").push().getKey());
                    billDetail.setQuantity(1);
                    //billDetail.setPrice(product.getSalePrice());
                    billDetail.setProduct(product);
                    list.add(billDetail);
                    billAdapter.notifyDataSetChanged();

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


        listView = (ListView)view.findViewById(R.id.lvList);
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
                intent = new Intent();
                intent.setClass(getActivity(), ClientInformationAfterOrder.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void createData()
    {
        //Shop shop = new Shop("SH0001", "MiuMiu", "01512151211");
//        Product product = new Product("SP0001", "giay1", 100000, user.getUid());
//        Product product2 = new Product("SP0002", "giay2", 150000, shop.getId());
//        Product product3 = new Product("SP0003", "giay3", 200000, shop.getId());
//        Product product4 = new Product("SP0004", "giay4", 250000, shop.getId());
//        Product product5 = new Product("SP0005", "giay5", 300000, shop.getId());
//        Product product6 = new Product("SP0005", "giay5", 300000, shop.getId());
//
//        BillDetail bd = new BillDetail(1,"HD0001", product, 1);
//        BillDetail bd2 = new BillDetail(1,"HD0001", product2, 1);
//        BillDetail bd3 = new BillDetail(1,"HD0001", product3, 1);
//        BillDetail bd4 = new BillDetail(1,"HD0001", product4, 1);
//        BillDetail bd5 = new BillDetail(1,"HD0001", product5, 1);

//        list.add(bd);
//        list.add(bd2);
//        list.add(bd3);
//        list.add(bd4);
//        list.add(bd5);
    }
}
