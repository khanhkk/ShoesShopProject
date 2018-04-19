package tdc.edu.vn.shoesshop.Thanh;

import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import Controls.General;
import tdc.edu.vn.shoesshop.Bao.MainInfoShop;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;

public class EdittingShopInformation extends AppCompatActivity {
    private static final int CAM_REQUEST = 1313;
    private Dialog dialog;
    ImageButton btn_chooseImg,btn_takeaphoto;
    final int CROP_PIC = 2;
    private Uri picUri;
    private Button btn_getimage;
    private Button btnSave;
    private Button btnSaveShopInformation;
    Intent intent;
    private EditText edttenshop, edtsdt, edtdiachi, edtemail, edtnguoidaidien, edtfacebook, edtsotaikhoan;

    ImageView img_ava_patient;
    @Nullable
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_shop_information_activity);


        final Button save = (Button) findViewById(R.id.btnSaveShopInformation);

        //EditText
        edttenshop = (EditText) findViewById(R.id.ten_shop);
        edtdiachi = (EditText) findViewById(R.id.dia_chi);
        edtemail = (EditText) findViewById(R.id.email);
        edtnguoidaidien = (EditText) findViewById(R.id.nguoidaidien_shop);
        edtfacebook = (EditText) findViewById(R.id.facebook);
        edtsdt = (EditText) findViewById(R.id.sdt);
        edtsotaikhoan = (EditText) findViewById(R.id.sotaikhoan);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdittingShopInformation.this, MainInfoShop.class);
                startActivity(intent);

            }
        });

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Choose Avatar Image");

        btnSave = (Button) findViewById(R.id.btnSaveShopInformation);
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

        btn_getimage = (Button) findViewById(R.id.btn_infor);
        btn_getimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EdittingShopInformation.this
                        ,"Clicked",Toast.LENGTH_SHORT).show();
                dialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edttenshop.getText().toString().trim();
                String diachi = edtdiachi.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String sdt = edtsdt.getText().toString().trim();
                String sotaikhoan = edtsotaikhoan.getText().toString().trim();
                String nguoidaidien = edtnguoidaidien.toString().trim();
                String facebook = edtfacebook.toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Please enter name!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(sdt)){
                    Toast.makeText(getApplicationContext(),"Please number phone!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(diachi)){
                    Toast.makeText(getApplicationContext(), "Please enter Address!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Please email address!", Toast.LENGTH_LONG).show();
                    return;}
                if(TextUtils.isEmpty(nguoidaidien)){
                    Toast.makeText(getApplicationContext(), "Please Enter The Representative!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(sotaikhoan)){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Account Number!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(facebook)){
                    Toast.makeText(getApplicationContext(), "Please Enter Facebook!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (sdt.length() <  10 || sdt.length() > 11){
                    Toast.makeText(getApplicationContext(), "Please Characters Between 10-11", Toast.LENGTH_LONG).show();
                    edtsdt.setText("");
                    edtsdt.requestFocus();
                    return;
                }
                if (sotaikhoan.length() <  10 || sotaikhoan.length() > 11){
                    Toast.makeText(getApplicationContext(), "Please Characters Between 10-11" + sotaikhoan.length()+sotaikhoan, Toast.LENGTH_LONG).show();
                    edtsotaikhoan.setText("");
                    edtsotaikhoan.requestFocus();
                    return;
                }
                intent = getIntent();
                intent.setClass(EdittingShopInformation.this, HomeForShop.class);
                startActivity(intent);
            }
        });

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

                dialog.dismiss();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
