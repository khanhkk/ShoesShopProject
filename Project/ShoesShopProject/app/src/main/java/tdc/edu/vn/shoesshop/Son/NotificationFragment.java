package tdc.edu.vn.shoesshop.Son;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.NotificationClientAdapter;
import Models.Notification;
import tdc.edu.vn.shoesshop.R;


public class NotificationFragment extends Fragment {

    //ImageButton back;
    ListView listView;
    ArrayList<Notification> arritem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = null;
        view = inflater.inflate(R.layout.notification_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.lv_notifiC);
        arritem = new ArrayList<Notification>();

        arritem.add(new Notification(R.mipmap.shop, "GoShop", "đang vận chuyển đơn hàng của bạn", "11/5/2017"));
        arritem.add(new Notification(R.mipmap.giay, "GâugâuShop", "đang có khuyến mãi", "11/5/2017"));

        NotificationClientAdapter adapter = new NotificationClientAdapter(getActivity(), R.layout.notification_client_layout_custom, arritem);
        listView.setAdapter(adapter);

//        back = (ImageButton) view.findViewById(R.id.btnBack);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), HomeForClient.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }
}
