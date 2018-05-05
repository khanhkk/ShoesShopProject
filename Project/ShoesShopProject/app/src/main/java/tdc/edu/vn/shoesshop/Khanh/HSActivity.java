package tdc.edu.vn.shoesshop.Khanh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import tdc.edu.vn.shoesshop.Thanh.DetailInformationOfProduct;

public class HSActivity extends Fragment {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FloatingActionButton fab, fab_add, fab_edit, fab_delete;
    private Animation amOpen, amClose, amRClockwise, amRanticlockwise;
    Boolean isOpen = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hs_activity, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //floating action button
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab_add = (FloatingActionButton) view.findViewById(R.id.fabAdd);
        fab_edit = (FloatingActionButton) view.findViewById(R.id.fabEdit);
        fab_delete = (FloatingActionButton) view.findViewById(R.id.fabDelete);

        amOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        amClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        amRClockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
        amRanticlockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlockwise);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailInformationOfProduct.class);
                startActivity(intent);
            }
        });

        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PlaceholderFragment.adapter.getCheckedProducts().size() > 0)
                {
                    Intent intent = new Intent(getActivity(), SelectionProductToEditting.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", PlaceholderFragment.adapter.getCheckedProducts());
                    intent.putExtra("data", bundle);
                    getContext().startActivity(intent);
                    Toast.makeText(getActivity(), "thao tac!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Chon san pham de thao tac!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen == true) {
                    fab_add.startAnimation(amClose);
                    fab_edit.startAnimation(amClose);
                    fab_delete.startAnimation(amClose);
                    fab.startAnimation(amRanticlockwise);
                    fab.setClickable(true);
                    isOpen = false;

                    fab_add.setClickable(false);
                    fab_edit.setClickable(false);
                    fab_delete.setClickable(false);
                } else {
                    fab_add.startAnimation(amOpen);
                    fab_edit.startAnimation(amOpen);
                    fab_delete.startAnimation(amOpen);
                    fab.startAnimation(amRClockwise);
                    fab.setClickable(true);
                    isOpen = true;

                    fab_add.setClickable(true);
                    fab_edit.setClickable(true);
                    fab_delete.setClickable(true);
                }
            }
        });

        return view;
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        GridView gridView;
        TextView textView;

        private ArrayList<Product> list = new ArrayList<>();
        public static Adapter_ProductFilter_Shop adapter;
        private ArrayList<String> listTrademark = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.hs_fragment, container, false);
            gridView = (GridView) rootView.findViewById(R.id.grid);
            final TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            textView.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getArguments().getInt(ARG_SECTION_NUMBER) + "", Toast.LENGTH_SHORT).show();

            list.clear();
            listTrademark.clear();
            database.child("Products").orderByChild("shop").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Product product = dataSnapshot.getValue(Product.class);
                    char c = textView.getText().charAt(textView.getText().length()-1);
                    if(c == '1')
                    {
//                        if(product.getSex() == 2)
//                        {
//                            list.add(product);
//                        }
                        list.add(product);
                    }
                    else if(c == '2')
                    {
                        if(product.getSex() == 0)
                        {
                            list.add(product);
                        }
                    }
                    else
                    {
                        if(product.getSex() == 1)
                        {
                            list.add(product);
                        }
                    }
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

        @Override
        public void onStart() {
            super.onStart();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        //ArrayList<Fragment> list = new ArrayList<>();
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
//            Fragment fragment = PlaceholderFragment.newInstance(1);
//            Fragment fragment2 = PlaceholderFragment.newInstance(2);
//            Fragment fragment3 = PlaceholderFragment.newInstance(3);
//
//            list.add(fragment);
//            list.add(fragment2);
//            list.add(fragment3);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;



            //if (getFragmentManager().getFragments().get(position) == null) {

                fragment = PlaceholderFragment.newInstance(position + 1);
//            } else {
//                fragment = getFragmentManager().getFragments().get(position);
//            }

            Toast.makeText(getActivity(), getChildFragmentManager().getFragments().size() + "", Toast.LENGTH_SHORT).show();
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
