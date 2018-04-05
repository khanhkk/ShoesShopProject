package Adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Product;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;

/**
 * Created by kk on 31/03/2018.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private AppCompatActivity context = null;
    private ArrayList<Product> list = null;
    private int layoutId;

    public ProductAdapter(@NonNull AppCompatActivity context, @LayoutRes int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.layoutId = resource;
    }

    public static class ViewHolder {
        public TextView tvNameproduct;
        public TextView tvListedPrice;
        public TextView tvSalePrice;
        public ImageView imageView;
        public ImageButton btnEdit, btnAdd, btnDetail;
        public ListView lvListDetail;
        public LinearLayout llListElement;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        final LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvNameproduct = (TextView) convertView.findViewById(R.id.tvNameProduct);
            viewHolder.tvListedPrice = (TextView) convertView.findViewById(R.id.tvListedPrice);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImageProduct);
            viewHolder.tvSalePrice = (TextView) convertView.findViewById(R.id.tvSalePrice);
            viewHolder.btnAdd = (ImageButton) convertView.findViewById(R.id.btnAddElement);
            viewHolder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEditElement);
            viewHolder.btnDetail = (ImageButton) convertView.findViewById(R.id.btnView);
            viewHolder.lvListDetail = (ListView) convertView.findViewById(R.id.lvListElement);
            viewHolder.llListElement = (LinearLayout)convertView.findViewById(R.id.llDanhSach);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Product member = list.get(position);

        viewHolder.tvNameproduct.setText(member.getName());
        viewHolder.tvSalePrice.setText(member.getSalePrice() + "");
        viewHolder.tvListedPrice.setText(member.getListedPrice() + "");
        //viewHolder.imageView.setImageResource(member.getImage1());
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.llListElement.getVisibility() != View.VISIBLE) {
                    ArrayList<ProductDetail> list = member.getList();
                    if (member.getList() != null) {
                        viewHolder.llListElement.setVisibility(View.VISIBLE);
                        ProductDetailApdapter apdapter = new ProductDetailApdapter(context, R.layout.product_detail_item_layout, member.getList());
                        viewHolder.lvListDetail.setAdapter(apdapter);
                    }
                }
                else
                {
                    viewHolder.llListElement.setVisibility(View.GONE);
                }
                if(viewHolder.btnDetail.isSelected())
                {
                    viewHolder.btnDetail.setSelected(false);
                }
                else
                {
                    viewHolder.btnDetail.setSelected(true);
                }
            }
        });

        return convertView;
    }

}
