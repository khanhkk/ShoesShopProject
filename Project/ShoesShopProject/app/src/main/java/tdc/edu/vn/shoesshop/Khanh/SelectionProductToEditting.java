package tdc.edu.vn.shoesshop.Khanh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    ArrayList<ProductDetail> details = new ArrayList<>();

    ExpandableListView lvList;
    Button btnFinish;
    ImageButton btnBack;
    Intent intent;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_product_to_editting_activity);



        lvList = (ExpandableListView) findViewById(R.id.lvListProduct);
        btnFinish = (Button) findViewById(R.id.btnFinishEdition);
        btnBack = (ImageButton) findViewById(R.id.btnBack);


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(SelectionProductToEditting.this, HomeForShop.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(SelectionProductToEditting.this, HomeForShop.class);
                startActivity(intent);
            }
        });

//        creatList();
//
//            for(Product product: products)
//            {
//                String s = database.child("Products").push().getKey();
//                product.setId(s);
//                database.child("Products").push().setValue(product);
//
//                for (ProductDetail productDetail : details)
//                {
//                    String s2 = database.child("ProductDetails").push().getKey();
//                    productDetail.setId(s2);
//                    productDetail.setProduct(s);
//                    database.child("ProductDetails").push().setValue(productDetail);
//                }
//        }

        children.clear();
        children.clear();
        details.clear();
        products.clear();

        database.child("ProductDetails").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                details.add(productDetail);
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


        database.child("Products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                products.add(product);
                if(details.size() > 0)
                {
                    ArrayList<ProductDetail> list = new ArrayList<>();
                    for(ProductDetail productDetail : details)
                    {
                        if(productDetail.getProduct().equals(product.getId()))
                        {
                            list.add(productDetail);
                            adapter.notifyDataSetChanged();
                        }
                    }
                    children.put(product, list);
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

        adapter = new ProductExpandListAdapter(SelectionProductToEditting.this, products, children);

        lvList.setAdapter(adapter);

        registerForContextMenu(lvList);
    }


    public void onCreateContextMenu(final ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child =	ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

            ProductDetail item = (ProductDetail) adapter.getChild(group, child);
            menu.setHeaderTitle("Select to action");

            //menu.setHeaderTitle(item.getProduct());
//            DatabaseReference myRef  = FirebaseDatabase.getInstance().getReference();
//            myRef.child("Products").orderByChild("id").equalTo(item.getProduct()).addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    Product pro = dataSnapshot.getValue(Product.class);
//                    menu.setHeaderTitle(pro.getName());
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });

            //menu.setHeaderIcon(R.mipmap.giay);

            menu.add(0, R.id.cmSua, 0, "Sua");
            menu.add(0, R.id.cmXoa, 0, "Xoa");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child =	ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            ProductDetail productDetail = (ProductDetail) adapter.getChild(group, child);
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

                    database.child("ProductDetails").orderByChild("id").equalTo(productDetail.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //dataSnapshot.getRef().setValue(null);

                            for (DataSnapshot child: dataSnapshot.getChildren()) {
                                child.getRef().setValue(null);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    children.get(products.get(group)).remove(child);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }

        return super.onContextItemSelected(item);
    }

    private  void creatList()
    {
        children = new HashMap<>();
        products = new ArrayList<>();
        details = new ArrayList<>();

        //Shop shop = new Shop("SH0001", "MiuMiu", "01512151211");


//        Product product = new Product("SP0001", "giay hang hieu 1", 1290000, 900000, shop.getId(), null);
//        Product product2 = new Product("SP0002", "giay di phuot 2", 175000, 150000, shop.getId(), null);
//        Product product3 = new Product("SP0003", "giay thoi trang 3", 239000, 200000, shop.getId(), null);
//        Product product4 = new Product("SP0004", "giay the thao 4", 299000, 250000, shop.getId(), null);
        Product product = new Product(null, "giay the thao","Nike", null, user.getUid(), "3 thang", 0, null, null, null , 1290000, 900000, 3);
        Product product2 = new Product(null, "giay thoi trang","Bittis", null, user.getUid(), "12 thang", 0, null, null, null , 149000, 90000, 3);
        Product product3 = new Product(null, "giay di phuot","Adidas", null, user.getUid(), "1 thang", 0, null, null, null , 499000, 300000, 3);
        Product product4 = new Product(null, "giay bao ho","Nike", null, user.getUid(), "2 thang", 0, null, null, null , 299000, 250000, 3);

        ProductDetail pd = new ProductDetail(null, null, 38, "xanh la cay", 10);
        ProductDetail pd2 = new ProductDetail(null,null, 39, "xanh la cay", 10);
        ProductDetail pd3 = new ProductDetail(null,null, 40, "xanh la cay", 10);
        ProductDetail pd4 = new ProductDetail(null,null, 38, "xam nhat", 10);
        ProductDetail pd5 = new ProductDetail(null,null, 39, "xam nhat", 10);
        ProductDetail pd6 = new ProductDetail(null,null, 40, "xam nhat", 10);
        ProductDetail pd7 = new ProductDetail(null,null, 38, "do nau", 10);

        details.add(pd);
        details.add(pd2);
        details.add(pd3);
        details.add(pd4);
        details.add(pd5);
        details.add(pd6);
        details.add(pd7);

        children.put(product, details);
        children.put(product2, null);
        children.put(product3, details);
        children.put(product4, null);

        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);
    }

}
