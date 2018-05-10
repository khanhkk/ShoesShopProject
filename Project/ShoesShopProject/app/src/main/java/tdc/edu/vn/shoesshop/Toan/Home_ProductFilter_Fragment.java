package tdc.edu.vn.shoesshop.Toan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Adapters.Adapter_ProductFilter_Shop;
import Models.Product;
import tdc.edu.vn.shoesshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_ProductFilter_Fragment extends Fragment {

    private static final String TAG = "ProductFilter";
    GridView gridView;
    Spinner spnThuongHieu;

    //vars

//    private ArrayList<Integer> mImageUrls = new ArrayList<>();
//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<Integer> mrate = new ArrayList<>();
//    private ArrayList<Integer> mCount = new ArrayList<>();
//    private ArrayList<Double> mSells = new ArrayList<>();
//    private ArrayList<Double> mCost = new ArrayList<>();
//    private ArrayList<Boolean> mCheck = new ArrayList<>();

    private ArrayList<Product> list = new ArrayList<>();
    private Adapter_ProductFilter_Shop  adapter;
    private ArrayList<String> listTrademark = new ArrayList<>();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public Home_ProductFilter_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.activity_product_filter_shop, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        spnThuongHieu = (Spinner) view.findViewById(R.id.spin_name) ;

        searchView.clearFocus();
        searchView.setFocusable(false);

        //General.setupUI(view.findViewById(R.id.product_filter), getActivity());

//        final ImageButton menu = (ImageButton) view.findViewById(R.id.popup_menu);
//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Creating the instance of PopupMenu
//                PopupMenu popup = new PopupMenu(getActivity(), menu);
//
//                //Inflating the Popup using xml file
//                popup.getMenuInflater().inflate(R.menu.menu_shop, popup.getMenu());
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        Toast.makeText(getActivity(),"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
//                        return true;
//                    }
//                });
//
//                popup.show();//showing popup menu
//            }
//        });
        //getImages();

        list.clear();
        listTrademark.clear();
        database.child("Products").orderByChild("shop").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                list.add(product);
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

        adapter = new Adapter_ProductFilter_Shop(getContext(), list);
        gridView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        return view;
    }

//    private void getImages() {
//        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
//
//        mImageUrls.add(R.drawable.a);
//        mNames.add("NIKE");
//        mrate.add(2);
//        mCount.add(20);
//        mSells.add(199000.0);
//        mCost.add(3000000.0);
//        mCheck.add(true);
//
//        mImageUrls.add(R.drawable.b);
//        mNames.add("CONVERT");
//        mrate.add(4);
//        mCount.add(20);
//        mSells.add(399000.0);
//        mCost.add(500000.0);
//        mCheck.add(false);
//
//        mImageUrls.add(R.drawable.c);
//        mNames.add("ADIDAS");
//        mrate.add(5);
//        mCount.add(40);
//        mSells.add(499000.0);
//        mCost.add(600000.0);
//        mCheck.add(true);
//
//        mImageUrls.add(R.drawable.a);
//        mNames.add("NIKE");
//        mrate.add(2);
//        mCount.add(20);
//        mSells.add(199000.0);
//        mCost.add(3000000.0);
//        mCheck.add(true);
//
//        mImageUrls.add(R.drawable.b);
//        mNames.add("CONVERT");
//        mrate.add(4);
//        mCount.add(20);
//        mSells.add(399000.0);
//        mCost.add(500000.0);
//        mCheck.add(false);
//
//        mImageUrls.add(R.drawable.c);
//        mNames.add("ADIDAS");
//        mrate.add(5);
//        mCount.add(40);
//        mSells.add(499000.0);
//        mCost.add(600000.0);
//        mCheck.add(false);
//
//        initGridView();
//
//    }

    private void initGridView() {

        Log.d(TAG, "initRecyclerView: init recyclerview");

        //Adapter_ProductFilter_Shop adapter = new Adapter_ProductFilter_Shop( mImageUrls,  mNames,  mrate,  mSells,  mCost,  mCount,  mCheck,getActivity());

        gridView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
    }


}

