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


public class NotificationShopFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;

    public static ArrayList<Notification> list;
    NotificationAdapter recyclerViewAdapter;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public NotificationShopFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        list = new ArrayList<>();
//        new LoadNotify().execute();
//        Log.d("s", list.size() + "");

        v = inflater.inflate(R.layout.notification_fragment, container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.id_recycleView);
        list = new ArrayList<>();

        database.child("Shops").child(user.getUid()).child("Notifications").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Notification notify = dataSnapshot.getValue(Notification.class);
                list.add(notify);
                recyclerViewAdapter.notifyDataSetChanged();
                //Toast.makeText(getContext(), notify.getClient(), Toast.LENGTH_SHORT).show();
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

        recyclerViewAdapter = new NotificationAdapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

//    public class LoadNotify extends AsyncTask<Void ,Integer , ArrayList<Notification>>
//    {
//        @Override
//        protected ArrayList<Notification> doInBackground(Void... voids) {
//            final ArrayList<Notification> list = new ArrayList<>();
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//            //database.child("Shops").child(user.getUid()).child("Notifications").
//
//            database.child("Shops").child(user.getUid()).child("Notifications").addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    Notification notify = dataSnapshot.getValue(Notification.class);
//                    list.add(notify);
//                    //Toast.makeText(getContext(), notify.getClient(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//
//            return list;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Notification> list) {
//            super.onPostExecute(list);
//            Log.d("afdasfa", list.size() + "");
//            NotificationShopFragment.list = list;
//        }
//    }
}
