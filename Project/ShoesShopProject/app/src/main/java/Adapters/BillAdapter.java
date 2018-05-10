package Adapters;

import android.app.Activity;
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

import java.util.ArrayList;

import Models.BillDetail;
import tdc.edu.vn.shoesshop.Khanh.Cart;
import tdc.edu.vn.shoesshop.R;



/**
 * Created by kk on 30/03/2018.
 */

public class BillAdapter extends ArrayAdapter<BillDetail> {

    private Activity context = null;
    private ArrayList<BillDetail> list = null;
    private int layoutId;

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

        viewHolder.tvCode.setText(member.getProduct().getName());
        viewHolder.tvPrice.setText(member.getProduct().getSalePrice() + "");
        viewHolder.tvQuantity.setText(member.getQuantity() + "");
        //viewHolder.imgImage.setImageResource(member.getProduct().getImage1());
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(viewHolder.tvQuantity.getText() + "");
                s++;
                if (s <= 20) {
                    member.setQuantity(s);
                    viewHolder.tvQuantity.setText(member.getQuantity() + "");
                    tinhTong();
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
                    tinhTong();
                }
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(member);
                BillAdapter.super.notifyDataSetChanged();
                tinhTong();
            }
        });

        tinhTong();
        return convertView;
    }
    private void tinhTong()
    {
        double money = 0;
        if(list.size() != 0) {
                for (BillDetail item : list) {
                money += (item.getQuantity() * item.getProduct().getSalePrice());
            }
        }
        else
        {
            money = 0;
        }
        Cart.tvMoney.setText(money + "");
    }
}


