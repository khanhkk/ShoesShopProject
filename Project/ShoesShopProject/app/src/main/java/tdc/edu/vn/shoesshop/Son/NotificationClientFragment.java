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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Adapters.NotificationAdapter;
import Models.Notification;
import tdc.edu.vn.shoesshop.R;


public class NotificationClientFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private ArrayList<Notification> list;
    NotificationAdapter recyclerViewAdapter;

    DatabaseReference database;
    FirebaseUser user;

    public NotificationClientFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        list = new ArrayList<>();
        v = inflater.inflate(R.layout.notification_fragment, container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.id_recycleView);
        recyclerViewAdapter = new  NotificationAdapter(getContext(),list);

        database.child("Clients").child(user.getUid()).child("Notifications").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Notification notification = dataSnapshot.getValue(Notification.class);
                if(notification != null) {
                    list.add(0 , notification);
                    recyclerViewAdapter.notifyDataSetChanged();
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

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        list = new ArrayList<>();
//        //list.add(new Notification(R.drawable.lzd,"Lazada Shop","đang giao hàng","13:20 05/02/2018"));
//        //list.add(new Notification(R.drawable.lzd,"Lazada Shop","đã giao hàng","13:20 08/02/2018"));
//        //list.add(new Notification(R.drawable.lzd,"Lazada Shop","đã hủy","13:20 09/02/2018"));
//    }
}
