package Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import tdc.edu.vn.shoesshop.EdittingPromotions;
import tdc.edu.vn.shoesshop.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private AppCompatActivity _context;
    private ArrayList<Promotion> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<Promotion, ArrayList<PromotionsDetail>> _listDataChild;

    public ExpandableListAdapter(AppCompatActivity context, ArrayList<Promotion> listDataHeader,HashMap<Promotion, ArrayList<PromotionsDetail>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
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
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.promotions_detail_item_layout, null);
            viewHolder = new ChildViewHolder();
            viewHolder.tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            viewHolder.tvDiscount = (TextView)convertView.findViewById(R.id.tvDiscount);
            viewHolder.tvGift = (TextView)convertView.findViewById(R.id.tvGift);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImage);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }


        final PromotionsDetail member = (PromotionsDetail) getChild(groupPosition, childPosition);

        viewHolder.tvCode.setText(member.getProduct().getId());
        if(member.getDiscount() == 0) {
            viewHolder.tvDiscount.setText("Không giảm giá!");
        }
        else
        {
            viewHolder.tvDiscount.setText(member.getDiscount() + "");
        }

        if(member.getGift() == null)
        {
            viewHolder.tvGift.setText("Không có quà tặng!");
        }
        else
        {
            viewHolder.tvGift.setText(member.getGift());
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.promotions_layout_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvNamePromotions = (TextView) convertView.findViewById(R.id.tvNameProduct);
            viewHolder.tvTimeEnd = (TextView) convertView.findViewById(R.id.tvTimeEnd);
            //viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImagePromotions);
            viewHolder.tvTimeStart = (TextView) convertView.findViewById(R.id.tvTimeStart);
            viewHolder.btnAdd = (ImageButton) convertView.findViewById(R.id.btnAddElementPromotions);
            viewHolder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEditPromotions);
            //viewHolder.lvListDetail = (ListView) convertView.findViewById(R.id.lvListPromotionsElement);
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
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
