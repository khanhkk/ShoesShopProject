package tdc.edu.vn.shoesshop.Toan;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import Adapters.AdapterComment;
import Adapters.Adapter_info_product;
import Controls.DateTimePicker;
import Models.BillDetail;
import Models.Comments;
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
    private ArrayAdapter<String> adapter1;
    private ArrayList<Integer> listSize = new ArrayList<>();
    private ArrayList<String> listColor = new ArrayList<>();
    private ArrayList<ProductDetail> listProductDetail = new ArrayList<>();
    private ArrayList<Comments> listComment = new ArrayList<>();
    private String key;
    Button btnXemThem, btnMuaHang;
    TextView tensp, nsx, sells, costs, tencuahang, mota;
    ListView lvComment;
    Spinner spinnerColor, spinnerSize;
    Bundle bundle, bundleShop;
    ImageButton btnBinhLuan;
    FloatingActionButton btnComment;
    LinearLayout llComment;
    EditText edtName, edtCcomment;
    private Animation amRClockwise, amRanticlockwise;
    AdapterComment customAdaper;


    public static Product pro;
    // BillAdapter billAdapter;
    ArrayList<BillDetail> list = new ArrayList<BillDetail>();
    String ma = null;
    String mau;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_product);
        check(this);
        //get current user
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Button
        btnBinhLuan = (ImageButton) findViewById(R.id.btnComment);
        btnXemThem = (Button) findViewById(R.id.btnXemThem);
        btnMuaHang = (Button) findViewById(R.id.btnBuyProduct);
        llComment = (LinearLayout) findViewById(R.id.llCommitArea);
        btnComment = (FloatingActionButton) findViewById(R.id.faComment);
        //      spinner
        spinnerColor = (Spinner) findViewById(R.id.spinner2);
        spinnerSize = (Spinner) findViewById(R.id.spinner1);
        //        TextView
        tensp = (TextView) findViewById(R.id.tensanpham);
        nsx = (TextView) findViewById(R.id.nsx);
        sells = (TextView) findViewById(R.id.sells);
        costs = (TextView) findViewById(R.id.cost);
        tencuahang = (TextView) findViewById(R.id.nameshop_info);
        mota = (TextView) findViewById(R.id.motasp);
        edtName = (EditText) findViewById(R.id.tenNguoiBL);
        edtCcomment = (EditText) findViewById(R.id.edtBinhLuan);
        edtName.setText("");
        edtCcomment.setText("");

        lvComment = (ListView) findViewById(R.id.lvBinhLuan);
        lvComment.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        customAdaper = new AdapterComment(Info_product.this, R.layout.item_lv_comment, listComment);
        lvComment.setAdapter(customAdaper);

        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        //View Pager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SlidearDots);

        // setPaintFlags(holder.cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        // nhận lại dữ liệu
        final Intent intent = getIntent();
        bundle = intent.getBundleExtra("Bundel");
        if (bundle != null) {
            ma = bundle.getString("id");
        }

        amRClockwise = AnimationUtils.loadAnimation(Info_product.this, R.anim.rotate_clockwise);
        amRanticlockwise = AnimationUtils.loadAnimation(Info_product.this, R.anim.rotate_anticlockwise);

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listColor);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listSize);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinnerColor.setAdapter(adapter1);
        spinnerSize.setAdapter(adapter);

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mau = listColor.get(position);
                listSize.clear();
                for (ProductDetail productDetail : listProductDetail) {
                    if (productDetail.getColor().equals(mau)) {
                        listSize.add(productDetail.getSize());
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                size = listSize.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        list.clear();
        data();
        btnBinhLuan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (edtCcomment.getText().length() == 0 || edtName.getText().length() == 0) {
                    Toast.makeText(Info_product.this, "Bạn quên nhập Tên hoặc Bình luận", Toast.LENGTH_SHORT).show();
                }else {
                    dataTestComment();
                }

                edtName.requestFocus();
                return false;
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listComment.clear();
                if (llComment.getVisibility() == View.VISIBLE) {
                    llComment.setVisibility(View.GONE);
                    btnComment.startAnimation(amRanticlockwise);
                } else {
                    llComment.setVisibility(View.VISIBLE);
                    btnComment.startAnimation(amRClockwise);

                    database.child("Products").orderByChild("id").equalTo(ma).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            database.child("Products").child(dataSnapshot.getKey()).child("Comments").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    Comments comments = dataSnapshot.getValue(Comments.class);
                                    if (comments != null) {
                                        listComment.add(0, comments);
                                        customAdaper.notifyDataSetChanged();
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


                }
            }
        });

        btnXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bundleShop = new Bundle();
                Intent intent = new Intent(Info_product.this, tdc.edu.vn.shoesshop.Bao.MainInfoShop.class);
                intent.putExtra("shop", pro.getShop());
                startActivity(intent);
            }
        });

        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBilldetail();
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

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                onBackPressed();
            }
        });


        int a = Integer.parseInt(bundle.getString("count"));
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
        database.child("Clients").child(user.getUid()).child("Cart").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BillDetail detail = dataSnapshot.getValue(BillDetail.class);
                if(detail != null) {
                    list.add(detail);
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

        database.child("Products").orderByChild("id").equalTo(ma).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                key = dataSnapshot.getKey();
                pro = dataSnapshot.getValue(Product.class);
                NumberFormat nf = NumberFormat.getInstance();
                DecimalFormat df = (DecimalFormat) nf;
                df.applyPattern("#,### đ");
                //list.add(product);

                //pro = product;
                //String setSell = String.valueOf(product.getSalePrice());
                //Log.d("id", bundle.getString("id"));
                tensp.setText(pro.getName());
                nsx.setText(pro.getTrademark());
                sells.setText(df.format(pro.getSalePrice()));
                costs.setText(df.format(pro.getListedPrice()));
                costs.setPaintFlags(costs.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                mota.setText(pro.getDescription());

//               Lấy dữ liệu từ Shop
                database.child("Shops").orderByKey().equalTo(pro.getShop()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Shop shop = dataSnapshot.getValue(Shop.class);
                        tencuahang.setText(shop.getName());
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

                database.child("ProductDetails").orderByChild("product").equalTo(pro.getId()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                        listProductDetail.add(productDetail);
                        if (listColor.size() > 0) {
                            int flag1 = 0;
                            for (String strCL : listColor) {
                                if (strCL == productDetail.getColor()) {
                                    flag1++;
                                    break;
                                }
                            }

                            if (flag1 == 0) {
                                listColor.add(productDetail.getColor());
                                adapter1.notifyDataSetChanged();
                            }
                        } else {
                            listColor.add(productDetail.getColor());
                            spinnerColor.setSelection(0);
                        }
                        Log.d("Color", String.valueOf(listColor));
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
    }

    @Override
    public boolean isDestroyed() {

        return super.isDestroyed();
    }

    public void addBilldetail() {
        ProductDetail productDetail = null;
        //tim san pham
        for (ProductDetail detail : listProductDetail) {
            if (detail.getColor().equals(mau) && detail.getSize() == size) {
                productDetail = detail;
                break;
            }
        }

        if(productDetail == null)
        {
            Toast.makeText(Info_product.this, "Sản phẩm chưa bán!", Toast.LENGTH_SHORT).show();
            return;
        }

        //kiem tra san pham trong hang
        if (list.size() > 0) {
            for (BillDetail detail : list) {
                if (detail.getDetail().equals(productDetail.getId())) {
                    Toast.makeText(Info_product.this, "Sản phẩm đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }


        //kiem tra so luong san pham
        database.child("ProductDetails").orderByChild("id").equalTo(productDetail.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ProductDetail detail = dataSnapshot.getValue(ProductDetail.class);
                if (detail != null) {
                    if (detail.getQuantity() > 0) {
                        BillDetail billDetail = new BillDetail();
                        billDetail.setId(database.child("Cart").push().getKey());
                        billDetail.setQuantity(1);
                        billDetail.setProduct(pro.getId());
                        billDetail.setDetail(detail.getId());
                        billDetail.setPrice(pro.getSalePrice());
                        billDetail.setShop(pro.getShop());
                        list.add(billDetail);
                        database.child("Clients").child(user.getUid()).child("Cart").push().setValue(billDetail);

                        Intent intent = new Intent(Info_product.this, HomeForClient.class);
                        intent.putExtra("action", "showCart");
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(Info_product.this, "Sản phẩm đã hết mất rồi! huhu...", Toast.LENGTH_SHORT).show();
                    return;
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


    public void dataTestComment() {

        database.child("Products").orderByChild("id").equalTo(ma).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Calendar calendar = Calendar.getInstance();
                Comments comments = new Comments();
                if (edtName.getText() != null && edtCcomment.getText() != null) {
                    comments.setTen(edtName.getText() + "");
                    comments.setThoiGian(DateTimePicker.simpleDateFormat.format(calendar.getTime()));
                    comments.setNoiDung(edtCcomment.getText() + "");
//                listComment.add(0, comments);
//                customAdaper.notifyDataSetChanged();
                    database.child("Products").child(dataSnapshot.getKey()).child("Comments").push().setValue(comments);
                }
                edtCcomment.setText("");
                edtName.setText("");
                //lay thong tin comment
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

    public void check(Info_product view) {
        boolean ret = ServerConnectInternet.isConnected();
        String msg;
        if (ret == false) {
            msg = "Thiết bị chưa kết nối internet";
        } else {
            return;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Info_product.super.onBackPressed();
    }
}
