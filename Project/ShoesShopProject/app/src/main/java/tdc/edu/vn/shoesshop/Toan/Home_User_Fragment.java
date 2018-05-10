package tdc.edu.vn.shoesshop.Toan;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Adapters.Adapter_ProductFilter;
import Models.Product;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_User_Fragment extends Fragment {
    private static final String TAG = "ProductFilter";
    GridView gridView;
    Spinner spnThuongHieu, spnGia;
    TabLayout tabLayout;
    TabItem tabItem, tabItem1, tabItem2;
    private ArrayList<Product> list = new ArrayList<>();
    private ArrayList<Product> list1 = new ArrayList<>();
    private ArrayList<Product> listPice = new ArrayList<>();
    private ArrayList<ProductDetail> listdDetails = new ArrayList<>();
    private Adapter_ProductFilter adapter;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public Home_User_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_product_filter, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
        listdDetails.clear();
        data();
        connectAdapter();
//        TabLayout
        tabItem = (TabItem) view.findViewById(R.id.tabItem);
        tabItem1 = (TabItem) view.findViewById(R.id.tabItem1);
        tabItem2 = (TabItem) view.findViewById(R.id.tabItem2);
//        tabItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (Product pro : list) {
//                    if (pro.getSex() == 0)
//                        list1.add(pro);
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });
//        tabItem1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for(Product pro : list){
//                    if(pro.getSex() == 1)
//                        list1.add(pro);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });
//        tabItem2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for(Product pro : list){
//                    if(pro.getSex() == 2)
//                        list1.add(pro);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });
//        spinner
        spnThuongHieu = (Spinner) view.findViewById(R.id.spin_name);
        spnGia = (Spinner) view.findViewById(R.id.spin_price);
//      Loc san pham theo thuong hieu
        spnThuongHieu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list1.clear();
                for (Product pro : list) {
                    if (i == 0) {
                        list1.add(pro);
                    }
                    if ((pro.getTrademark()).equals(spnThuongHieu.getSelectedItem().toString())) {
                        list1.add(pro);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
//      Loc san pham theo gia
        spnGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list1.clear();
                switch (i) {
                    case 0:
                        for (Product pro : list)
                            list1.add(pro);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1: {
                        for (Product pro : list) {
                            if (pro.getSalePrice() <= 1000000)
                                list1.add(pro);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    break;
                    case 2: {
                        for (Product pro : list) {
                            if (pro.getSalePrice() > 1000000)
                                list1.add(pro);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    break;
                }

                //   Toast.makeText(getActivity(), list1.size() + "", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return view;
    }

    //
    public void connectAdapter() {
        adapter = new Adapter_ProductFilter(getContext(), list1, listdDetails);
        gridView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
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
                list.add(product);
                list1.add(product);
                //listPice.add(product);
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