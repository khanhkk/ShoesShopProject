package tdc.edu.vn.shoesshop.Sang;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.CustumAdapterHistory;
import tdc.edu.vn.shoesshop.R;


public class TransactionOfShopFragment extends Fragment {

    ListView lvContact;
    CustumAdapterHistory adapter;
    ArrayList<String> list ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = inflater.inflate(R.layout.transaction_of_shop_fragment, container, false);

        lvContact = (ListView) view.findViewById(R.id.listview);
        lvContact.setDivider(null);


        list = new ArrayList<>();

        list.add("Đã giao");
        list.add("Đang vận chuyển");
        list.add("Đơn đã hủy");
        list.add("Đơn chờ xử lí");


        CustumAdapterHistory customAdapter = new CustumAdapterHistory(getActivity(), R.layout.listview_layout_history_transaction22,list);

        lvContact.setAdapter(customAdapter);

        return view;
    }

}
