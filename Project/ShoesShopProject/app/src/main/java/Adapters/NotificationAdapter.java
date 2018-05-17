package Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import Controls.General;
import Models.Notification;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.OrderInformationForShop;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<Notification> mData;

    public NotificationAdapter(Context mContext, ArrayList<Notification> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.notification_fragment_custom, parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(v);

        viewHolder.item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"TT"+String.valueOf(viewHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,OrderInformationForShop.class);
                String s = mData.get(viewHolder.getAdapterPosition()).getBill();
                intent.putExtra("bill", s);
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //holder.tv_name.setText(mData.get(position).getClient());
        holder.tv_active.setText(mData.get(position).getHoatdong());
        holder.tv_date.setText(mData.get(position).getThoiGian());
        if(mData.get(position).getHinh() != null)
        {
            Bitmap bitmap = null;
            try {
                bitmap = General.decodeFromFirebaseBase64(mData.get(position).getHinh());
                holder.img_hinh.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //holder.img_hinh.setImageResource(mData.get(position).getHinh());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_contact;
        //private TextView tv_name;
        private TextView tv_active;
        private ImageView img_hinh;
        private TextView tv_date;
        public MyViewHolder(View itemView) {

            super(itemView);
            item_contact = (LinearLayout) itemView.findViewById(R.id.id_item);
            //tv_name = (TextView) itemView.findViewById(R.id.id_name);
            tv_active = (TextView) itemView.findViewById(R.id.id_active);
            tv_date = (TextView) itemView.findViewById(R.id.id_date);
            img_hinh = (ImageView) itemView.findViewById(R.id.id_img);
        }
    }

}
