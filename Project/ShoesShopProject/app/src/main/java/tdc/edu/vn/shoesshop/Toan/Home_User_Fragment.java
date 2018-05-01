package tdc.edu.vn.shoesshop.Toan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import java.util.ArrayList;
import Adapters.Adapter_ProductFilter;
import tdc.edu.vn.shoesshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_User_Fragment extends Fragment {
    private static final String TAG = "ProductFilter";
    GridView gridView;

    //vars
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mrate = new ArrayList<>();
    private ArrayList<Integer> mCount = new ArrayList<>();
    private ArrayList<Double> mSells = new ArrayList<>();
    private ArrayList<Double> mCost = new ArrayList<>();

    public Home_User_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.activity_product_filter, container, false);
        gridView = (GridView) view.findViewById(R.id.grid);
        getImages();
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

        initGridView();

    }

    private void initGridView() {

        Log.d(TAG, "initRecyclerView: init recyclerview");

        Adapter_ProductFilter adapter = new Adapter_ProductFilter(mImageUrls, mNames,mrate,mSells,mCost,mCount,getActivity());
        gridView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
    }


}

