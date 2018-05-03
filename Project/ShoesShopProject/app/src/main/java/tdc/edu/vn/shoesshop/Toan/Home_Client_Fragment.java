package tdc.edu.vn.shoesshop.Toan;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import Adapters.RecyclerViewAdapter_Client;
import Models.Product;
import tdc.edu.vn.shoesshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Client_Fragment extends Fragment {

    private static final String TAG = "MainActivity";

    static HashMap<Product, ArrayList<Product>> list = new HashMap<>();
    ArrayList<Product> listParent = new ArrayList<>(), listCopy = new ArrayList<>();

    //vars
//    private ArrayList<String> mNames = new ArrayList<>();
//
//    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mrate = new ArrayList<>();
    private ArrayList<Double> mSells = new ArrayList<>();
    private ArrayList<Double> mCost = new ArrayList<>();
    private ArrayList<Integer> mCount = new ArrayList<>();

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
        getImages();
        initRecyclerView(view, recyclerView);
        initRecyclerView(view, recyclerView1);
        initRecyclerView(view, recyclerView2);
        return view;


    }

    private void getImages() {


        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add(R.drawable.a);
        mNames.add("NIKE");
        mrate.add(2);
        mCount.add(20);
        mSells.add(199000.0);
        mCost.add(3000000.0);

        mImageUrls.add(R.drawable.b);
        mNames.add("CONVERT");
        mrate.add(4);
        mCount.add(20);
        mSells.add(399000.0);
        mCost.add(500000.0);

        mImageUrls.add(R.drawable.c);
        mNames.add("ADIDAS");
        mrate.add(5);
        mCount.add(40);
        mSells.add(499000.0);
        mCost.add(600000.0);

        mImageUrls.add(R.drawable.a);
        mNames.add("NIKE");
        mrate.add(2);
        mCount.add(20);
        mSells.add(199000.0);
        mCost.add(3000000.0);

        mImageUrls.add(R.drawable.b);
        mNames.add("CONVERT");
        mrate.add(4);
        mCount.add(20);
        mSells.add(399000.0);
        mCost.add(500000.0);

        mImageUrls.add(R.drawable.c);
        mNames.add("ADIDAS");
        mrate.add(5);
        mCount.add(40);
        mSells.add(499000.0);
        mCost.add(600000.0);

    }

    private void initRecyclerView(View view, RecyclerView recyclerView) {
        RecyclerViewAdapter_Client adapter = new RecyclerViewAdapter_Client(mImageUrls, mNames, mrate, mSells, mCost, mCount, getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
    }

}
