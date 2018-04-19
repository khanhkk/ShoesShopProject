package tdc.edu.vn.shoesshop.Thanh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controls.General;
import tdc.edu.vn.shoesshop.Khanh.Promotions;
import tdc.edu.vn.shoesshop.Khanh.SelectionProductToEditting;
import tdc.edu.vn.shoesshop.R;

public class EditingPromotionDetail extends AppCompatActivity {

    Button btnSave;
    Intent intent;
    EditText edtsanpham, edtquatang, edtgiamgia, edtgiaban, edtgiauudai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editing_promotion_detail_activity);

        General.setupUI(findViewById(R.id.promotion_detail), EditingPromotionDetail.this);
        btnSave = (Button) findViewById(R.id.btnSavePromotionsDetail);

        edtsanpham = (EditText) findViewById(R.id.sanpham);
        edtquatang = (EditText) findViewById(R.id.quatang);
        edtgiamgia = (EditText) findViewById(R.id.giamgia);
        edtgiaban = (EditText) findViewById(R.id.giaban_promotion);
        edtgiauudai = (EditText) findViewById(R.id.giauudai);
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle != null)
        {}



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sanpham = edtsanpham.getText().toString().trim();
                String quatang = edtquatang.getText().toString().trim();
                String giamgia = edtgiamgia.getText().toString().trim();
                int giaban = Integer.parseInt(edtgiaban.getText().toString().trim());
                int giauudai = Integer.parseInt(edtgiauudai.getText().toString().trim());
                if(TextUtils.isEmpty(sanpham)){
                    Toast.makeText(getApplicationContext(), "Please enter product", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(quatang)){
                    Toast.makeText(getApplicationContext(), "Please enter promotion", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(giamgia)){
                    Toast.makeText(getApplicationContext(), "Please enter sale", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (giaban <  100000 || giaban > 200000){
                    Toast.makeText(getApplicationContext(), "Please Characters Between 100000-200000", Toast.LENGTH_LONG).show();                    edtgiaban.setText("");
                    edtgiaban.requestFocus();
                    return;
                }
                if(giauudai < 100000 || giauudai > 200000){
                    Toast.makeText(getApplicationContext(), "Please Characters Between 100000-200000", Toast.LENGTH_SHORT).show();
                    edtgiauudai.setText("");
                    edtgiauudai.requestFocus();
                    return;
                }
                intent.setClass(EditingPromotionDetail.this, Promotions.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });
    }
}
