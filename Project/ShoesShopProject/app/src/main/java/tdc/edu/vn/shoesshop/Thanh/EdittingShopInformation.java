package tdc.edu.vn.shoesshop.Thanh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;

import Controls.General;
import Models.Shop;
import tdc.edu.vn.shoesshop.Bao.MainInfoShop;
import tdc.edu.vn.shoesshop.R;

public class EdittingShopInformation extends AppCompatActivity {
    private static final int CAM_REQUEST = 1313;
    private Dialog dialog;
    ImageButton btn_chooseImg,btn_takeaphoto;
    final int CROP_PIC = 2;
    private Uri picUri;
    private ImageButton btn_getimage;
    private Button btnSave;
    Intent intent;
    String img;
    private TextInputLayout textInputTen;
    private TextInputLayout textInputNguoidaidien;
    private TextInputLayout textInputSdt;
    private TextInputLayout textInputDiachi;
    private TextInputLayout textInputFacebook;
    private TextInputLayout textInputSotaikhoan;


    private TextView txt_ten;
    private TextView txt_nguoidd;
    private TextView txt_fb;
    private TextView txt_sotk;
    private TextView txt_sdt;
    private TextView txt_diachi;


    ImageView img_ava_patient;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @SuppressLint("WrongViewCast")
    @Nullable
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_shop_information_activity);

        textInputTen = findViewById(R.id.ten_shop);
        textInputSdt = findViewById(R.id.sdt);
        textInputDiachi = findViewById(R.id.dia_chi);
        textInputNguoidaidien = findViewById(R.id.nguoidaidien_shop);
        textInputSotaikhoan = findViewById(R.id.sotaikhoan);
        textInputFacebook = findViewById(R.id.facebook);

        txt_ten = (TextView) findViewById(R.id.tname);
        txt_nguoidd = (TextView) findViewById(R.id.tndd);
        txt_fb = (TextView) findViewById(R.id.tfb);
        txt_sotk = (TextView) findViewById(R.id.tsotk);
        txt_sdt = (TextView) findViewById(R.id.tphone);
        txt_diachi = (TextView) findViewById(R.id.tdiachi);



        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
                finish();
            }
        });
        pullData();

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Choose Avatar Image");

        btn_chooseImg = (ImageButton) dialog.findViewById(R.id.img_choosenGallery);
        btn_takeaphoto = (ImageButton) dialog.findViewById(R.id.img_choosenTakephoto);
        General.setupUI(findViewById(R.id.information_of_shop), EdittingShopInformation.this);

        btn_chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFromGallery();
            }
        });

        btn_takeaphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profilepictureOnClick();
            }
        });

        img_ava_patient = (ImageView) findViewById(R.id.imgView_info);
        btn_getimage = (ImageButton) findViewById(R.id.btn_infor);

        btn_getimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EdittingShopInformation.this
                        ,"Clicked",Toast.LENGTH_SHORT).show();
                dialog.show();
            }
        });


    }
    private boolean validateHoten() {
        String usernameInput = textInputTen.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            textInputTen.setError("Không thể để trống");
            return false;
        } else {
            textInputTen.setError(null);
            return true;
        }
    }

    private boolean validateSdt() {
        String sdtInput = textInputSdt.getEditText().getText().toString().trim();

        if (sdtInput.isEmpty()) {
            textInputSdt.setError("Không thể để trống");
            return false;
        } else {
            if(sdtInput.length() != 10 && sdtInput.length() != 11)
            {
                textInputSdt.setError("Số điện thoại chưa đúng !");
                return false;
            }else
            {
                textInputSdt.setError(null);
                return true;
            }

        }
    }

    private boolean validateDiachi() {
        String passwordInput = textInputDiachi.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputDiachi.setError("Không thể để trống");
            return false;
        } else {
            textInputDiachi.setError(null);
            return true;
        }
    }


    private boolean validateNguoiDD() {
        String inputNguoiDD = textInputNguoidaidien.getEditText().getText().toString().trim();

        if (inputNguoiDD.isEmpty()) {
            textInputNguoidaidien.setError("Không thể để trống");
            return false;
        } else {
            textInputNguoidaidien.setError(null);
            return true;
        }
    }
    private boolean validateSotaikhoan() {
        String soTkInput = textInputSotaikhoan.getEditText().getText().toString().trim();

        if (soTkInput.isEmpty()) {
            textInputSotaikhoan.setError("Không thể để trống !");
            return false;
        } else {
            if(soTkInput.length() != 13)
            {
                textInputSotaikhoan.setError("Số tài khoản chưa đúng !");
                return false;
            }else
            {
                textInputSotaikhoan.setError(null);
                return true;
            }
        }
    }


    private boolean validateFacebook() {
        String inputFB = textInputFacebook.getEditText().getText().toString().trim();

        if (inputFB.isEmpty()) {
            textInputFacebook.setError("Không thể để trống");
            return false;
        } else {
            textInputFacebook.setError(null);
            return true;
        }
    }




    public void confirmInput(View v) {
        if ( !validateHoten() | !validateSdt() | !validateDiachi()| !validateNguoiDD() | !validateSotaikhoan() | !validateFacebook() ) {
            return;
        }

        updateShop();
        onBackPressed();
    }

    public void chooseFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }


    private void takeNewProfilePicture(){
        Activity profileFrag = this;
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        profileFrag.startActivityForResult(cameraintent, CAM_REQUEST);
    }



    public void profilepictureOnClick(){
        takeNewProfilePicture();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == CAM_REQUEST) {
            if(requestCode == CAM_REQUEST){
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                img = General.encodeBitmap(thumbnail);
                //  picUri = data.getData();

                img_ava_patient.setImageBitmap(thumbnail);
                dialog.dismiss();
            }
        }
        else if (resultCode == RESULT_OK){
            picUri = data.getData();

            // Uri targetUri = data.getData();
            //  textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                Context applicationContext = dialog.getContext();
                bitmap = BitmapFactory.decodeStream( applicationContext.getContentResolver().openInputStream(picUri));

                img_ava_patient.setImageBitmap(bitmap);
                img = General.encodeBitmap(bitmap);
                dialog.dismiss();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void pullData() {
        database.child("Shops").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Shop shop = dataSnapshot.getValue(Shop.class);
                if(shop != null)
                {
                    txt_ten.setText(shop.getName());
                    txt_nguoidd.setText(shop.getNguoidaidien());
                    txt_sdt.setText(shop.getPhone());
                    txt_fb.setText(shop.getFb());
                    txt_sotk.setText(shop.getBankAccount());
                    txt_diachi.setText(shop.getAddress());

                    if(shop.getImage() != null)
                    {
                        try {
                            Bitmap bitmap = General.decodeFromFirebaseBase64(shop.getImage());
                            Glide.with(EdittingShopInformation.this).load(bitmap).into(img_ava_patient);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void updateShop()
    {
        database.child("Shops").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Shop shop = dataSnapshot.getValue(Shop.class);
                dataSnapshot.getRef().child("name").setValue(txt_ten.getText().toString());
                dataSnapshot.getRef().child("phone").setValue(txt_sdt.getText().toString());
                dataSnapshot.getRef().child("address").setValue(txt_diachi.getText().toString());
                dataSnapshot.getRef().child("bankAccount").setValue(txt_sotk.getText().toString());
                dataSnapshot.getRef().child("nguoidaidien").setValue(txt_nguoidd.getText().toString());
                dataSnapshot.getRef().child("fb").setValue(txt_fb.getText().toString());
                if(img != null)
                {
                    dataSnapshot.getRef().child("image").setValue(img.toString());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Lưu?")
                .setMessage("Bạn muốn lưu?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent_o_s = new Intent(EdittingShopInformation.this, MainInfoShop.class);
                        setResult(RESULT_OK, intent_o_s);
                        EdittingShopInformation.super.onBackPressed();
                    }
                }).create().show();
    }
}
