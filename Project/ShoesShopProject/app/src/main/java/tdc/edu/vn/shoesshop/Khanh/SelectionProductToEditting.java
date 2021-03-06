package tdc.edu.vn.shoesshop.Khanh;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import Adapters.ProductExpandListAdapter;
import Models.Product;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.QuantityManagement;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;

public class SelectionProductToEditting extends AppCompatActivity {

    public static ProductExpandListAdapter adapter;
    ArrayList<Product> products = new ArrayList<>();
    HashMap<Product, ArrayList<ProductDetail>> children = new HashMap<>();
    public static ArrayList<ProductDetail> details = new ArrayList<>();
    ArrayList<String> ListProductId = new ArrayList<>();

    ExpandableListView lvList;
    ImageButton btnBack;
    Intent intent;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_product_to_editting_activity);

        //anh xa
        lvList = (ExpandableListView) findViewById(R.id.lvListProduct);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(SelectionProductToEditting.this, HomeForShop.class);
                startActivity(intent);
            }
        });

        adapter = new ProductExpandListAdapter(SelectionProductToEditting.this, products, children);

        lvList.setAdapter(adapter);

        registerForContextMenu(lvList);
    }

    @Override
    protected void onStart() {
        super.onStart();

        children.clear();
        details.clear();
        products.clear();
        adapter.notifyDataSetChanged();

        if(ListProductId.size() == 0) {
            String product;
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("data");
            if (bundle != null) {
                ListProductId = (ArrayList<String>) bundle.getStringArrayList("list");
            } else if ((product = intent.getStringExtra("pro")) != null) {
                ListProductId.add(product);
            }
        }

        //Toast.makeText(SelectionProductToEditting.this, ListProductId.size() + "", Toast.LENGTH_SHORT).show();
        //if (ListProductId.size() > 0) {
        for (String str : ListProductId) {
            database.child("Products").orderByChild("id").equalTo(str).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    final Product product = dataSnapshot.getValue(Product.class);
                    products.add(product);
                    final ArrayList<ProductDetail> list =  new ArrayList<ProductDetail>();

                    database.child("ProductDetails").orderByChild("product").equalTo(product.getId()).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                            details.add(productDetail);
                            //children.get(product).add(productDetail);
                            list.add(productDetail);
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

                    children.put(product, list);
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
        }
    }
//        } else {
//            String product = intent.getStringExtra("pro");
//            if (product != null) {
//                ListProductId.add(product);
//                database.child("Products").orderByChild("id").equalTo(product).addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        final Product pro = dataSnapshot.getValue(Product.class);
//                        products.add(pro);
//                        children.put(pro, new ArrayList<ProductDetail>());
//                        database.child("ProductDetails").orderByChild("product").equalTo(pro.getId()).addChildEventListener(new ChildEventListener() {
//                            @Override
//                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
//                                details.add(productDetail);
//                                children.get(pro).add(productDetail);
//                            }
//
//                            @Override
//                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                            }
//
//                            @Override
//                            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                            }
//
//                            @Override
//                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        }
//    }


//    @Override
////    protected void onRestoreInstanceState(Bundle savedInstanceState) {
////        super.onRestoreInstanceState(savedInstanceState);
////        if (savedInstanceState != null) {
////            ListProductId = savedInstanceState.getStringArrayList("aaa");
////            Toast.makeText(SelectionProductToEditting.this, ListProductId.size() + "", Toast.LENGTH_SHORT).show();
////            //Log.d("afa", ListProductId.size()+"");
////        }
////    }
////
////    @Override
////    protected void onSaveInstanceState(Bundle outState) {
////        super.onSaveInstanceState(outState);
////        if (ListProductId.size() > 0) {
////            outState.putStringArrayList("aaa", ListProductId);
////        }
////    }

    public void onCreateContextMenu(final ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

            ProductDetail item = (ProductDetail) adapter.getChild(group, child);
            menu.setHeaderTitle("Chọn để thực hiện");

            menu.add(0, R.id.cmSua, 0, "Sua");
            menu.add(0, R.id.cmXoa, 0, "Xoa");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        final int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        final int child = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            final ProductDetail productDetail = (ProductDetail) adapter.getChild(group, child);
            switch (item.getItemId()) {
                case R.id.cmSua:
                    Toast.makeText(SelectionProductToEditting.this, "sua" + productDetail.getProduct(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SelectionProductToEditting.this, QuantityManagement.class);
                    intent.putExtra("detail", productDetail);
                    //Bundle bundle = new Bundle();
                    //bundle.putString("detail", productDetail.getId()+ "");
                    startActivity(intent);
                    break;

                case R.id.cmXoa:
                    Toast.makeText(SelectionProductToEditting.this, "xoa" + productDetail.getProduct(), Toast.LENGTH_SHORT).show();

//                    database.child("ProductDetails").orderByChild("id").equalTo(productDetail.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            //dataSnapshot.getRef().setValue(null);
//
//                            for (DataSnapshot child: dataSnapshot.getChildren()) {
//                                child.getRef().setValue(null);
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SelectionProductToEditting.this);
                    alertDialog.setTitle("Thông báo");
                    alertDialog.setIcon(R.mipmap.ic_launcher);
                    alertDialog.setMessage("Bạn muốn xóa sản phẩm?");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            database.child("ProductDetails").orderByChild("id").equalTo(productDetail.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    //dataSnapshot.getRef().setValue(null);

                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        child.getRef().setValue(null);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            children.get(products.get(group)).remove(child);
                            adapter.notifyDataSetChanged();
                        }
                    });

                    alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    alertDialog.show();
                    break;
            }
        }

        return super.onContextItemSelected(item);
    }

}
