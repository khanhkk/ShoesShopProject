package Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Controls.General;
import Models.Product;
import Models.Promotion;
import Models.PromotionsDetail;
import tdc.edu.vn.shoesshop.Khanh.EdittingPromotions;
import tdc.edu.vn.shoesshop.Khanh.Promotions;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.EditingPromotionDetail;

public class PromotionExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private ArrayList<Promotion> _listDataHeader; // header titles
    private LayoutInflater inflater;
    HashMap<Promotion,ArrayList<PromotionsDetail>> _childList;
    private ArrayList<Product> listProduct;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public PromotionExpandableListAdapter(Context context, ArrayList<Promotion> listDataHeader, HashMap<Promotion, ArrayList<PromotionsDetail>> list) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this._childList = list;

        listProduct = new ArrayList<>();
        myRef.child("Products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                listProduct.add(product);
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

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._childList.get(_listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public static class ChildViewHolder
    {
        public TextView tvCode, tvGift, tvDiscount, tvPoint;
        public ImageView imageView;
        public LinearLayout llGift, llDiscount, llPoint;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder viewHolder;
        if(getChildrenCount(groupPosition) > 0) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.promotions_detail_item_layout, null);
                viewHolder = new ChildViewHolder();
                viewHolder.tvCode = (TextView) convertView.findViewById(R.id.tvCode);
                viewHolder.tvDiscount = (TextView) convertView.findViewById(R.id.tvDiscount);
                viewHolder.tvGift = (TextView) convertView.findViewById(R.id.tvGift);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImage);
                viewHolder.tvPoint = (TextView) convertView.findViewById(R.id.tvPoint);

                viewHolder.llGift = (LinearLayout) convertView.findViewById(R.id.llGift);
                viewHolder.llDiscount = (LinearLayout) convertView. findViewById(R.id.llDiscount);
                viewHolder.llPoint = (LinearLayout) convertView.findViewById(R.id.llPoint);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ChildViewHolder) convertView.getTag();
            }

            final PromotionsDetail member = (PromotionsDetail) getChild(groupPosition, childPosition);

            if(listProduct.size() > 0)
            {
                for ( Product p : listProduct) {
                    if(p.getId().equals(member.getProduct()))
                    {
                        viewHolder.tvCode.setText(p.getName());
                    }
                }
            }

            if (member.getDiscount() <= 0) {
                viewHolder.llDiscount.setVisibility(View.GONE);
            }
            else
            {
                viewHolder.llDiscount.setVisibility(View.VISIBLE);
                viewHolder.tvDiscount.setText(member.getDiscount() + "");
            }

            if (member.getGift().length() == 0) {
                viewHolder.llGift.setVisibility(View.GONE);
            }
            else
            {
                viewHolder.llGift.setVisibility(View.VISIBLE);
                viewHolder.tvGift.setText(member.getGift());
            }

            if(member.getPoint() <= 0)
            {
                viewHolder.llPoint.setVisibility(View.GONE);
            }
            else
            {
                viewHolder.llPoint.setVisibility(View.VISIBLE);
                viewHolder.tvPoint.setText(member.getPoint()+"");
            }
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int quantity = 0;
        if(this._childList.get(_listDataHeader.get(groupPosition)) != null)
            quantity = this._childList.get(_listDataHeader.get(groupPosition)).size();
        return quantity;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public static class ViewHolder {
        public TextView tvNamePromotions    ;
        public TextView tvTimeStart;
        public TextView tvTimeEnd;
        public ImageView imageView;
        public ImageButton btnEdit, btnAdd, btnDelete;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.d("parent", groupPosition + "");
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.promotions_layout_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvNamePromotions = (TextView) convertView.findViewById(R.id.tvNameProduct);
            viewHolder.tvTimeEnd = (TextView) convertView.findViewById(R.id.tvTimeEnd);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImagePromotions);
            viewHolder.tvTimeStart = (TextView) convertView.findViewById(R.id.tvTimeStart);
            viewHolder.btnAdd = (ImageButton) convertView.findViewById(R.id.btnAddElementPromotions);
            viewHolder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEditPromotions);
            viewHolder.btnDelete = (ImageButton) convertView.findViewById(R.id.btnDeletePromotions);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Promotion member = (Promotion) getGroup(groupPosition);

        viewHolder.tvNamePromotions.setText(member.getTitle());
        viewHolder.tvTimeStart.setText(member.getDateStart());
        viewHolder.tvTimeEnd.setText(member.getDateEnd());
        if(member.getImage() != null)
        {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(member.getImage());
                viewHolder.imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            viewHolder.imageView.setImageResource(R.mipmap.promotions2);
        }


        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, EditingPromotionDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", String.valueOf(member.getId()));
                intent.putExtra("data", bundle);
                _context.startActivity(intent);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.child("PromotionsDetail").orderByChild("promotions").equalTo(member.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            child.getRef().setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myRef.child("Promotions").orderByChild("id").equalTo(member.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        myRef.child("PromotionsDetails").orderByChild("promotions").equalTo(member.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot child: dataSnapshot.getChildren()) {
                                    child.getRef().setValue(null);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            child.getRef().setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                _childList.remove(_listDataHeader.get(groupPosition));
                _listDataHeader.remove(groupPosition);
                Promotions.adapter.notifyDataSetChanged();
            }
        });

        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, EdittingPromotions.class);
                Bundle bundle = new Bundle();
                Log.d("id", member.getId() + "");
                bundle.putString("data",member.getId()+"");
                intent.putExtra("member",bundle);
                _context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
