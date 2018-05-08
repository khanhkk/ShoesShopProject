package tdc.edu.vn.shoesshop.Thanh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Controls.General;
import Models.Product;
import Models.Promotion;
import Models.PromotionsDetail;
import tdc.edu.vn.shoesshop.Khanh.Promotions;
import tdc.edu.vn.shoesshop.R;

public class EditingPromotionDetail extends AppCompatActivity {

    Button btnSave;
    Intent intent;
    EditText  edtquatang, edtgiamgia, edtgiaban, edtgiauudai, edtDiemTichLuy;
    Spinner spnSanPham;

    ArrayList<String> listProduct = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    ArrayAdapter<String> adapter;

    Bundle bundle = null;
    String promotion = null;
    Product product = null;
    //String product_id = null;
    PromotionsDetail promotionsDetail;
    //String id = null;
    int type = 0;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editing_promotion_detail_activity);

        //setup focus edittext
        General.setupUI(findViewById(R.id.promotion_detail), EditingPromotionDetail.this);

        //anh xa
        btnSave = (Button) findViewById(R.id.btnSavePromotionsDetail);
        spnSanPham = (Spinner) findViewById(R.id.spnSanPham);

        edtquatang = (EditText) findViewById(R.id.quatang);
        edtgiamgia = (EditText) findViewById(R.id.giamgia);
        edtgiaban = (EditText) findViewById(R.id.giaban_promotion);
        edtgiauudai = (EditText) findViewById(R.id.giauudai);
        edtDiemTichLuy = (EditText) findViewById(R.id.diemTichLuy);

        //lay danh sach san pham cua shop
        products.clear();
        listProduct.clear();
        Query query = database.child("Products").orderByChild("shop").equalTo(user.getUid());
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                listProduct.add(product.getName());
                products.add(product);

                if(promotionsDetail != null) {
                    if (product.getId().equals(promotionsDetail.getProduct())) {
                        spnSanPham.setSelection(listProduct.indexOf(product.getName()));
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

        //nhan gia tri tu activity khac
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("sua");
        if(bundle != null)
        {
            promotionsDetail = (PromotionsDetail) bundle.getSerializable("detail");
            edtquatang.setText(promotionsDetail.getGift());
            this.promotion = promotionsDetail.getPromotions();
            //this.product_id = promotionsDetail.getProduct();
            spnSanPham.setEnabled(false);
            edtgiamgia.setText( promotionsDetail.getDiscount() + "");
            edtDiemTichLuy.setText(promotionsDetail.getPoint() + "");
            //id = promotionsDetail.getId();
            type = 1;
        }
        else if((bundle = intent.getBundleExtra("data")) != null)
        {
            String s = (String)bundle.getString("key");
            type = 2;
            for(Promotion promotion : Promotions.listParent)
            {
                if(promotion.getId().equals(s))
                {
                    this.promotion = promotion.getId();
                }
            }
        }

        //tao adapter va gan cho spinner
        adapter = new ArrayAdapter<>(EditingPromotionDetail.this, android.R.layout.simple_spinner_item, listProduct);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnSanPham.setAdapter(adapter);
        spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    product =  products.get(i);
                    edtgiaban.setText(product.getSalePrice() + "");
                    edtgiauudai.setText(product.getSalePrice() + "");
                if(edtgiamgia.getText().length() > 0) {
                    changePrice();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtgiamgia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                changePrice();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD


=======
                //String sanpham = s.getText().toString().trim();
>>>>>>> 3e4951d3a8aadaa3ae7794ce50ec6e4ee7f6ddd7
                String quatang = edtquatang.getText().toString().trim();
                String giamgia = edtgiamgia.getText().toString().trim();

                if(TextUtils.isEmpty(quatang) && TextUtils.isEmpty(giamgia) && TextUtils.isEmpty(edtDiemTichLuy.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(), "Please enter promotion", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(type == 1)
                {
                    //sua thong tin san pham duoc khuyen mai
                    if(edtgiamgia.getText().length() > 0) {
                        try {
                            promotionsDetail.setDiscount(Integer.parseInt(edtgiamgia.getText() + ""));
                        }catch (Exception ex)
                        {
                            Toast.makeText(getApplicationContext(), "Check discount for the product!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    if(edtDiemTichLuy.getText().length() > 0) {
                        try {
                            promotionsDetail.setPoint(Integer.parseInt(edtDiemTichLuy.getText() + ""));
                        }catch (Exception ex)
                        {
                            Toast.makeText(getApplicationContext(), "Check discount for the product!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    promotionsDetail.setGift(edtquatang.getText() + "");

                    database.child("PromotionsDetails").orderByChild("id").equalTo(promotionsDetail.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //PromotionsDetail pro = dataSnapshot.getValue(PromotionsDetail.class);
                            //Log.d("test", pro.getPromotions() + "111" + promotion);

                            //if(pro.getPromotions().equals(promotion))
                            //{
                            //dataSnapshot.getRef().setValue(promotionsDetail);

                            for (DataSnapshot child: dataSnapshot.getChildren()) {
                                child.getRef().setValue(promotionsDetail);
                            }

                            Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_LONG).show();
                            intent.setClass(EditingPromotionDetail.this, Promotions.class);
                            startActivity(intent);
                            //}
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                else
                {
                    //them san pham duoc khuyen mai moi
                    try {
                        PromotionsDetail pro = new PromotionsDetail();

                        if (edtgiamgia.getText().length() > 0) {
                            pro.setDiscount(Integer.parseInt(edtgiamgia.getText() + ""));
                        }

                        if (edtDiemTichLuy.getText().length() > 0) {
                            pro.setPoint(Integer.parseInt(edtDiemTichLuy.getText() + ""));
                        }

                        pro.setGift(edtquatang.getText() + "");
                        pro.setProduct(product.getId());
                        pro.setPromotions(promotion);

                        database.child("PromotionsDetails").orderByChild("promotions").equalTo(promotion).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                PromotionsDetail pro = dataSnapshot.getValue(PromotionsDetail.class);
//                            if(promotionsDetail1.getPromotions().equals(promotion))
//                            {
                                if (pro.getProduct().equals(product.getId()) && pro.getPromotions().equals(promotion)) {
                                    Toast.makeText(getApplicationContext(), "This product was in the promotions program!", Toast.LENGTH_LONG).show();
                                    return;
                                }
//                            }
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

                        pro.setId(database.child("PromotionsDetails").push().getKey());
                        database.child("PromotionsDetails").push().setValue(pro);
                        Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_LONG).show();
                        intent.setClass(EditingPromotionDetail.this, Promotions.class);
                        startActivity(intent);
                    }catch (Exception ex)
                    {
                        Toast.makeText(getApplicationContext(), "Check data input!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    //update gia moi
    public void changePrice()
    {
        if(edtgiaban.getText().length()>0) {
            int giam = 0;
            double gia = Double.parseDouble(edtgiaban.getText() + "");
            try {
                giam = Integer.parseInt(edtgiamgia.getText() + "");
                if (giam >= 0 && giam < 100) {
                    double uudai = gia - (gia * giam * 0.01);
                    double tien = Math.round(uudai * 0.001 / 0.001);
                    edtgiauudai.setText(tien + "");
                } else {
                    Toast.makeText(EditingPromotionDetail.this, "Data input is invalid !!!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Toast.makeText(EditingPromotionDetail.this, "Input number!!!", Toast.LENGTH_SHORT).show();
                edtgiauudai.setText(edtgiaban.getText() + "");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
