package tdc.edu.vn.shoesshop.Khanh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

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

public class PlaceholderFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    GridView gridView;
    TextView textView;

    private ArrayList<Product> list = new ArrayList<>();
    public static Adapter_ProductFilter_Shop adapter;
    private ArrayList<String> listTrademark = new ArrayList<>();
    //private int mPage;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

   //public PlaceholderFragment (){}

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String ss = HSActivity.name_product.length() + " ---- " + HSActivity.trademark.length() ;
        Toast.makeText(getContext(), ss, Toast.LENGTH_LONG).show();
        Log.d("tag", ss);

        View rootView = inflater.inflate(R.layout.hs_fragment, container, false);
        gridView = (GridView) rootView.findViewById(R.id.grid);
        final TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        textView.setVisibility(View.GONE);

        final char c = textView.getText().charAt(textView.getText().length()-1);
        list.clear();
        listTrademark.clear();
        database.child("Products").orderByChild("shop").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);

//                if(HSActivity.name_product.length() != 0) {
//                    if(HSActivity.trademark.length() != 0) {
//                        if (product.getName().contains(HSActivity.name_product) && product.getTrademark().toUpperCase().equals(HSActivity.trademark)) {
//                            setup(product, c);
//                        }
//                    }
//                    else
//                    {
//                        if (product.getName().contains(HSActivity.name_product)) {
//                            setup(product, c);
//                        }
//                    }
//                }
//                else {
//                    if(HSActivity.trademark.length() != 0) {
//                        if ( product.getTrademark().toUpperCase().equals(HSActivity.trademark)) {
//                            setup(product, c);
//                        }
//                    }
//                    else {
//                        setup(product, c);
//                    }
//                }

                setup(product, c);

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

        return rootView;
    }

    private void setup(Product product, char c)
    {
        if (c == '1') {
            if (product.getSex() == 2) {
                list.add(product);
            }

        } else if (c == '2') {
            if (product.getSex() == 0) {
                list.add(product);
            }
        } else {
            if (product.getSex() == 1) {
                list.add(product);
            }
        }
    }
}
