package tdc.edu.vn.shoesshop.Thanh;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.FileNotFoundException;
import tdc.edu.vn.shoesshop.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nntd290897 on 3/9/18.
 */

@SuppressLint("Registered")
public class product_information extends AppCompatActivity {

    private static final int CAM_REQUEST = 1313;
    private Dialog dialog;

    final int CROP_PIC = 2;
    private Uri picUri;
    private Button btn_getimage;

    ImageView img_ava_patient1;
    ImageView img_ava_patient2;
    ImageView img_ava_patient3;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_information);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Choose Avatar Image");

        ImageButton btn_chooseImg = (ImageButton) dialog.findViewById(R.id.img_choosenGallery);
        ImageButton btn_takeaphoto = (ImageButton) dialog.findViewById(R.id.img_choosenTakephoto);


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
                Toast.makeText(product_information.this
                        ,"Clicked",Toast.LENGTH_SHORT).show();
                dialog.show();
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
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                //  picUri = data.getData();

                img_ava_patient1.setImageBitmap(thumbnail);
                dialog.dismiss();
            }
        } else if (resultCode == RESULT_OK) {
            picUri = data.getData();

            // Uri targetUri = data.getData();
            //  textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                Context applicationContext = dialog.getContext();
                bitmap = BitmapFactory.decodeStream(applicationContext.getContentResolver().openInputStream(picUri));

                img_ava_patient1.setImageBitmap(bitmap);

                dialog.dismiss();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
