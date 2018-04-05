package tdc.edu.vn.shoesshop;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.BillAdapter;
import Models.BillDetail;
import Models.Product;
import Models.Shop;

/**
 * Created by kk on 05/04/2018.
 */

public class Cart extends Fragment {

    ListView listView;
    BillAdapter billAdapter;
    ArrayList<BillDetail> list = new ArrayList<BillDetail>();
    public static TextView tvMoney;
    Button btnThanhToan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.cart_fragment, container, false);

        tvMoney = (TextView) view.findViewById(R.id.tvMoney);
        btnThanhToan = (Button) view.findViewById(R.id.btnPay);
        //btnBack = (Button)findViewById(R.id.btnBack);

        createData();

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
//                Intent intent = new Intent();
//                intent.setClass(Cart.this, ListProduct.class);
//                startActivity(intent);
            }
        });
        return view;
    }

    public void createData()
    {
        Shop shop = new Shop("SH0001", "MiuMiu", "01512151211");
        Product product = new Product("SP0001", "giay1", 100000, shop);
        Product product2 = new Product("SP0002", "giay2", 150000, shop);
        Product product3 = new Product("SP0003", "giay3", 200000, shop);
        Product product4 = new Product("SP0004", "giay4", 250000, shop);
        Product product5 = new Product("SP0005", "giay5", 300000, shop);

        BillDetail bd = new BillDetail(1,"HD0001", product, 1);
        BillDetail bd2 = new BillDetail(1,"HD0001", product2, 1);
        BillDetail bd3 = new BillDetail(1,"HD0001", product3, 1);
        BillDetail bd4 = new BillDetail(1,"HD0001", product4, 1);
        BillDetail bd5 = new BillDetail(1,"HD0001", product5, 1);

        list.add(bd);
        list.add(bd2);
        list.add(bd3);
        list.add(bd4);
        list.add(bd5);
    }
}
