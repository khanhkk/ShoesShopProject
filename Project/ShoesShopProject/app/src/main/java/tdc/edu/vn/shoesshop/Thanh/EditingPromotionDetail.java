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
    EditText  edtquatang, edtgiamgia, edtgiaban, edtgiauudai;
    Spinner spnSanPham;
    ArrayList<String> listProduct = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Bundle bundle = null;
    Promotion promotion = null;
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


        edtgiamgia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isDigitsOnly(edtgiamgia.getText()+""))
                {
                    if(Integer.parseInt(edtgiamgia.getText()+"") > 0 && !TextUtils.isEmpty(edtgiaban.getText()+""))
                    {
                        double gia = Double.parseDouble(edtgiaban.getText()+"");
                        int giam = Integer.parseInt(edtgiamgia.getText()+"");
                        double uudai = gia * giam / 100;
                        edtgiauudai.setText(uudai + "");
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //lay danh sach san pham cua shop
        Query query = database.child("Products").orderByChild("shop").equalTo(user.getUid());
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                listProduct.add(product.getName());
                products.add(product);
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

        //tao adapter va gan cho spinner
        adapter = new ArrayAdapter<>(EditingPromotionDetail.this, android.R.layout.simple_list_item_1, listProduct);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnSanPham.setAdapter(adapter);
        spnSanPham.setOnItemSelectedListener(new MyProcessEvent());

        //nhan gia tri tu activity khac
        intent = getIntent();
        final PromotionsDetail promotionsDetail = (PromotionsDetail)intent.getSerializableExtra("detail");
        if(promotionsDetail != null)
        {
            type = 1;
            edtgiamgia.setText( promotionsDetail.getDiscount());
            edtquatang.setText(promotionsDetail.getGift());
            for(Product product : products)
            {
                if(product.getId() == promotionsDetail.getProduct())
                {
                    edtgiaban.setText(product.getSalePrice()+"");
                    double gia = product.getSalePrice();
                    double uudai = gia*promotionsDetail.getDiscount()/100;
                    edtgiauudai.setText(uudai+"");
                }
            }

        }
        else if((bundle = intent.getBundleExtra("data")) != null)
        {
            String s = (String)bundle.getString("key");
            type = 2;
            for(Promotion promotion : Promotions.listParent)
            {
                if(promotion.getId() == s)
                {
                    this.promotion = promotion;
                }
            }

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String sanpham = s.getText().toString().trim();
                String quatang = edtquatang.getText().toString().trim();
                String giamgia = edtgiamgia.getText().toString().trim();
                int giaban = Integer.parseInt(edtgiaban.getText().toString().trim());
                int giauudai = Integer.parseInt(edtgiauudai.getText().toString().trim());
//                if(TextUtils.isEmpty(sanpham)){
//                    Toast.makeText(getApplicationContext(), "Please enter product", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(TextUtils.isEmpty(quatang)){
                    Toast.makeText(getApplicationContext(), "Please enter promotion", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(giamgia)){
                    Toast.makeText(getApplicationContext(), "Please enter sale", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (giaban <=  0 ){
                    Toast.makeText(getApplicationContext(), "Please value is more than 0", Toast.LENGTH_LONG).show();                    edtgiaban.setText("");
                    edtgiaban.requestFocus();
                    return;
                }
                if(giauudai <= 0 || giauudai > giaban){
                    Toast.makeText(getApplicationContext(), "Please Characters is more than 0 and  less than 'gia ban'", Toast.LENGTH_SHORT).show();
                    edtgiauudai.setText("");
                    edtgiauudai.requestFocus();
                    return;
                }

                if(type == 1)
                {

                }
                else
                {
                    PromotionsDetail promotionsDetail1 = new PromotionsDetail();
                    promotionsDetail.setDiscount(Integer.parseInt(edtgiamgia.getText()+""));
                    promotionsDetail.setGift(edtquatang.getText()+"");
                }

                intent.setClass(EditingPromotionDetail.this, Promotions.class);
                startActivity(intent);
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
          Product product =  products.get(arg2);
          edtgiaban.setText(product.getSalePrice()+"");
          edtgiauudai.setText(product.getSalePrice()+"");
        }

        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
