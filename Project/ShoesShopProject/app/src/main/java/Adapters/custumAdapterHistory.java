package Adapters;

/**
 * Created by ACER on 4/4/2018.
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.shoesshop.ListHistoryTransaction;
import tdc.edu.vn.shoesshop.R;


public class custumAdapterHistory extends ArrayAdapter<String> {

    private  Context context;
    private  int resource;
    private ArrayList<String> arrayContact;


    public custumAdapterHistory(@NonNull ListHistoryTransaction context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayContact = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.listview_layout22,parent,false);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.imgView);
        TextView txtLichSu = (TextView) convertView.findViewById(R.id.txtLichSu);
        ImageButton btnImg = (ImageButton) convertView.findViewById(R.id.btnImg);

        String contact = arrayContact.get(position);


        txtLichSu.setText(contact);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toi mang hinh 12
            }
        });


        return convertView;

    }
}

