package Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Models.Notification;
import Models.OrderClient;
import tdc.edu.vn.shoesshop.R;

public class NotificationClientAdapter extends BaseAdapter {
    Context myContext;
    int LAYOUT;
    List<Notification> arrItem;
    public NotificationClientAdapter(Context context, int layout, List<Notification> itemList)
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


        TextView txtTen=(TextView) view.findViewById(R.id.tv_name);
        txtTen.setText(arrItem.get(i).getTen());
        ImageView imageView= (ImageView) view.findViewById(R.id.tv_img);
        imageView.setImageResource(arrItem.get(i).getHinh());
        TextView txtHoatdong=(TextView) view.findViewById(R.id.tv_active);
        txtHoatdong.setText(arrItem.get(i).getHoatdong());
        TextView txtDate=(TextView) view.findViewById(R.id.tv_date);
        txtDate.setText(arrItem.get(i).getThoiGian());



        return view;
    }
}
