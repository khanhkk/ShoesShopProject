package tdc.edu.vn.shoesshop.Bao;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.Client;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.ChangePassword;
import tdc.edu.vn.shoesshop.Sang.ListOder;

public class PersonalOfClientLoginedFragment extends Fragment {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    TextView nameClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = inflater.inflate(R.layout.personal_of_client_logined_fragment, container, false);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        nameClient = (TextView) view.findViewById(R.id.txtName);
        data();
        // lịch sử mua hàng
        ImageView imglichsu = (ImageView) view.findViewById(R.id.imglichsu);
        imglichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListOder.class);
                startActivity(intent);
            }
        });
        TextView txtlichsu = (TextView) view.findViewById(R.id.txtlichsu);
        txtlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListOder.class);
                startActivity(intent);
            }
        });

        // thông tin khách hàng
        ImageView imgthongtin = (ImageView) view.findViewById(R.id.imgthongtin);
        imgthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainInfoCilent.class);
                startActivity(intent);
            }
        });
        TextView txtthongtin = (TextView) view.findViewById(R.id.txtthongtin);
        txtthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainInfoCilent.class);
                startActivity(intent);
            }
        });

        // đổi mật khẩu
        ImageView imgdoimk = (ImageView) view.findViewById(R.id.imgdoimk);
        imgdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });
        TextView txtdoimk = (TextView) view.findViewById(R.id.txtdoimk);
        txtdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });

        // đăng xuất
        ImageView imglogout = (ImageView) view.findViewById(R.id.imglogoutclient);
        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), MainCustomer.class);
                startActivity(intent);
            }
        });
        TextView txtlogout = (TextView) view.findViewById(R.id.txtlogoutclient);
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), MainCustomer.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void data() {


        database.child("Clients").orderByKey().equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Client client = dataSnapshot.getValue(Client.class);
                nameClient.setText(client.getName());
//                txtsdt.setText("Số điện thoại:  " + client.getPhone());
//                txtemail.setText("Email:  " + client.getEmail());
//                txtdiachi.setText("Địa chỉ:  " + client.getAddress());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        database.child("Accounts").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Account account = dataSnapshot.getValue(Account.class);
//                if ((account.getLevel() == 0) || (user == null))
//                    button.setVisibility(View.INVISIBLE);
//                else
//                    button.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
