package tdc.edu.vn.shoesshop.Sang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import Controls.General;
import Models.Product;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.Khanh.SelectionProductToEditting;
import tdc.edu.vn.shoesshop.R;

public class QuantityManagement extends AppCompatActivity {

    Button btnSave;
    EditText edtProduct, edtColor, edtSize, edtQuantity;
    LinearLayout llBound;

    Intent intent;
    ProductDetail productDetail = null;
    Product product = null;

    ArrayList<ProductDetail> listDetails = new ArrayList<>();

    //firebase
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutquantitymanagement17);

        //anh xa
        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.back);

        btnSave = (Button) findViewById(R.id.btnSaveQuantityInformation);
        edtProduct = (EditText) findViewById(R.id.edtNameProduct);
        edtColor = (EditText) findViewById(R.id.edtColor);
        edtQuantity = (EditText) findViewById(R.id.edtQuantity);
        edtSize = (EditText) findViewById(R.id.edtSize);
        llBound = (LinearLayout) findViewById(R.id.llEditQuantityManagement);

        General.setupUI(llBound, QuantityManagement.this );

        listDetails.clear();
        intent = getIntent();
        productDetail = (ProductDetail) intent.getSerializableExtra("detail");
        if(productDetail != null)
        {
            edtSize.setText(productDetail.getSize() + "");
            edtQuantity.setText(productDetail.getQuantity() + "");
            edtColor.setText(productDetail.getColor());
            database.child("Products").orderByChild("id").equalTo(productDetail.getProduct()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    product = dataSnapshot.getValue(Product.class);
                    edtProduct.setText(product.getName());

                    database.child("ProductDetails").orderByChild("product").equalTo(product.getId()).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            ProductDetail productDetail = dataSnapshot.getValue(ProductDetail.class);
                            listDetails.add(productDetail);
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
        else if( (product = (Product) intent.getSerializableExtra("product")) != null )
        {
            edtProduct.setText(product.getName());
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(QuantityManagement.this, SelectionProductToEditting.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(edtProduct.getText().toString().trim()))
                {
                    Toast.makeText(QuantityManagement.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(edtColor.getText().toString().trim()))
                {
                    Toast.makeText(getApplicationContext(), "Nhập màu sắc!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(edtSize.getText().toString().trim()))
                {
                    Toast.makeText(getApplicationContext(), "Nhập kích cỡ!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(edtQuantity.getText().toString().trim()))
                {
                    Toast.makeText(getApplicationContext(), "Nhập số lượng!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    String mau = edtColor.getText().toString().trim();
                    int size = Integer.parseInt(edtSize.getText().toString().trim());
                    int quantity = Integer.parseInt(edtQuantity.getText().toString().trim());
                    String color = edtColor.getText() + "";

                    if(size <= 0)
                    {
                        Toast.makeText(getApplicationContext(), "Nhập kích cỡ lớn hơn 0!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(quantity <= 0)
                    {
                        Toast.makeText(getApplicationContext(), "Nhập số lượng lớn hơn 0!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(listDetails.size() > 1)
                    {
                        for(ProductDetail pro : listDetails)
                        {
                            if(pro.getId() != productDetail.getId())
                            {
                                if(pro.getColor() == color && pro.getSize() == size)
                                {
                                    Toast.makeText(getApplicationContext(), "Kiểm tra thông tin nhập!", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "Kiểm tra thông tin nhập!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }
                    }

                    if (productDetail == null) {
                        productDetail = new ProductDetail();
                        productDetail.setProduct(product.getId());
                        productDetail.setColor(mau);
                        productDetail.setSize(size);
                        productDetail.setQuantity(quantity);
                        productDetail.setId(database.child("ProductDetails").push().getKey());
                        database.child("ProductDetails").push().setValue(productDetail);
                        intent = new Intent(QuantityManagement.this, SelectionProductToEditting.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                    else {
                        productDetail.setColor(mau);
                        productDetail.setSize(size);
                        productDetail.setQuantity(quantity);
                        database.child("ProductDetails").orderByChild("id").equalTo(productDetail.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot child: dataSnapshot.getChildren()) {
                                    child.getRef().setValue(productDetail);
                                }
                                intent = new Intent(QuantityManagement.this, SelectionProductToEditting.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "Please check data input!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
