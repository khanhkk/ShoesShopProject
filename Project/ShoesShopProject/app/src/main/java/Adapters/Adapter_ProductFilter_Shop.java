package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import tdc.edu.vn.shoesshop.Khanh.HSActivity;
import tdc.edu.vn.shoesshop.R;

public class Adapter_ProductFilter_Shop extends BaseAdapter {

    //vars
//    private ArrayList<Integer> mImageUrls = new ArrayList<>();
//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<Integer> mrate = new ArrayList<>();
//    private ArrayList<Double> mSells = new ArrayList<>();
//    private ArrayList<Double> mCost = new ArrayList<>();
//    private ArrayList<Double> mPercent = new ArrayList<>();
//    private ArrayList<Integer> mCount = new ArrayList<>();
//    private ArrayList<Boolean> mCheck = new ArrayList<>();
    private static final String TAG = "Adapter_ProductFilter";
    private Context mContext;
    private ArrayList<Product> list;
    private ArrayList<ProductDetail> listDetail;
    //private ArrayList<Product> CheckedProducts;

//    public Adapter_ProductFilter_Shop(ArrayList<Integer> mImageUrls, ArrayList<String> mNames, ArrayList<Integer> mrate, ArrayList<Double> mSells, ArrayList<Double> mCost, ArrayList<Integer> mCount, ArrayList<Boolean> mCheck, Context mContext) {
//        this.mImageUrls = mImageUrls;
//        this.mNames = mNames;
//        this.mrate = mrate;
//        this.mSells = mSells;
//        this.mCost = mCost;
//        this.mCount = mCount;
//        this.mCheck = mCheck;
//        this.mContext = mContext;
//    }

    public Adapter_ProductFilter_Shop() {
        super();
    }

    public Adapter_ProductFilter_Shop(Context mContext, ArrayList<Product> list, ArrayList<ProductDetail> listDetail) {
        this.mContext = mContext;
        this.list = list;
        this.listDetail = listDetail;

        //CheckedProducts = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    public ArrayList<Product> getCheckedProducts() {
//        return CheckedProducts;
//    }

    class ViewHolderGrid
    {
        public CheckBox chkCheck;
        public ImageView imageView;
        public RatingBar ratingBar;
        public TextView tvThuongHieu, tvName, tvSoLuong, tvSalePrice, tvListedPrice, tvDiscount;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        ViewHolderGrid viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolderGrid();
            convertView = layoutInflater.inflate(R.layout.layout_list_profilter_shop, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.rating);
            viewHolder.tvSoLuong = (TextView) convertView.findViewById(R.id.count);
            viewHolder.tvSalePrice = (TextView) convertView.findViewById(R.id.sells);
            viewHolder.tvListedPrice = (TextView) convertView.findViewById(R.id.cost);
            viewHolder.tvDiscount = (TextView) convertView.findViewById(R.id.percent);
            viewHolder.chkCheck = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(viewHolder);
        }
        else
        {
           viewHolder = (ViewHolderGrid) convertView.getTag();
        }

//        imageView.setImageResource(mImageUrls.get(position));
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        final Product product = list.get(position);

        //format 1
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#,### đ");

        viewHolder.tvName.setText(product.getName());
        viewHolder.ratingBar.setRating(product.getRating());

        int soluong = 0;

        for (ProductDetail pro : listDetail) {
            if (pro.getProduct().equals(product.getId())) {
                soluong += pro.getQuantity();
            }
            viewHolder.tvSoLuong.setText("(" + String.valueOf(soluong) + ")");
        }

        //count.setText("(" + String.valueOf(mCount.get(position)) + ")");
        //long percent_a;

        //percent_a = Math.round(100 - 100 * mSells.get(position) / mCost.get(position));
        //percent.setText("-" + String.valueOf() + "%");

        viewHolder.tvSalePrice.setText(df.format(product.getSalePrice()));
        viewHolder.tvListedPrice.setText(df.format(product.getListedPrice()));
        viewHolder.tvListedPrice.setPaintFlags(viewHolder.tvListedPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //viewHolder.chkCheck.isChecked();
        viewHolder.chkCheck.setZ(2000f);

        if(product.getImage1() != null)
        {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage1());
                viewHolder.imageView.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        else if(product.getImage2() != null)
        {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage2());
                viewHolder.imageView.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        else if(product.getImage3() != null)
        {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage3());
                viewHolder.imageView.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }

        viewHolder.chkCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    //Log.d("tag", "y");
                    HSActivity.ListProducts.add(product);
                    Toast.makeText(mContext, product.getName() + " checked - " , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Log.d("tag", "n");
                    HSActivity.ListProducts.remove(product);
                    Toast.makeText(mContext, product.getName() + " unchecked - ", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        name.setText(mNames.get(position));
//        rate_bar.setRating(Integer.valueOf(mrate.get(position)));
//        count.setText("(" + String.valueOf(mCount.get(position)) + ")");
//        long percent_a;
//        percent_a = Math.round(100 - 100 * mSells.get(position) / mCost.get(position));
//        percent.setText("-" + String.valueOf(percent_a) + "%");
//
//        sells.setText(df.format((mSells.get(position))));
//        cost.setText(df.format((mCost.get(position))));
//        cost.setPaintFlags(cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        checkBox.isChecked();
//        checkBox.setZ(2000f);


        return convertView;
    }
}
