package tdc.edu.vn.shoesshop.Toan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import Adapters.RecyclerViewAdapter_Client;
import Models.Product;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Client_Fragment extends Fragment {

    private static final String TAG = "MainActivity";

    static HashMap<Product, ArrayList<Product>> list = new HashMap<>();
    ArrayList<Product> listParent = new ArrayList<>(), listCopy = new ArrayList<>();

    private ArrayList<Product> list1 = new ArrayList<>();
    private ArrayList<ProductDetail> listdDetails = new ArrayList<>();
    private RecyclerViewAdapter_Client adapter;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public Home_Client_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.home_layout_activity, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView1);
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView2);
        listdDetails.clear();
        data();
        adapter = new RecyclerViewAdapter_Client(getContext(), list1, listdDetails);
        initRecyclerView(recyclerView);
        initRecyclerView(recyclerView1);
        initRecyclerView(recyclerView2);
        return view;
    }


    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
    }

    public void data() {
        database.child("ProductDetails").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                listdDetails.add(productDetail);
                adapter.notifyDataSetChanged();
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
        database.child("Products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                list1.add(product);
                adapter.notifyDataSetChanged();
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
