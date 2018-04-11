package Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Models.Promotion;
import Models.PromotionsDetail;
import tdc.edu.vn.shoesshop.Thanh.EditingPromotionDetail;
import tdc.edu.vn.shoesshop.Khanh.EdittingPromotions;
import tdc.edu.vn.shoesshop.R;

public class PromotionExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private ArrayList<Promotion> _listDataHeader; // header titles
    private LayoutInflater inflater;
    HashMap<Promotion,ArrayList<PromotionsDetail>> _childList;

    public PromotionExpandableListAdapter(Context context, ArrayList<Promotion> listDataHeader, HashMap<Promotion, ArrayList<PromotionsDetail>> list) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this._childList = list;
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
        public TextView tvCode, tvGift, tvDiscount;
        public ImageView imageView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if(getChildrenCount(groupPosition) > 0) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.promotions_detail_item_layout, null);
                viewHolder = new ChildViewHolder();
                viewHolder.tvCode = (TextView) convertView.findViewById(R.id.tvCode);
                viewHolder.tvDiscount = (TextView) convertView.findViewById(R.id.tvDiscount);
                viewHolder.tvGift = (TextView) convertView.findViewById(R.id.tvGift);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImage);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ChildViewHolder) convertView.getTag();
            }

            PromotionsDetail member = (PromotionsDetail) getChild(groupPosition, childPosition);

            viewHolder.tvCode.setText(member.getProduct().getId());
            if (member.getDiscount() == 0) {
                viewHolder.tvDiscount.setText("Không giảm giá!");
            } else {
                viewHolder.tvDiscount.setText(member.getDiscount() + "");
            }

            if (member.getGift() == null) {
                viewHolder.tvGift.setText("Không có quà tặng!");
            } else {
                viewHolder.tvGift.setText(member.getGift());
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
        //public ImageView imageView;
        public ImageButton btnEdit, btnAdd;
        //public ListView lvListDetail;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.d("parent", groupPosition + "");
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.promotions_layout_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvNamePromotions = (TextView) convertView.findViewById(R.id.tvNameProduct);
            viewHolder.tvTimeEnd = (TextView) convertView.findViewById(R.id.tvTimeEnd);
            //viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImagePromotions);
            viewHolder.tvTimeStart = (TextView) convertView.findViewById(R.id.tvTimeStart);
            viewHolder.btnAdd = (ImageButton) convertView.findViewById(R.id.btnAddElementPromotions);
            viewHolder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEditPromotions);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Promotion member = (Promotion) getGroup(groupPosition);

        viewHolder.tvNamePromotions.setText(member.getTitle());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        viewHolder.tvTimeStart.setText(format.format(member.getDateStart()));
        viewHolder.tvTimeEnd.setText(format.format(member.getDateEnd()));

        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, EditingPromotionDetail.class);
                _context.startActivity(intent);
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
