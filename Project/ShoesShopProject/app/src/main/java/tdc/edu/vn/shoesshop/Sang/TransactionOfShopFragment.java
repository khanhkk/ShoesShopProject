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
    CustumAdapterHistory customAdapter;
    ArrayList<String> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = inflater.inflate(R.layout.transaction_of_shop_fragment, container, false);

        lvContact = (ListView) view.findViewById(R.id.listview);

//        ArrayList<contactList22>  arrayList = new ArrayList<>();
//        contactList22 contact1 = new contactList22(null,"Nguyen Van A",);
//        contactList22 contact2 = new contactList22(null,"Nguyen Van B","092xx xxxx");
//        contactList22 contact3 = new contactList22(null,"Nguyen Van C","092xxx xxx");
//        contactList22 contact4 = new contactList22(null,"Nguyen Van D","092xxx xxx");


//        arrayList.add(contact1);
//        arrayList.add(contact2);
//        arrayList.add(contact3);
//        arrayList.add(contact4);

        list.add("Đơn hàng đã giao");
        list.add("Đơn hàng đang + vận chuyển");
        list.add("Đơn hàng đã hủy");
        list.add("đơn hàng chờ xử lí");
      //  customAdapter.notifyDataSetChanged();

         customAdapter = new CustumAdapterHistory(getActivity(), R.layout.listview_layout_history_transaction22,list);
        lvContact.setAdapter(customAdapter);

        return view;
    }

}
