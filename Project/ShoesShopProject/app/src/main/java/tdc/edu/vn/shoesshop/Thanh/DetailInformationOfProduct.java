package tdc.edu.vn.shoesshop.Thanh;

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

import java.io.FileNotFoundException;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import Controls.General;
import tdc.edu.vn.shoesshop.Khanh.SelectionProductToEditting;
import tdc.edu.vn.shoesshop.R;

public class DetailInformationOfProduct extends AppCompatActivity {

    private static final int CAM_REQUEST = 1313;
    private Dialog dialog;

    final int CROP_PIC = 2;
    private Uri picUri;
    private Button btn_getimage;
    private Button btnSave;

    ImageView img_ava_patient1;
    ImageView img_ava_patient2;
    ImageView img_ava_patient3;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_information_of_product_activity);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Choose Avatar Image");

        ImageButton btn_chooseImg = (ImageButton) dialog.findViewById(R.id.img_choosenGallery);
        ImageButton btn_takeaphoto = (ImageButton) dialog.findViewById(R.id.img_choosenTakephoto);
        btnSave = (Button) findViewById(R.id.btnSaveProductInformation) ;

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

                profilepictureOnClick();
            }
        });

        img_ava_patient1 = (ImageView) findViewById(R.id.imgView_info1);
        img_ava_patient2 = (ImageView) findViewById(R.id.imgView_info2);
        img_ava_patient3 = (ImageView) findViewById(R.id.imgView_info3);


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
                Intent intent = new Intent(DetailInformationOfProduct.this, SelectionProductToEditting.class);
                startActivity(intent);
            }
        });
    }

    /*

     */


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

        if (resultCode == RESULT_OK && requestCode == CAM_REQUEST) {
            if (requestCode == CAM_REQUEST) {
                Bitmap thumbnail1 = (Bitmap) data.getExtras().get("data");
                Bitmap thumbnail2 = (Bitmap) data.getExtras().get("data");
                Bitmap thumbnail3 = (Bitmap) data.getExtras().get("data");
                //  picUri = data.getData();
                img_ava_patient1.setImageBitmap(thumbnail1);
                img_ava_patient2.setImageBitmap(thumbnail2);
                img_ava_patient3.setImageBitmap(thumbnail3);
                dialog.dismiss();
            }
        } else if (resultCode == RESULT_OK) {
            picUri = data.getData();

            // Uri targetUri = data.getData();
            //  textTargetUri.setText(targetUri.toString());
            Bitmap bitmap1, bitmap2, bitmap3;
            try {
                Context applicationContext = dialog.getContext();
                bitmap1 = BitmapFactory.decodeStream(applicationContext.getContentResolver().openInputStream(picUri));
                bitmap2 = BitmapFactory.decodeStream(applicationContext.getContentResolver().openInputStream(picUri));
                bitmap3 = BitmapFactory.decodeStream(applicationContext.getContentResolver().openInputStream(picUri));
                img_ava_patient1.setImageBitmap(bitmap1);
                img_ava_patient2.setImageBitmap(bitmap2);
                img_ava_patient3.setImageBitmap(bitmap3);
                dialog.dismiss();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}