package Adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Models.PromotionsDetail;
import tdc.edu.vn.shoesshop.R;



/**
 * Created by kk on 02/04/2018.
 */

public class PromotionsDetailAdapter extends ArrayAdapter<PromotionsDetail> {
private AppCompatActivity context = null;
private ArrayList<PromotionsDetail> list = null;
private int layoutId;

public PromotionsDetailAdapter(@NonNull AppCompatActivity context, @LayoutRes int resource, @NonNull ArrayList<PromotionsDetail> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.layoutId = resource;
        }

public static class ViewHolder
{
    public TextView tvCode, tvGift, tvDiscount;
    public ImageView imageView;
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
            viewHolder.tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            viewHolder.tvDiscount = (TextView)convertView.findViewById(R.id.tvDiscount);
            viewHolder.tvGift = (TextView)convertView.findViewById(R.id.tvGift);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImage);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        PromotionsDetail member = list.get(position);

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
}
