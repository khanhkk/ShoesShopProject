package Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Product;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.Khanh.SelectionProductToEditting;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Sang.QuantityManagement;
import tdc.edu.vn.shoesshop.Thanh.DetailInformationOfProduct;

/**
 * Created by ITLAB on 4/10/2018.
 */

public class ProductExpandListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private ArrayList<Product> _listDataHeader; // header titles
    private LayoutInflater inflater;
    HashMap<Product,ArrayList<ProductDetail>> _childList;

    public ProductExpandListAdapter(Context context, ArrayList<Product> listDataHeader, HashMap<Product, ArrayList<ProductDetail>> list) {
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
        public TextView tvColor, tvSize, tvQuantity;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(getChildrenCount(groupPosition) > 0) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_detail_item_layout, null);
            viewHolder = new ChildViewHolder();
            viewHolder.tvSize = (TextView) convertView.findViewById(R.id.tvSize);
            viewHolder.tvColor = (TextView)convertView.findViewById(R.id.tvColor);
            viewHolder.tvQuantity = (TextView)convertView.findViewById(R.id.tvQuantity);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
            ProductDetail member = (ProductDetail) getChild(groupPosition, childPosition);
            viewHolder.tvColor.setText(member.getColor());
            viewHolder.tvSize.setText(member.getSize() + "");
            viewHolder.tvQuantity.setText(member.getQuantity() + "");
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int quanity = 0;
         if(this._childList.get(_listDataHeader.get(groupPosition)) != null)
             quanity = this._childList.get(_listDataHeader.get(groupPosition)).size();
        return quanity;
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
        public TextView tvNameproduct;
        public TextView tvListedPrice;
        public TextView tvSalePrice;
        public ImageView imageView;
        public ImageButton btnEdit, btnAdd, btnDelete;
        public LinearLayout llListElement;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.d("parent", groupPosition + "");
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvNameproduct = (TextView) convertView.findViewById(R.id.tvNameProduct);
            viewHolder.tvListedPrice = (TextView) convertView.findViewById(R.id.tvListedPrice);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImageProduct);
            viewHolder.tvSalePrice = (TextView) convertView.findViewById(R.id.tvSalePrice);
            viewHolder.btnAdd = (ImageButton) convertView.findViewById(R.id.btnAddElement);
            viewHolder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEditElement);
            viewHolder.btnDelete = (ImageButton) convertView.findViewById(R.id.btnDeleteElement);
            viewHolder.llListElement = (LinearLayout)convertView.findViewById(R.id.llDanhSach);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Product member = (Product) getGroup(groupPosition);
        Log.d("gia", member.getSalePrice() + "");
        viewHolder.tvNameproduct.setText(member.getName()+"");
        viewHolder.tvSalePrice.setText(member.getSalePrice() + "");
        viewHolder.tvListedPrice.setText(member.getListedPrice() + "");
        viewHolder.tvListedPrice.setTextColor(_context.getResources().getColor(R.color.bg_register));
        viewHolder.tvListedPrice.setPaintFlags(viewHolder.tvListedPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, QuantityManagement.class);
                _context.startActivity(intent);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(_context,"dsafds",Toast.LENGTH_SHORT).show();
                _childList.remove(_listDataHeader.get(groupPosition));
                _listDataHeader.remove(_listDataHeader.get(groupPosition));
                SelectionProductToEditting.adapter.notifyDataSetChanged();
            }
        });

        if(isExpanded)
        {
            viewHolder.llListElement.setVisibility(View.VISIBLE);
        }
        else
        {
            viewHolder.llListElement.setVisibility(View.GONE);

        }

        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, DetailInformationOfProduct.class);
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
