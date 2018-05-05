package tdc.edu.vn.shoesshop.Son;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import Adapters.NotificationClientAdapter;
import Adapters.NotificationShopAdapter;
import Models.Notification;
import tdc.edu.vn.shoesshop.R;


public class NotificationShopFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<Notification> list;
    public NotificationShopFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.notification_fragment, container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.id_recycleView);
        NotificationShopAdapter recyclerViewAdapter = new  NotificationShopAdapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        list = new ArrayList<>();
        list.add(new Notification(R.drawable.gai,"Quỳnh Như","đã đặt hàng","13:20 05/07/2017"));
        list.add(new Notification(R.drawable.gai,"Quỳnh Như","đã hủy đơn hàng","13:20 05/07/2017"));

    }
}
