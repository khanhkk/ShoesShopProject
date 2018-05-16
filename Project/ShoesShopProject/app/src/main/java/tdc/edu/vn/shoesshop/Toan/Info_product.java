package tdc.edu.vn.shoesshop.Toan;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Adapters.Adapter_info_product;
import Models.BillDetail;
import Models.Product;
import Models.ProductDetail;
import Models.Shop;
import tdc.edu.vn.shoesshop.R;

public class Info_product extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    //private ArrayList<Product> list = new ArrayList<>();
    private ArrayList<Shop> listShop = new ArrayList<>();
    private ArrayAdapter<Integer> adapter;
    private ArrayList<Integer> listSize = new ArrayList<>();
    Button btnXemThem, btnBinhLuan, btnMuaHang;
    TextView tensp, nsx, sells, costs, tencuahang, mota;
    Spinner spinner;
    Bundle bundle, bundleShop;
    public static Product pro;
   // BillAdapter billAdapter;
    ArrayList<BillDetail> list = new ArrayList<BillDetail>();
    String ma = null;
    public static  int type;

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        Info_product.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_product);
        // setPaintFlags(holder.cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        // nhận lại dữ liệu
        final Intent intent = getIntent();
        bundle = intent.getBundleExtra("Bundel");
        if (bundle != null) {
            ma = bundle.getString("id");
        }

//      spinner
        spinner = (Spinner) findViewById(R.id.spinner1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listSize);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinner.setAdapter(adapter);

//        TextView
        tensp = (TextView) findViewById(R.id.tensanpham);
        nsx = (TextView) findViewById(R.id.nsx);
        sells = (TextView) findViewById(R.id.sells);
        costs = (TextView) findViewById(R.id.cost);
        tencuahang = (TextView) findViewById(R.id.nameshop_info);
        mota = (TextView) findViewById(R.id.motasp);

        data();

//        for (Product product : list) {
//            Toast.makeText(this, bundle.getString("id"), Toast.LENGTH_SHORT).show();
//        }

        //get current user
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Button
        btnBinhLuan = (Button) findViewById(R.id.btnBinhLuan);
        btnXemThem = (Button) findViewById(R.id.btnXemThem);
        btnMuaHang = (Button) findViewById(R.id.btnBuyProduct);

        btnXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bundleShop = new Bundle();
                Intent intent = new Intent(Info_product.this, tdc.edu.vn.shoesshop.Bao.MainInfoShop.class);
                Info_product.setType(1);
                startActivity(intent);
            }
        });

        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBilldetail();
                Intent intent = new Intent(Info_product.this, tdc.edu.vn.shoesshop.Toan.HomeForClient.class);
                startActivity(intent);
            }
        });

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(Info_product.this, LoginActivity.class));
                    finish();
                }
            }
        };

        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(Info_product.this, HomeForClient.class);
                startActivity(intent);
            }
        });

        //View Pager
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SlidearDots);
        int a = Integer.parseInt(bundle.getString("count"));
        //Log.d("A: ", String.valueOf(a));
        Adapter_info_product viewPagerAdapter = new Adapter_info_product(this, bundle.getString("id"), a);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);

            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

    }


    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            Info_product.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }

    public void data() {
        database.child("Products").orderByChild("id").equalTo(ma).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Product product = dataSnapshot.getValue(Product.class);
                NumberFormat nf = NumberFormat.getInstance();
                DecimalFormat df = (DecimalFormat) nf;
                df.applyPattern("#,### đ");
                //list.add(product);

                pro = product;
                //String setSell = String.valueOf(product.getSalePrice());
                Log.d("id", bundle.getString("id"));
                tensp.setText(product.getName());
                nsx.setText(product.getTrademark());
                sells.setText(df.format(product.getSalePrice()));
                costs.setText(df.format(product.getListedPrice()));
                costs.setPaintFlags(costs.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                mota.setText(product.getDescription());

//                        Lấy dữ liệu từ Shop
                database.child("Shops").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Shop shop = dataSnapshot.getValue(Shop.class);
                        if (dataSnapshot.getKey().equals(product.getShop())) {
                            // listShop.add(shop);
                            tencuahang.setText(shop.getName());
                            //  bundleShop.putString("keyShop", dataSnapshot.getKey());
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


        database.child("ProductDetails").orderByChild("product").equalTo(bundle.getString("id")).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                if (listSize.size() > 0) {
                    int flag = 0;
                    for (Integer str : listSize) {
                        if (str == productDetail.getSize()) {
                            flag++;
                            break;
                        }
                    }

                    if (flag == 0) {
                        listSize.add(productDetail.getSize());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    listSize.add(productDetail.getSize());
                }
                Log.d("Size", listSize.toString());
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

    @Override
    public boolean isDestroyed() {

        return super.isDestroyed();
    }

    public void addBilldetail() {
        BillDetail billDetail = new BillDetail();
        //billDetail.setCodeOfProduct(product.getId());
        billDetail.setId(database.child("Cart").push().getKey());
        billDetail.setQuantity(1);
        //billDetail.setPrice(product.getSalePrice());
        billDetail.setProduct(pro.getId());
        billDetail.setPrice(pro.getSalePrice());
        list.add(billDetail);
        database.child("Clients").child(user.getUid()).child("Cart").push().setValue(billDetail);
      //  billAdapter.notifyDataSetChanged();
    }
}
