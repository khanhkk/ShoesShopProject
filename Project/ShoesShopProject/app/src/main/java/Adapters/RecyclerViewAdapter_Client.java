package Adapters;

import android.content.Context;
import android.content.Intent;
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

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.Info_product;

public class RecyclerViewAdapter_Client extends RecyclerView.Adapter<RecyclerViewAdapter_Client.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars

    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mRate = new ArrayList<>();
    private ArrayList<Double> mSells = new ArrayList<>();
    private ArrayList<Double> mCost = new ArrayList<>();
    private ArrayList<Integer> mCount = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter_Client(ArrayList<Integer> mImageUrls, ArrayList<String> mNames, ArrayList<Integer> rate, ArrayList<Double> mSells, ArrayList<Double> mCost, ArrayList<Integer> mCount, Context mContext) {
        this.mImageUrls = mImageUrls;
        this.mNames = mNames;
        this.mRate = rate;
        this.mSells = mSells;
        this.mCost = mCost;
        this.mCount = mCount;
        this.mContext = mContext;
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
        holder.image.setImageResource(mImageUrls.get(position));
        holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.name.setText(mNames.get(position));
        holder.ratingBar.setRating(Integer.valueOf(mRate.get(position)));
        holder.count.setText("(" + String.valueOf(mCount.get(position)) + ")");
        long percent_a;
        percent_a = Math.round(100 - 100 * mSells.get(position) / mCost.get(position));
        holder.percent.setText("-" + String.valueOf(percent_a) + "%");

        //format 1
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#,### đ");

        holder.sells.setText(df.format((mSells.get(position))));
        holder.cost.setText(df.format((mCost.get(position))));
        holder.cost.setPaintFlags(holder.cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                Intent intent = new Intent(mContext, Info_product.class);
                mContext.startActivity(intent);
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
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
