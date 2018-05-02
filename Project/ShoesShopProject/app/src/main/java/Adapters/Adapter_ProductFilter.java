package Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import tdc.edu.vn.shoesshop.R;

public class Adapter_ProductFilter extends BaseAdapter {


    private static final String TAG = "Adapter_ProductFilter";

    //vars
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> rate = new ArrayList<>();
    private ArrayList<Double> mSells = new ArrayList<>();
    private ArrayList<Double> mCost = new ArrayList<>();
    private ArrayList<Integer> mCount = new ArrayList<>();
    private Context mContext;

    public Adapter_ProductFilter(ArrayList<Integer> mImageUrls, ArrayList<String> mNames, ArrayList<Integer> rate, ArrayList<Double> mSells, ArrayList<Double> mCost, ArrayList<Integer> mCount, Context mContext) {
        this.mImageUrls = mImageUrls;
        this.mNames = mNames;
        this.rate = rate;
        this.mSells = mSells;
        this.mCost = mCost;
        this.mCount = mCount;
        this.mContext = mContext;
    }

    public Adapter_ProductFilter(ArrayList<Integer> mImageUrls, ArrayList<String> mNames, ArrayList<Integer> rate, ArrayList<Double> mSells, ArrayList<Double> mCost, Context mContext) {
        this.mImageUrls = mImageUrls;
        this.mNames = mNames;
        this.rate = rate;
        this.mSells = mSells;
        this.mCost = mCost;
        this.mContext = mContext;
    }

    public Adapter_ProductFilter() {
        super();
    }

    @Override
    public int getCount() {
        return mImageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.layout_list_profilter_s, null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
        TextView name = (TextView) convertView.findViewById(R.id.name) ;
        RatingBar rate_bar = (RatingBar) convertView.findViewById(R.id.rating);
        TextView count = (TextView) convertView.findViewById(R.id.count);
        TextView sells = (TextView) convertView.findViewById(R.id.sells);
        TextView cost = (TextView) convertView.findViewById(R.id.cost);
        TextView percent = (TextView) convertView.findViewById(R.id.percent);

        imageView.setImageResource(mImageUrls.get(position));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        name.setText(mNames.get(position));
        rate_bar.setRating(Integer.valueOf(rate.get(position)));
        count.setText("("+String.valueOf(mCount.get(position))+")");
        long percent_a;
        percent_a = Math.round(100 - 100 * mSells.get(position)/mCost.get(position));
        percent.setText("-"+String.valueOf(percent_a)+"%");

        //format 1
        NumberFormat nf=NumberFormat.getInstance();
        DecimalFormat df=(DecimalFormat)nf;
        df.applyPattern("#,### đ");

        sells.setText(df.format((mSells.get(position))));
        cost.setText(df.format((mCost.get(position))));
        cost.setPaintFlags(cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        return convertView;

    }


}
