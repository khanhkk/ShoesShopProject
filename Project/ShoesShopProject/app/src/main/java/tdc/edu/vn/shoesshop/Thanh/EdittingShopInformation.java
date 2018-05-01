package tdc.edu.vn.shoesshop.Thanh;

import android.icu.text.UnicodeSetSpanner;
import android.support.design.widget.TextInputLayout;
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
import android.support.v7.widget.Toolbar;
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
import tdc.edu.vn.shoesshop.Bao.MainInfoCilent;
import tdc.edu.vn.shoesshop.Bao.MainInfoShop;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.HomeForShop;

public class EdittingShopInformation extends AppCompatActivity {
    private static final int CAM_REQUEST = 1313;
    private Dialog dialog;
    ImageButton btn_chooseImg,btn_takeaphoto;
    final int CROP_PIC = 2;
    private Uri picUri;
    private ImageButton btn_getimage;
    private Button btnSave;
    Intent intent;

    private TextInputLayout textInputTenshop;
    private TextInputLayout textInputNguoidaidien;
    private TextInputLayout textInputSdt;
    private TextInputLayout textInputDiachi;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputFacebook;
    private TextInputLayout textInputSotaikhoan;
    //private EditText edttenshop, edtsdt, edtdiachi, edtemail, edtnguoidaidien, edtfacebook, edtsotaikhoan;

    ImageView img_ava_patient;
    @Nullable
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editting_shop_information_activity);


        //final Button save = (Button) findViewById(R.id.btnSaveShopInformation);

        //EditText

        textInputEmail = findViewById(R.id.id_email);
        textInputTenshop = findViewById(R.id.id_hoten);
        textInputSdt = findViewById(R.id.id_sdt);
        textInputDiachi = findViewById(R.id.id_diachi);
        textInputNguoidaidien = findViewById(R.id.id_nguoidaidien);
        textInputFacebook = findViewById(R.id.id_Facebook);
        textInputSotaikhoan = findViewById(R.id.id_soTk);

//        edttenshop = (EditText) findViewById(R.id.ten_shop);
//        edtdiachi = (EditText) findViewById(R.id.dia_chi);
//        edtemail = (EditText) findViewById(R.id.email);
//        edtnguoidaidien = (EditText) findViewById(R.id.nguoidaidien_shop);
//        edtfacebook = (EditText) findViewById(R.id.facebook);
//        edtsdt = (EditText) findViewById(R.id.sdt);
//        edtsotaikhoan = (EditText) findViewById(R.id.sotaikhoan);

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EdittingShopInformation.this, MainInfoShop.class);
//                startActivity(intent);
//
//            }
//        });

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdittingShopInformation.this,MainInfoShop.class);
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

//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = edttenshop.getText().toString().trim();
//                String diachi = edtdiachi.getText().toString().trim();
//                String email = edtemail.getText().toString().trim();
//                String sdt = edtsdt.getText().toString().trim();
//                String sotaikhoan = edtsotaikhoan.getText().toString().trim();
//                String nguoidaidien = edtnguoidaidien.toString().trim();
//                String facebook = edtfacebook.toString().trim();
//                if (TextUtils.isEmpty(name)){
//                    Toast.makeText(getApplicationContext(), "Please enter name!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(sdt)){
//                    Toast.makeText(getApplicationContext(),"Please number phone!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(diachi)){
//                    Toast.makeText(getApplicationContext(), "Please enter Address!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(email)){
//                    Toast.makeText(getApplicationContext(), "Please email address!", Toast.LENGTH_LONG).show();
//                    return;}
//                if(TextUtils.isEmpty(nguoidaidien)){
//                    Toast.makeText(getApplicationContext(), "Please Enter The Representative!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(sotaikhoan)){
//                    Toast.makeText(getApplicationContext(), "Please Enter Your Account Number!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(facebook)){
//                    Toast.makeText(getApplicationContext(), "Please Enter Facebook!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (sdt.length() <  10 || sdt.length() > 11){
//                    Toast.makeText(getApplicationContext(), "Please Characters Between 10-11", Toast.LENGTH_LONG).show();
//                    edtsdt.setText("");
//                    edtsdt.requestFocus();
//                    return;
//                }
//                if (sotaikhoan.length() <  10 || sotaikhoan.length() > 11){
//                    Toast.makeText(getApplicationContext(), "Please Characters Between 10-11" + sotaikhoan.length()+sotaikhoan, Toast.LENGTH_LONG).show();
//                    edtsotaikhoan.setText("");
//                    edtsotaikhoan.requestFocus();
//                    return;
//                }
//                intent = getIntent();
//                intent.setClass(EdittingShopInformation.this, HomeForShop.class);
//                startActivity(intent);
//            }
//        });
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

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Không thể để trống");
            return false;
        } else {
            if(!emailInput.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            {
                textInputEmail.setError("Email không đúng !");
                return false;
            }else
            {
                textInputEmail.setError(null);
                return true;
            }

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


    private boolean validateHoten() {
        String usernameInput = textInputTenshop.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputTenshop.setError("Không thể để trống");
            return false;
        } else {
            textInputTenshop.setError(null);
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

    public void confirmInput(View v) {
        if (!validateEmail() | !validateHoten() | !validateSdt() | !validateDiachi() | !validateFacebook() | !validateNguoiDD() | !validateSotaikhoan()) {
            return;
        }

        String input =  "Địa chỉ: " + textInputDiachi.getEditText().getText().toString();
        input += "\n";
        input += "Họ Tên: " + textInputTenshop.getEditText().getText().toString();
        input += "\n";
        input += "Sdt: " + textInputSdt.getEditText().getText().toString();
        input += "\n";
        input += "Email: " + textInputEmail.getEditText().getText().toString();
        input += "\n";
        input += "Người đại diện: " + textInputNguoidaidien.getEditText().getText().toString();
        input += "\n";
        input += "Số tài khoản: " + textInputSotaikhoan.getEditText().getText().toString();
        input += "\n";
        input += "Facebook: " + textInputFacebook.getEditText().getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(EdittingShopInformation.this,MainInfoShop.class);
        startActivity(intent);
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
