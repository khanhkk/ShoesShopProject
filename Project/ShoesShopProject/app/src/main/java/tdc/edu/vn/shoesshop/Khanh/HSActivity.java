package tdc.edu.vn.shoesshop.Khanh;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Models.Product;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.DetailInformationOfProduct;

public class HSActivity extends Fragment implements SearchView.OnQueryTextListener {
    private Adapters.SectionsPagerAdapter mSectionsPagerAdapter;
    private ArrayAdapter<String> adapter;

    private ViewPager mViewPager;
    private FloatingActionButton fab, fab_add, fab_edit, fab_delete;
    private Animation amOpen, amClose, amRClockwise, amRanticlockwise;
    private SearchView svSearch;
    private Spinner spnTradeMark;

    Boolean isOpen = false;
    public static ArrayList<Product> ListProducts = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    public static ProgressDialog dialog;
    ArrayList<String> ListTradeMark = new ArrayList<>();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    LoadData loadData;

    public static String trademark = "";
    public static String name_product = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ......");
        dialog.show();
        new LoadData().execute();

        final View view = inflater.inflate(R.layout.hs_activity, container, false);

        svSearch = (SearchView) view.findViewById(R.id.searchView) ;
        spnTradeMark = (Spinner) view.findViewById(R.id.spin_name) ;
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        //floating action button
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab_add = (FloatingActionButton) view.findViewById(R.id.fabAdd);
        fab_edit = (FloatingActionButton) view.findViewById(R.id.fabEdit);
        fab_delete = (FloatingActionButton) view.findViewById(R.id.fabDelete);

        mSectionsPagerAdapter = new Adapters.SectionsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
       // mViewPager.setPageTransformer(true, new RotateUpTransformer());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        svSearch.setOnQueryTextListener(this);
        mViewPager.setPageTransformer(true, new RotateUpTransformer());

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
                    ArrayList<String> list = new ArrayList<>();
                    for(Product product : ListProducts)
                    {
                        list.add(product.getId());
                    }

                    bundle.putStringArrayList("list", list);
                    intent.putExtra("data", bundle);
                    getContext().startActivity(intent);
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
                                products.remove(product);
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

        //chang status floating button
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

        ListTradeMark.clear();
        database.child("Products").orderByChild("shop").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                if(ListTradeMark.size() > 0)
                {
                    int flag = 0;
                    for(int i = 1 ; i < ListTradeMark.size(); i++)
                    {
                        String str = ListTradeMark.get(i);
                        if(str.toLowerCase().equals(product.getTrademark().toLowerCase()))
                        {
                            flag++;
                            break;
                        }
                    }

                    if(flag == 0)
                    {
                        ListTradeMark.add(product.getTrademark().toUpperCase());
                        adapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    ListTradeMark.add("All");
                    adapter.notifyDataSetChanged();
                }
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

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ListTradeMark);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTradeMark.setAdapter(adapter);
        spnTradeMark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0) {
                    trademark = ListTradeMark.get(i);
                    mSectionsPagerAdapter = new Adapters.SectionsPagerAdapter(getChildFragmentManager());
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                }
                else
                {
                    trademark = "";
                    mSectionsPagerAdapter = new Adapters.SectionsPagerAdapter(getChildFragmentManager());
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Toast.makeText(getContext(), newText + "--", Toast.LENGTH_SHORT).show();
        name_product = newText.trim();
        mSectionsPagerAdapter = new Adapters.SectionsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        return true;
    }


    public class LoadData extends AsyncTask<Void ,Integer , ArrayList<Product>>
    {
        @Override
        protected ArrayList<Product> doInBackground(Void... voids) {

            final ArrayList<Product> list = new ArrayList<>();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();

            database.child("Products").orderByChild("shop").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Product product = dataSnapshot.getValue(Product.class);
                    list.add(product);
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

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<Product> products) {
            super.onPostExecute(products);
            Log.d("afdasf", products.size() + "");
            HSActivity.products = products;
            HSActivity.dialog.dismiss();
        }
    }
}

