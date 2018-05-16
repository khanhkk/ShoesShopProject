package tdc.edu.vn.shoesshop.Bao;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import tdc.edu.vn.shoesshop.Khanh.Promotions;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ChangePassword;
import tdc.edu.vn.shoesshop.Son.NotificationShopFragment;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;

public class PersonalOfShopFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        View view = null;
        view = inflater.inflate(R.layout.personal_of_shop_fragment, container, false);

        // thông tin khuyến mãi
        ImageView imgkhuyenmai = (ImageView) view.findViewById(R.id.imgkhuyenmai);
        imgkhuyenmai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Promotions.class);
                startActivity (intent);
            }
        });
        TextView txtkhuyenmai = (TextView) view.findViewById(R.id.txtkhuyenmai);
        txtkhuyenmai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Promotions.class);
                startActivity (intent);
            }
        });

        // thông tin shop
        ImageView imgthongtin = (ImageView) view.findViewById(R.id.imgthongtin);
        imgthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainInfoShop.class);
                startActivity (intent);
            }
        });
        TextView txtthongtin = (TextView) view.findViewById(R.id.txtthongtin);
        txtthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainInfoShop.class);
                startActivity (intent);
            }
        });

        // thông báo
        ImageView imgtbshop = (ImageView) view.findViewById(R.id.imgtbshop);
        imgtbshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        TextView txttbshop = (TextView) view.findViewById(R.id.txttbshop);
        txttbshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // đăng xuát
        ImageView imglogout = (ImageView) view.findViewById(R.id.imglogout);
        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity (intent);
            }
        });
        TextView txtlogout = (TextView) view.findViewById(R.id.txtlogout);
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity (intent);
            }
        });

        return view;
    }


}
