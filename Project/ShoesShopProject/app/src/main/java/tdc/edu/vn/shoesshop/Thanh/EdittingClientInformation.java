package tdc.edu.vn.shoesshop.Thanh;

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
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import Models.Client;
import tdc.edu.vn.shoesshop.Bao.MainInfoShop;
import tdc.edu.vn.shoesshop.R;

public class EdittingClientInformation extends AppCompatActivity {
    ImageView img_ava_patient;
    private static final int CAM_REQUEST = 1313;
    private Dialog dialog;
    Bundle bundle;
    ImageButton btn_chooseImg, btn_takeaphoto;
    final int CROP_PIC = 2;
    private Uri picUri;
    private ImageButton btn_getimage;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputHoten;
    private TextInputLayout textInputSdt;
    private TextInputLayout textInputDiachi;
    private TextInputEditText txtname;
    private TextInputEditText txtemail;
    private TextInputEditText txtsdt;
    private TextInputEditText txtdiachi;
    private String img;

    public static final String Bundle = "bundle";
    public static final String Name = "name";
    public static final String Sdt = "sodienthoai";
    public static final String Email = "email";
    public static final String DiaChi = "diachi";


    //ImageButton back;
    //Button btnOrder;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_client_information_activity);
        //textInputEmail = findViewById(R.id.id_email);
        textInputHoten = findViewById(R.id.id_hoten);
        textInputSdt = findViewById(R.id.id_sdt);
        textInputDiachi = findViewById(R.id.id_diachi);

        //txtemail = findViewById(R.id.id_edtemail);
        txtname = findViewById(R.id.id_edthoten);
        txtsdt = findViewById(R.id.id_edtsdt);
        txtdiachi = findViewById(R.id.id_edtdiachi);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Choose Avatar Image");

        btn_chooseImg = (ImageButton) dialog.findViewById(R.id.img_choosenGallery);
        btn_takeaphoto = (ImageButton) dialog.findViewById(R.id.img_choosenTakephoto);
        General.setupUI(findViewById(R.id.editting_client_information), EdittingClientInformation.this);

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
                Toast.makeText(EdittingClientInformation.this
                        , "Clicked", Toast.LENGTH_SHORT).show();
                dialog.show();
            }
        });


        //  Action bar back
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pullData();
    }


    private boolean validateHoten() {
        String usernameInput = textInputHoten.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputHoten.setError("Không thể để trống");
            return false;
        } else {
            textInputHoten.setError(null);
            return true;
        }
    }

    private boolean validateSdt() {
        String sdtInput = textInputSdt.getEditText().getText().toString().trim();

        if (sdtInput.isEmpty()) {
            textInputSdt.setError("Không thể để trống");
            return false;
        } else {
            if (sdtInput.length() != 10 && sdtInput.length() != 11) {
                textInputSdt.setError("Số điện thoại chưa đúng !");
                return false;
            } else {
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


    public void confirmInput(View v) {
        if (!validateHoten() | !validateSdt() | !validateDiachi()) {
            return;
        }


        updateClient();
        updateAcount();
        try {
//            Intent intent = new Intent(EdittingClientInformation.this, MainInfoCilent.class);
//            startActivity(intent);
            onBackPressed();
        } catch (Exception e) {
            Toast.makeText(EdittingClientInformation.this, "ss" + e, Toast.LENGTH_LONG).show();
        }


    }

    public void updateClient() {
        database.child("Clients").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Client client = dataSnapshot.getValue(Client.class);
                dataSnapshot.getRef().child("name").setValue(txtname.getText().toString());
                dataSnapshot.getRef().child("phone").setValue(txtsdt.getText().toString());
                dataSnapshot.getRef().child("address").setValue(txtdiachi.getText().toString());
                if(img != null) {
                    dataSnapshot.getRef().child("images").setValue(img.toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateAcount() {
        database.child("Accounts").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Client client = dataSnapshot.getValue(Client.class);
                dataSnapshot.getRef().child("name").setValue(txtname.getText().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void chooseFromGallery() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);

        } catch (Exception e) {
            Toast.makeText(EdittingClientInformation.this, "ss" + e, Toast.LENGTH_LONG).show();
        }
    }


    private void takeNewProfilePicture() {
        Activity profileFrag = this;
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        profileFrag.startActivityForResult(cameraintent, CAM_REQUEST);
    }


    public void profilepictureOnClick() {
        takeNewProfilePicture();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == CAM_REQUEST) {
            if (requestCode == CAM_REQUEST) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                img = General.encodeBitmap(thumbnail);
                //  picUri = data.getData();
                img_ava_patient.setImageBitmap(thumbnail);
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
                img = General.encodeBitmap(bitmap);
                img_ava_patient.setImageBitmap(bitmap);

                dialog.dismiss();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void pullData() {
        database.child("Clients").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Client client = dataSnapshot.getValue(Client.class);
                if (client != null) {
                    txtdiachi.setText(client.getAddress());
                    //txtemail.setText(client.getEmail());
                    txtname.setText(client.getName());
                    txtsdt.setText(client.getPhone());
                    if (client.getImages() != null) {
                        try {
                            Bitmap bitmap = General.decodeFromFirebaseBase64(client.getImages());
                            img = client.getImages();
                            Glide.with(EdittingClientInformation.this).load(bitmap).into(img_ava_patient);
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Lưu?")
                .setMessage("Bạn muốn lưu?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent_o_s = new Intent(EdittingClientInformation.this, MainInfoShop.class);
                        setResult(RESULT_OK, intent_o_s);
                        EdittingClientInformation.super.onBackPressed();
                    }
                }).create().show();
    }
}
