package Adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;

/**
 * Created by kk on 01/04/2018.
 */

public class ProductDetailApdapter extends ArrayAdapter<ProductDetail> {
    private AppCompatActivity context = null;
    private ArrayList<ProductDetail> list = null;
    private int layoutId;

    public ProductDetailApdapter(@NonNull AppCompatActivity context, @LayoutRes int resource, @NonNull ArrayList<ProductDetail> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.layoutId = resource;
    }

    public static class ViewHolder
    {
        public TextView tvColor, tvSize, tvQuantity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView == null)
        {
            convertView = inflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvColor = (TextView) convertView.findViewById(R.id.tvColor);
            viewHolder.tvSize = (TextView)convertView.findViewById(R.id.tvSize);
            viewHolder.tvQuantity = (TextView)convertView.findViewById(R.id.tvQuantity);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        ProductDetail member = list.get(position);

        viewHolder.tvColor.setText(member.getColor());
        viewHolder.tvSize.setText(member.getSize()+"");
        viewHolder.tvQuantity.setText(member.getQuantity()+"");
        return convertView;
    }
}
