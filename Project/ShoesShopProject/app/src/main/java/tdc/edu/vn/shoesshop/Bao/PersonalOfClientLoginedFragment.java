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
import tdc.edu.vn.shoesshop.Son.NotificationFragment;
import tdc.edu.vn.shoesshop.Toan.LoginActivity;


public class PersonalOfClientLoginedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = inflater.inflate(R.layout.personal_of_client_logined_fragment, container, false);


        final FirebaseAuth auth = FirebaseAuth.getInstance();

        ImageView imglichsu = (ImageView) view.findViewById(R.id.imglichsu);
        TextView txtlichsu = (TextView) view.findViewById(R.id.txtlichsu);
        txtlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListOder.class);
                startActivity (intent);
                Toast.makeText(getActivity(), " Lịch sử mua hàng " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgthongtin = (ImageView) view.findViewById(R.id.imgthongtin);
        TextView txtthongtin = (TextView) view.findViewById(R.id.txtthongtin);
        txtthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainInfoCilent.class);
                startActivity (intent);
                Toast.makeText(getActivity(), " Thông tin của tôi " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgdoimk = (ImageView) view.findViewById(R.id.imgdoimk);
        TextView txtdoimk = (TextView) view.findViewById(R.id.txtdoimk);
        txtdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity (intent);
                Toast.makeText(getActivity(), " Đổi mật khẩu " , Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imglogout = (ImageView) view.findViewById(R.id.imglogout);
        TextView txtlogout = (TextView) view.findViewById(R.id.txtlogout);
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), MainCustomer.class);
                startActivity (intent);
                Toast.makeText(getActivity(), " Đăng xuất " , Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
