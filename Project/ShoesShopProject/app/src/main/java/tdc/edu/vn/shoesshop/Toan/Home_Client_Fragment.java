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

import Adapters.RecyclerViewAdapter;
import Models.Product;
import Models.Promotion;
import Models.PromotionsDetail;
import tdc.edu.vn.shoesshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Client_Fragment extends Fragment {

    private static final String TAG = "MainActivity";

    static HashMap<Product,ArrayList<Product>> list = new HashMap<>();
    ArrayList<Product> listParent = new ArrayList<>(), listCopy = new ArrayList<>();

    //vars
    private ArrayList<String> mNames = new ArrayList<>();

    private ArrayList<String> mImageUrls = new ArrayList<>();

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
//        //recyclerView.setLayoutManager(layoutManager);
//
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(llm);
//        getImages();
//        adapter.notifyDataSetChanged();
       getImages();
        initRecyclerView(view, recyclerView);
        initRecyclerView(view, recyclerView1);
        initRecyclerView(view, recyclerView2);
        return view;


    }

    private void getImages() {


        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("199.000 đ");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("199.000 đ");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("199.000 đ");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("199.000 đ");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("199.000 đ");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("199.000 đ");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("199.000 đ");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("199.000 đ");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("199.000 đ");
    }
    private void initRecyclerView(View view, RecyclerView recyclerView) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
    }

}
