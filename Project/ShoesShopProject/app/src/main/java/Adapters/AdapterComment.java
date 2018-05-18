package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Comments;
import tdc.edu.vn.shoesshop.R;

public class AdapterComment extends ArrayAdapter<Comments> {

    private Context context;
    private int resource;
    private ArrayList<Comments> arrCcomment;

    public AdapterComment(Context context, int resource, ArrayList<Comments> arrCcomment) {
        super(context, resource, arrCcomment);
        this.context = context;
        this.resource = resource;
        this.arrCcomment = arrCcomment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_comment, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.nameUser);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.timeComment);
            viewHolder.tvCcoment = (TextView) convertView.findViewById(R.id.Comment);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageBL);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Comments comments = arrCcomment.get(position);
//        viewHolder.tvAvatar.setBackgroundColor(contact.getColor());
//        viewHolder.tvAvatar.setText(String.valueOf(position+1));
        viewHolder.tvCcoment.setText(comments.getNoiDung());
        viewHolder.tvName.setText(comments.getTen());
        viewHolder.tvTime.setText(comments.getThoiGian());
        viewHolder.imageView.setImageResource(R.drawable.image_bl);
        return convertView;
    }

    public class ViewHolder {
        TextView tvName, tvTime, tvCcoment;
        ImageView imageView;
    }
}