package tdc.edu.vn.shoesshop.Thanh;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import Controls.DateTimePicker;
import Controls.General;
import Models.Product;
import tdc.edu.vn.shoesshop.Khanh.SelectionProductToEditting;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;

public class DetailInformationOfProduct extends AppCompatActivity {
    private static final int CAM_REQUEST = 1313;
    private Dialog dialog;
    ImageButton btn_chooseImg,btn_takeaphoto;
    final int CROP_PIC = 2;
    private Uri picUri;
    private Button btn_getimage;
    private Button btnSave;
    private EditText edttensanpham, edtthuonghieu, edtbaohanh, edtgianiemyet, edtgiaban, edtdiemtichluy, edtmota;
    ImageView img_ava_patient1;
    ImageView img_ava_patient2;
    ImageView img_ava_patient3;
    RatingBar ratingBar;
    RadioButton rbtBoth, rbtNam, rbtNu;
    Intent intent;

    Product product = null;
    String img1 = null;
    String img2 = null;
    String img3 = null;

    //firebase
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_information_of_product_activity);


        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.back);

        //NumberFormat nf = NumberFormat.getInstance();
        //DecimalFormat df = (DecimalFormat) nf;
        //df.applyPattern("#,###");

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Choose Avatar Image");


        ImageButton btn_chooseImg = (ImageButton) dialog.findViewById(R.id.img_choosenGallery);
        ImageButton btn_takeaphoto = (ImageButton) dialog.findViewById(R.id.img_choosenTakephoto);

        btnSave = (Button) findViewById(R.id.btnSaveProductInformation) ;
        btn_getimage = (Button) findViewById(R.id.btn_infor);
        ratingBar = (RatingBar) findViewById(R.id.rbRating);

        edttensanpham = (EditText) findViewById(R.id.tensanpham);
        edtthuonghieu = (EditText) findViewById(R.id.thuonghieu);
        edtbaohanh = (EditText) findViewById(R.id.baohanh);
        edtgianiemyet = (EditText) findViewById(R.id.gianiemyet);
        edtgiaban = (EditText) findViewById(R.id.giaban);
        edtdiemtichluy = (EditText) findViewById(R.id.diemtichluy);
        edtmota = (EditText) findViewById(R.id.mota);

        img_ava_patient1 = (ImageView) findViewById(R.id.imgView_info1);
        img_ava_patient2 = (ImageView) findViewById(R.id.imgView_info2);
        img_ava_patient3 = (ImageView) findViewById(R.id.imgView_info3);

        rbtBoth = (RadioButton) findViewById(R.id.rbtAll);
        rbtNam = (RadioButton) findViewById(R.id.rbtMan);
        rbtNu = (RadioButton) findViewById(R.id.rbtWoman);

        General.setupUI(findViewById(R.id.information_of_product), DetailInformationOfProduct.this);

        //chon anh tu thu vien
        btn_chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFromGallery();
            }
        });

        //lay anh tu camera
        btn_takeaphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeNewProfilePicture();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });

        btn_getimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(DetailInformationOfProduct.this,"Clicked",Toast.LENGTH_SHORT).show();
                dialog.show();
            }
        });

        intent = getIntent();
        String id = (String) intent.getSerializableExtra("product");
        if(id != null)
        {
            database.child("Products").orderByChild("id").equalTo(id).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    product = dataSnapshot.getValue(Product.class);
                    edttensanpham.setText(product.getName());
                    edtbaohanh.setText(product.getGuarantee());
                    edtdiemtichluy.setText(product.getAccumulatedPoint()+"");
//            edtgiaban.setText(df.format(product.getSalePrice()));
//            edtgianiemyet.setText(df.format(product.getListedPrice()));
                    //double uudai = gia - (gia * giam * 0.01);
                    double ny = Math.round(product.getListedPrice() * 0.001 / 0.001);
                    double gb = Math.round(product.getSalePrice() * 0.001 / 0.001);
                    edtgiaban.setText(gb + "");
                    edtgianiemyet.setText(ny + "");
                    edtmota.setText(product.getDescription());
                    edtthuonghieu.setText(product.getTrademark());

                    if(product.getSex() == 0)
                    {
                        rbtNu.setChecked(true);
                    }
                    else if(product.getSex() == 1)
                    {
                        rbtNam.setChecked(true);
                    }
                    else
                    {
                        rbtBoth.setChecked(true);
                    }

                    if(product.getImage1() != null)
                    {
                        try {
                            Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage1());
                            img_ava_patient1.setImageBitmap(bitmap);
                            img1 = product.getImage1();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(product.getImage2() != null)
                    {
                        try {
                            Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage2());
                            img_ava_patient2.setImageBitmap(bitmap);
                            img2 = product.getImage2();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(product.getImage3() != null)
                    {
                        try {
                            Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage3());
                            img_ava_patient3.setImageBitmap(bitmap);
                            img3 = product.getImage3();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    float number = product.getRating();
                    ratingBar.setRating(number);
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

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product == null) {
                    intent = new Intent(DetailInformationOfProduct.this, HomeForShop.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("chuyen", 1);
                    intent.putExtra("chuyen", bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else
                {
                    intent = new Intent(DetailInformationOfProduct.this, SelectionProductToEditting.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });

        //luu thong tin san pham
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (TextUtils.isEmpty(edttensanpham.getText()+"")) {
                        Toast.makeText(getApplicationContext(), "Please enter product name!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edtthuonghieu.getText()+"")) {
                        Toast.makeText(getApplicationContext(), "Please enter trademark!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edtbaohanh.getText()+"")) {
                        Toast.makeText(getApplicationContext(), "Please enter guarantee!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edtgianiemyet.getText() + "")) {
                        Toast.makeText(getApplicationContext(), "Please email Listed price!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edtgiaban.getText() + "")) {
                        Toast.makeText(getApplicationContext(), "Please Enter price!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edtmota.getText()+"")) {
                        Toast.makeText(getApplicationContext(), "Please Enter describe!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    String tensanpham = edttensanpham.getText().toString().trim();
                    String thuonghieu = edtthuonghieu.getText().toString().trim();
                    String baohanh = edtbaohanh.getText().toString().trim();
                    double gianiemyet = Double.parseDouble(edtgianiemyet.getText().toString().trim());
                    double giaban = Double.parseDouble(edtgiaban.getText().toString().trim());
                    String mota = edtmota.getText().toString().trim();
                    int gioiTinh = -1;

                    if(rbtBoth.isChecked())
                    {
                        gioiTinh = 2;
                    }
                    else if(rbtNam.isChecked())
                    {
                        gioiTinh = 1;
                    }
                    else
                    {
                        gioiTinh = 0;
                    }

                    if (gianiemyet <= 0) {
                        Toast.makeText(getApplicationContext(), "Please check listed price!", Toast.LENGTH_LONG).show();
                        edtgianiemyet.setText("");
                        edtgianiemyet.requestFocus();
                        return;
                    }
                    if (giaban <= 1000 || giaban > gianiemyet) {
                        Toast.makeText(getApplicationContext(), "Please check sale price!", Toast.LENGTH_LONG).show();
                        edtgiaban.setText("");
                        edtgiaban.requestFocus();
                        return;
                    }

                    if(img1 == null || img2 == null || img3 == null)
                    {
                        Toast.makeText(getApplicationContext(), "Please check images!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(ratingBar.getRating() < 1)
                    {
                        Toast.makeText(getApplicationContext(), "Please rate the product!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(product == null)
                    {
                        product = new Product();
                        product.setName(tensanpham);
                        product.setRating(ratingBar.getRating());
                        if(edtdiemtichluy.getText().length() > 0) {
                            product.setAccumulatedPoint(Integer.parseInt(edtdiemtichluy.getText()+""));
                        }
                        product.setDescription(mota);
                        product.setGuarantee(baohanh);
                        product.setListedPrice(gianiemyet);
                        product.setSalePrice(giaban);
                        product.setSex(gioiTinh);
                        product.setTrademark(thuonghieu);
                        product.setShop(user.getUid());
                        product.setImage1(img1);
                        product.setImage2(img2);
                        product.setImage3(img3);
                        Calendar calendar = Calendar.getInstance();
                        //Date date = DateTimePicker.simpleDateFormat.parse(calendar.getTime().toString());
                        product.setAddDay(DateTimePicker.simpleDateFormat.format(calendar.getTime()));

                        product.setId(database.child("Products").push().getKey());

                        database.child("Products").push().setValue(product);
                        intent = new Intent(DetailInformationOfProduct.this, SelectionProductToEditting.class);
                        intent.putExtra("pro", product.getId());
                        startActivity(intent);
                    }
                    else
                    {
                        product.setName(tensanpham);
                        product.setRating(ratingBar.getRating());
                        if(edtdiemtichluy.getText().length() > 0) {
                            product.setAccumulatedPoint(Integer.parseInt(edtdiemtichluy.getText()+""));
                        }
                        product.setDescription(mota);
                        product.setGuarantee(baohanh);
                        product.setListedPrice(gianiemyet);
                        product.setSalePrice(giaban);
                        product.setTrademark(thuonghieu);
                        product.setSex(gioiTinh);
                        product.setImage1(img1);
                        product.setImage2(img2);
                        product.setImage3(img3);

                        database.child("Products").orderByChild("id").equalTo(product.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot child: dataSnapshot.getChildren()) {
                                    child.getRef().setValue(product);
                                    break;
                                }
                                intent = new Intent(DetailInformationOfProduct.this, SelectionProductToEditting.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                } catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "Check data input", Toast.LENGTH_LONG).show();
                }

//                if(img_ava_patient1.getDrawable() == null || img_infor_2.getDrawable() == null || img_infor_3.getDrawable() == null)
//                {
//                    Toast.makeText(getApplicationContext(), "Please choose image or take a photo in here!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                Intent intent = new Intent(DetailInformationOfProduct.this, SelectionProductToEditting.class);
//                startActivity(intent);
            }
        });
    }

//    public void profilepictureOnClick(){
//        General.chooseFromCamera(DetailInformationOfProduct.this);
//    }
    public void chooseFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    private void takeNewProfilePicture(){
        Activity profileFrag = this;
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        profileFrag.startActivityForResult(cameraintent, CAM_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK & requestCode == CAM_REQUEST) {
            if(requestCode == CAM_REQUEST){
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                if(img1 == null)
                {
                    Glide.with(DetailInformationOfProduct.this).load(thumbnail).into(img_ava_patient1);
                    //img_ava_patient1.setImageBitmap(thumbnail);
                    img1 = General.encodeBitmap(thumbnail);
                }
                else if (img2 == null)
                {
                    //img_ava_patient2.setImageBitmap(thumbnail);
                    Glide.with(DetailInformationOfProduct.this).load(thumbnail).into(img_ava_patient2);
                    img2 = General.encodeBitmap(thumbnail);
                }
                else if (img3 == null)
                {
                    //img_infor_3.setImageBitmap(thumbnail);
                    Glide.with(DetailInformationOfProduct.this).load(thumbnail).into(img_ava_patient3);
                    img3 = General.encodeBitmap(thumbnail);
                }
                dialog.dismiss();
            }
        }
        else if (resultCode == RESULT_OK){
            picUri = data.getData();
            //Log.i("image",picUri+"");
            Bitmap bitmap;
            try {
                Context applicationContext = dialog.getContext();
                bitmap = BitmapFactory.decodeStream( applicationContext.getContentResolver().openInputStream(picUri));

                if(img1 == null)
                {
                    Glide.with(DetailInformationOfProduct.this).load(bitmap).into(img_ava_patient1);
                    //img_ava_patient1.setImageBitmap(bitmap);
                    img1 = General.encodeBitmap(bitmap);
                }
                else if (img2 == null)
                {
                    Glide.with(DetailInformationOfProduct.this).load(bitmap).into(img_ava_patient2);
                    //img_infor_2.setImageBitmap(bitmap);
                    img2 = General.encodeBitmap(bitmap);
                }
                else if (img3 == null)
                {
                    Glide.with(DetailInformationOfProduct.this).load(bitmap).into(img_ava_patient3);
                    ///img_infor_3.setImageBitmap(bitmap);
                    img3 = General.encodeBitmap(bitmap);
                }

                dialog.dismiss();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}