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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import Controls.General;
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
    ImageView img_ava_patient1,img_infor_2,img_infor_3;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_information_of_product_activity);


        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailInformationOfProduct.this, HomeForShop.class);
                Bundle bundle = new Bundle();
                bundle.putInt("chuyen",1);
                intent.putExtra("chuyen",bundle);
                startActivity(intent);
            }
        });

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Choose Avatar Image");

        btn_chooseImg = (ImageButton) dialog.findViewById(R.id.img_choosenGallery);
        btn_takeaphoto = (ImageButton) dialog.findViewById(R.id.img_choosenTakephoto);
        btnSave = (Button) findViewById(R.id.btnSaveProductInformation) ;


        edttensanpham = (EditText) findViewById(R.id.tensanpham);
        edtthuonghieu = (EditText) findViewById(R.id.thuonghieu);
        edtbaohanh = (EditText) findViewById(R.id.baohanh);
        edtgianiemyet = (EditText) findViewById(R.id.gianiemyet);
        edtgiaban = (EditText) findViewById(R.id.giaban);
        edtdiemtichluy = (EditText) findViewById(R.id.diemtichluy);
        edtmota = (EditText) findViewById(R.id.mota);

        General.setupUI(findViewById(R.id.information_of_product), DetailInformationOfProduct.this);

        btn_chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFromGallery();
            }
        });
        btn_takeaphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeNewProfilePicture();
            }
        });
        img_ava_patient1 = (ImageView) findViewById(R.id.imgView_info1);
        img_infor_2  = (ImageView) findViewById(R.id.imgView_info2);
        img_infor_3  = (ImageView) findViewById(R.id.imgView_info3);

        btn_getimage = (Button) findViewById(R.id.btn_infor);
        btn_getimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailInformationOfProduct.this
                        ,"Clicked",Toast.LENGTH_SHORT).show();
                dialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensanpham = edttensanpham.getText().toString().trim();
                String thuonghieu = edtthuonghieu.getText().toString().trim();
                String baohanh = edtbaohanh.getText().toString().trim();
                int gianiemyet = -1;
                if (edtgianiemyet.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter price marker!", Toast.LENGTH_LONG).show();
                    return;
                }
                gianiemyet = Integer.parseInt(edtgianiemyet.getText().toString().trim());
                int giaban = -1;
                if (edtgiaban.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter product price!", Toast.LENGTH_LONG).show();
                    return;
                }
                giaban = Integer.parseInt(edtgiaban.getText().toString().trim());
                String diemtichluy = edtdiemtichluy.toString().trim();
                String mota = edtmota.toString().trim();
                if (TextUtils.isEmpty(tensanpham)){
                    Toast.makeText(getApplicationContext(), "Please enter product name!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(thuonghieu)){
                    Toast.makeText(getApplicationContext(),"Please enter trademark!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(baohanh)){
                    Toast.makeText(getApplicationContext(), "Please enter guarantee!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(edtgianiemyet.getText() +"")){
                    Toast.makeText(getApplicationContext(), "Please email Listed price!", Toast.LENGTH_LONG).show();
                    return;}
                if(TextUtils.isEmpty(edtgiaban.getText() +"")){
                    Toast.makeText(getApplicationContext(), "Please Enter price!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(diemtichluy)){
                    Toast.makeText(getApplicationContext(), "Please Enter cumulative point!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(mota)){
                    Toast.makeText(getApplicationContext(), "Please Enter describe!", Toast.LENGTH_LONG).show();
                    return;
                }
               if ((gianiemyet < 100000) || (gianiemyet > 300000)){
                  Toast.makeText(getApplicationContext(), "Please Characters Between 100000-300000", Toast.LENGTH_LONG).show();
                   edtgianiemyet.setText("");
                    edtgianiemyet.requestFocus();
                    return;
                }
                if (giaban <  100000 || giaban > 200000){
                    Toast.makeText(getApplicationContext(), "Please Characters Between 10-11", Toast.LENGTH_LONG).show();                    edtgiaban.setText("");
                    edtgiaban.requestFocus();
                    return;
                }
                if(img_ava_patient1.getDrawable() == null || img_infor_2.getDrawable() == null || img_infor_3.getDrawable() == null)
                {
                    Toast.makeText(getApplicationContext(), "Please choose image or take a photo in here!", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(DetailInformationOfProduct.this, SelectionProductToEditting.class);
                    startActivity(intent);

            }
        });
    }

    public void profilepictureOnClick(){
        General.chooseFromCamera(DetailInformationOfProduct.this);
    }
    public void chooseFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
    }


    private void takeNewProfilePicture(){
        Activity profileFrag = this;
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        profileFrag.startActivityForResult(cameraintent, CAM_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == CAM_REQUEST) {
            if(requestCode == CAM_REQUEST){
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                //  picUri = data.getData();
               // img_ava_patient1.setImageBitmap(thumbnail);

                if(img_ava_patient1.getDrawable() == null)
                {
                    img_ava_patient1.setImageBitmap(thumbnail);
                }
                else if (img_infor_2.getDrawable() == null)
                {
                    img_infor_2.setImageBitmap(thumbnail);
                }
                else if (img_infor_3.getDrawable() == null)
                {
                    img_infor_3.setImageBitmap(thumbnail);
                }
                dialog.dismiss();
            }
        }
        else if (resultCode == RESULT_OK){
            picUri = data.getData();
            Log.i("image",picUri+"");
            // Uri targetUri = data.getData();
            //  textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                Context applicationContext = dialog.getContext();
                bitmap = BitmapFactory.decodeStream( applicationContext.getContentResolver().openInputStream(picUri));
                if(img_ava_patient1.getDrawable() == null)
                {
                    img_ava_patient1.setImageBitmap(bitmap);
                }
                else if (img_infor_2.getDrawable() == null)
                {
                    img_infor_2.setImageBitmap(bitmap);
                }
                else if (img_infor_3.getDrawable() == null)
                {
                    img_infor_3.setImageBitmap(bitmap);
                }

                dialog.dismiss();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}