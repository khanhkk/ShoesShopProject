package Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Controls.General;
import Models.Product;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.Info_product;

public class RecyclerViewAdapter_Client extends RecyclerView.Adapter<RecyclerViewAdapter_Client.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
//    var
    private Context mContext;
    private ArrayList<Product> list;
    private ArrayList<ProductDetail> listDetail;

    public RecyclerViewAdapter_Client(Context mContext, ArrayList<Product> list, ArrayList<ProductDetail> listDetail) {
        this.mContext = mContext;
        this.list = list;
        this.listDetail = listDetail;
    }

    @Override
    public RecyclerViewAdapter_Client.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_pro_user, parent, false);
        return new RecyclerViewAdapter_Client.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_Client.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImageUrls.get(position))
//                .into(holder.image);
//        holder.image.setImageResource(mImageUrls.get(position));
        final Product product = list.get(position);
        holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.name.setText(product.getName());
        holder.ratingBar.setRating(product.getRating());
        int soluong = 0;

        for(ProductDetail pro : listDetail)
        {
            if(pro.getProduct().equals(product.getId()))
            {
                soluong += pro.getQuantity();
            }
            holder.count.setText("("+ String.valueOf(soluong)+")");
        }
        //  Log.d("da", soluong+"");
        long percent_a;

        percent_a = Math.round(100 - 100 * product.getSalePrice() /product.getListedPrice());
        holder.percent.setText("-" + String.valueOf(percent_a) + "%");
        //format 1
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#,### đ");

        holder.sells.setText(df.format((product.getSalePrice())));
        holder.cost.setText(df.format((product.getListedPrice())));
        holder.cost.setPaintFlags(holder.cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        if(product.getImage1() != null)
        {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage1());
                holder.image.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        else if(product.getImage2() != null)
        {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage2());
                holder.image.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        else if(product.getImage3() != null)
        {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage3());
                holder.image.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + product.getName());
                Intent intent = new Intent(mContext, Info_product.class);
                mContext.startActivity(intent);
                Toast.makeText(mContext, product.getName(), Toast.LENGTH_SHORT).show();
            }
        });
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, Info_product.class);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, sells, cost, count, percent;
        RatingBar ratingBar;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.rating);
            count = itemView.findViewById(R.id.count);
            sells = itemView.findViewById(R.id.sells);
            cost = itemView.findViewById(R.id.cost);
            percent = itemView.findViewById(R.id.percent);
        }
    }
}
