package tdc.edu.vn.shoesshop.Khanh;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Models.Product;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.DetailInformationOfProduct;

public class HSActivity extends Fragment {
    private Adapters.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FloatingActionButton fab, fab_add, fab_edit, fab_delete;
    private Animation amOpen, amClose, amRClockwise, amRanticlockwise;

    Boolean isOpen = false;
    public static ArrayList<Product> ListProducts = new ArrayList<>();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.hs_activity, container, false);

        mSectionsPagerAdapter = new Adapters.SectionsPagerAdapter(getChildFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer(true, new RotateUpTransformer());
//        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                int pageWidth = view.getWidth();
//                int pageHeight = view.getHeight();
//
//                if (position < -1) { // [-Infinity,-1)
//                    // This page is way off-screen to the left.
//                    view.setAlpha(0);
//                } else if(position <= 1){ // Page to the left, page centered, page to the right
//                    // modify page view animations here for pages in view
//                } else { // (1,+Infinity]
//                    // This page is way off-screen to the right.
//                    view.setAlpha(0);
//                }
//            }
//        });
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

        ListProducts.clear();
        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ListProducts.size() > 0)
                {
                    Intent intent = new Intent(getContext(), SelectionProductToEditting.class);
                    Bundle bundle = new Bundle();
                    //bundle.putSerializable("list", ListProducts);
                    ArrayList<String> list = new ArrayList<>();
                    for(Product product : ListProducts)
                    {
                        list.add(product.getId());
                    }

                    bundle.putStringArrayList("list", list);
                    intent.putExtra("data", bundle);
                    getContext().startActivity(intent);
                    //Toast.makeText(getActivity(), "kk!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Chọn sản phẩm để thao tác!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ListProducts.size() > 0)
                {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("Notification");
                    alertDialog.setIcon(R.mipmap.ic_launcher);
                    alertDialog.setMessage("Bạn muốn xóa thông tin sản phẩm?");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for(Product product : ListProducts)
                            {
                                database.child("ProductDetails").orderByChild("product").equalTo(product.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                                            child.getRef().setValue(null);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                database.child("Products").orderByChild("id").equalTo(product.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                                            child.getRef().setValue(null);
                                            mSectionsPagerAdapter = new Adapters.SectionsPagerAdapter(getChildFragmentManager());
                                            mViewPager.setAdapter(mSectionsPagerAdapter);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    });

                    alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    alertDialog.show();

                }
                else
                {
                    Toast.makeText(getActivity(), "Chọn sản phẩm để thao tác!", Toast.LENGTH_SHORT).show();
                }
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


}

