package tdc.edu.vn.shoesshop.Bao;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import Models.Notification;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ChangePassword;
import tdc.edu.vn.shoesshop.Sang.ListOder;

public class PersonalOfClientLoginedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = inflater.inflate(R.layout.personal_of_client_logined_fragment, container, false);


        final FirebaseAuth auth = FirebaseAuth.getInstance();

        // lịch sử mua hàng
        ImageView imglichsu = (ImageView) view.findViewById(R.id.imglichsu);
        imglichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListOder.class);
                startActivity (intent);
            }
        });
        TextView txtlichsu = (TextView) view.findViewById(R.id.txtlichsu);
        txtlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListOder.class);
                startActivity (intent);
            }
        });

        // thông tin khách hàng
        ImageView imgthongtin = (ImageView) view.findViewById(R.id.imgthongtin);
        imgthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainInfoCilent.class);
                startActivity (intent);
            }
        });
        TextView txtthongtin = (TextView) view.findViewById(R.id.txtthongtin);
        txtthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainInfoCilent.class);
                startActivity (intent);
            }
        });

        // đổi mật khẩu
        ImageView imgdoimk = (ImageView) view.findViewById(R.id.imgdoimk);
        imgdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity (intent);
            }
        });
        TextView txtdoimk = (TextView) view.findViewById(R.id.txtdoimk);
        txtdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity (intent);
            }
        });

        // đăng xuất
        ImageView imglogout = (ImageView) view.findViewById(R.id.imglogoutclient);
        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), MainCustomer.class);
                startActivity (intent);
            }
        });
        TextView txtlogout = (TextView) view.findViewById(R.id.txtlogoutclient);
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), MainCustomer.class);
                startActivity (intent);
            }
        });

        return view;
    }
}
