package Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import Models.OrderShop;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.OrderInformationForShop;

public class OrderShopAdapter extends BaseAdapter {
    Context myContext;
    int LAYOUT;
    ArrayList<OrderShop> arrItem;
    public OrderShopAdapter(Context context, int layout, ArrayList<OrderShop> itemList)
    {
        myContext = context;
        LAYOUT = layout;
        arrItem = itemList;
    }

    @Override
    public int getCount() {
        return arrItem.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(LAYOUT,null);
        TextView txtMa=(TextView) view.findViewById(R.id.ma);
        txtMa.setText(arrItem.get(i).getTen());
        ImageView imageView= (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(arrItem.get(i).getHinh());
        TextView txtGiasp=(TextView) view.findViewById(R.id.giasp);
        txtGiasp.setText(arrItem.get(i).getGia()+" ");
        TextView txtGiagoc=(TextView) view.findViewById(R.id.giagoc);
        txtGiagoc.setText(arrItem.get(i).getGiaGoc()+ " ");
        txtGiagoc.setPaintFlags(txtGiagoc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        TextView txtSoLuong=(TextView) view.findViewById(R.id.soluong);
        txtSoLuong.setText(arrItem.get(i).getSoLuong() + " ");


        double tien = 0;
        for(int pos = 0; pos < arrItem.size(); pos++ )
        {
            tien += (arrItem.get(pos).getGia() *  arrItem.get(pos).getSoLuong());
        }

        OrderInformationForShop.tvTotal.setText(tien+"");
        return view;
    }


}