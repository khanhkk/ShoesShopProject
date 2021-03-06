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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Controls.General;
import Models.Product;
import Models.ProductDetail;
import tdc.edu.vn.shoesshop.R;

public class Adapter_ProductFilter extends BaseAdapter {

    private static final String TAG = "Adapter_ProductFilter";
    private Context mContext;
    private ArrayList<Product> list;
    private ArrayList<ProductDetail> listDetail;

    public Adapter_ProductFilter() {
        super();
    }

    public Adapter_ProductFilter(Context mContext, ArrayList<Product> list, ArrayList<ProductDetail> listDetail) {
        this.mContext = mContext;
        this.list = list;
        this.listDetail = listDetail;
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


    class ViewHolderGrid {
        public ImageView imageView;
        public RatingBar ratingBar;
        public TextView tvThuongHieu, tvName, tvSoLuong, tvSalePrice, tvListedPrice, tvDiscount;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        ViewHolderGrid viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolderGrid();
            convertView = layoutInflater.inflate(R.layout.layout_list_profilter_s, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.rating);
            viewHolder.tvSoLuong = (TextView) convertView.findViewById(R.id.count);
            viewHolder.tvSalePrice = (TextView) convertView.findViewById(R.id.sells);
            viewHolder.tvListedPrice = (TextView) convertView.findViewById(R.id.cost);
            viewHolder.tvDiscount = (TextView) convertView.findViewById(R.id.percent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderGrid) convertView.getTag();
        }

//        imageView.setImageResource(mImageUrls.get(position));
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        final Product product = list.get(position);

        //format
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#,### đ");


        viewHolder.tvName.setText(product.getName());
        viewHolder.ratingBar.setRating(product.getRating());
        int soluong = 0;

        if (listDetail.size() > 0) {
            for (ProductDetail pro : listDetail) {
                if (pro.getProduct().equals(product.getId())) {
                    if (pro.getQuantity() != 0) {
                        soluong += pro.getQuantity();
                    }
                }
                if (soluong != 0) {
                    viewHolder.tvSoLuong.setText("(" + String.valueOf(soluong) + ")");
                }
            }
        }
        //  Log.d("da", soluong+"");
        long percent_a;

        percent_a = Math.round(100 - 100 * product.getSalePrice() / product.getListedPrice());

        viewHolder.tvDiscount.setText("-" + String.valueOf(percent_a) + "%");
        viewHolder.tvSalePrice.setText(df.format(product.getSalePrice()));
        viewHolder.tvListedPrice.setText(df.format(product.getListedPrice()));
        viewHolder.tvListedPrice.setPaintFlags(viewHolder.tvListedPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //viewHolder.chkCheck.isChecked();

        if (product.getImage1() != null) {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage1());
                viewHolder.imageView.setImageBitmap(bitmap);
            } catch (Exception ex) {

            }
        } else if (product.getImage2() != null) {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage2());
                viewHolder.imageView.setImageBitmap(bitmap);
            } catch (Exception ex) {

            }
        } else if (product.getImage3() != null) {
            try {
                Bitmap bitmap = General.decodeFromFirebaseBase64(product.getImage3());
                viewHolder.imageView.setImageBitmap(bitmap);
            } catch (Exception ex) {

            }
        }

        return convertView;
    }
}
