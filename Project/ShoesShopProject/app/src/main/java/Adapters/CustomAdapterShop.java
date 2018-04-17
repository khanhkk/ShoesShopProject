package Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import Models.Contact;
import tdc.edu.vn.shoesshop.R;

public class CustomAdapterShop extends BaseAdapter {
    private List<Contact> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomAdapterShop(Context aContext, List<Contact> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_shop, null);
            holder = new ViewHolder();
            holder.imgShop = (ImageView) convertView.findViewById(R.id.imgShop);
            holder.nameShop = (TextView) convertView.findViewById(R.id.txtTenShop);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = this.listData.get(position);
        holder.nameShop.setText(contact.getTen());

        int imageId = this.getMipmapResIdByName(String.valueOf(contact.getHinh()));

        holder.imgShop.setImageResource(imageId);

        return convertView;
    }


    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView imgShop;
        TextView nameShop;
    }
}
