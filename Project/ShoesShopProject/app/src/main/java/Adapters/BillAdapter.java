package Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Controls.General;
import Models.BillDetail;
import Models.Product;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;



/**
 * Created by kk on 30/03/2018.
 */

public class BillAdapter extends ArrayAdapter<BillDetail> {

    private Activity context = null;
    private ArrayList<BillDetail> list = null;
    private int layoutId;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public BillAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<BillDetail> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.layoutId = resource;
    }

    public static class ViewHolder {
        public TextView tvCode;
        public TextView tvPrice;
        public TextView tvQuantity;
        public Button btnSub, btnAdd, btnDelete;
        public ImageView imgImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        final LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            viewHolder.tvQuantity = (TextView) convertView.findViewById(R.id.tvQuantity);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            viewHolder.btnAdd = (Button) convertView.findViewById(R.id.btnAdd);
            viewHolder.btnSub = (Button) convertView.findViewById(R.id.btnSub);
            viewHolder.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
            viewHolder.imgImage = (ImageView) convertView.findViewById(R.id.imgImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final BillDetail member = list.get(position);



        database.child("Products").orderByChild("id").equalTo(member.getProduct()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                final Product product = dataSnapshot.getValue(Product.class);
                viewHolder.tvCode.setText(product.getName());
                viewHolder.tvPrice.setText(product.getSalePrice() + "");

                if(product.getImage1() != null)
                {
                    try {
                        Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage1());
                        viewHolder.imgImage.setImageBitmap(bitmap);
                    }catch (Exception ex)
                    {

                    }
                }
                else if(product.getImage2() != null)
                {
                    try {
                        Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage2());
                        viewHolder.imgImage.setImageBitmap(bitmap);
                    }catch (Exception ex)
                    {

                    }
                }
                else if(product.getImage3() != null)
                {
                    try {
                        Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage3());
                        viewHolder.imgImage.setImageBitmap(bitmap);
                    }catch (Exception ex)
                    {

                    }
                }

                viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int s = Integer.parseInt(viewHolder.tvQuantity.getText() + "");
                        s++;
                        if (s <= 20) {
                            member.setQuantity(s);
                            viewHolder.tvQuantity.setText(member.getQuantity() + "");
                            tinhTong(product);

                            database.child("Clients").child(user.getUid()).child("Cart").orderByChild("id").equalTo(member.getId()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    database.child("Clients").child(user.getUid()).child("Cart").child(dataSnapshot.getKey()).child("quantity").setValue(member.getQuantity());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                });

                viewHolder.btnSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int s = Integer.parseInt(viewHolder.tvQuantity.getText() + "");
                        s--;
                        if (s > 0) {
                            member.setQuantity(s);
                            viewHolder.tvQuantity.setText(member.getQuantity() + "");
                            tinhTong(product);

                            database.child("Clients").child(user.getUid()).orderByChild("id").equalTo(member.getId()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    database.child("Clients").child(user.getUid()).child("Cart").child(dataSnapshot.getKey()).child("quantity").setValue(member.getQuantity());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                });

                viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(member);
                        BillAdapter.super.notifyDataSetChanged();
                        tinhTong(product);
                    }
                });

                tinhTong(product);
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

        viewHolder.tvQuantity.setText(member.getQuantity() + "");
        //viewHolder.imgImage.setImageResource(member.getProduct().getImage1());

        return convertView;
    }
    private void tinhTong(Product pro)
    {
        double money = 0;
        if(list.size() != 0) {
                for (BillDetail item : list) {
                money += (item.getQuantity() * pro.getSalePrice());
            }
        }
        else
        {
            money = 0;
        }
        Cart.tvMoney.setText(money + "");
    }
}


