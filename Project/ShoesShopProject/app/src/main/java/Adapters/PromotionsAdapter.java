package Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Models.Promotion;
import Models.PromotionsDetail;
import tdc.edu.vn.shoesshop.EdittingPromotions;
import tdc.edu.vn.shoesshop.R;

/**
 * Created by kk on 01/04/2018.
 */

public class PromotionsAdapter  extends ArrayAdapter<Promotion> {

    private AppCompatActivity context = null;
    private ArrayList<Promotion> list = null;
    private int layoutId;

    public PromotionsAdapter(@NonNull AppCompatActivity context, @LayoutRes int resource, @NonNull ArrayList<Promotion> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.layoutId = resource;
    }

    public static class ViewHolder {
        public TextView tvNamePromotions    ;
        public TextView tvTimeStart;
        public TextView tvTimeEnd;
        public ImageView imageView;
        public ImageButton btnEdit, btnAdd, btnDetail;
        public ListView lvListDetail;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        final LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvNamePromotions = (TextView) convertView.findViewById(R.id.tvNameProduct);
            viewHolder.tvTimeEnd = (TextView) convertView.findViewById(R.id.tvTimeEnd);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgImagePromotions);
            viewHolder.tvTimeStart = (TextView) convertView.findViewById(R.id.tvTimeStart);
            viewHolder.btnAdd = (ImageButton) convertView.findViewById(R.id.btnAddElementPromotions);
            viewHolder.btnEdit = (ImageButton) convertView.findViewById(R.id.btnEditPromotions);
            viewHolder.btnDetail = (ImageButton) convertView.findViewById(R.id.btnViewDetailPromotions);
            viewHolder.lvListDetail = (ListView) convertView.findViewById(R.id.lvListPromotionsElement);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Promotion member = list.get(position);

//        viewHolder.btnEdit.setTag(member.getId());
//        viewHolder.btnAdd.setTag(member.getId());

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
                Intent intent = new Intent(getContext(), EdittingPromotions.class);
                Bundle bundle = new Bundle();
                Log.d("id", member.getId() + "");
                bundle.putString("data",member.getId()+"");
                intent.putExtra("member",bundle);
                getContext().startActivity(intent);
            }
        });

        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.lvListDetail.getVisibility() != View.VISIBLE) {
                    ArrayList<PromotionsDetail> list = member.getListDetail();
                    if (member.getListDetail() != null) {
                        viewHolder.lvListDetail.setVisibility(View.VISIBLE);
                        PromotionsDetailAdapter apdapter = new PromotionsDetailAdapter(context, R.layout.promotions_detail_item_layout, member.getListDetail());
                        viewHolder.lvListDetail.setAdapter(apdapter);
                    }
                }
                else
                {
                    viewHolder.lvListDetail.setVisibility(View.GONE);
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
