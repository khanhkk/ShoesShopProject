package Adapters;

/**
 * Created by ACER on 4/4/2018.
 */
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.shoesshop.R;


public class CustumAdapterHistory extends ArrayAdapter<String> {

    private Activity context;
    private  int resource;
    private ArrayList<String> arrayContact;


    public CustumAdapterHistory(@NonNull Activity context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayContact = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //convertView = LayoutInflater.from(context).inflate(R.layout.layouthistorytransaction22,parent,false);
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resource,parent,false);
        //ImageView imgView = (ImageView) convertView.findViewById(R.id.imgView);
        TextView txtLichSu = (TextView) convertView.findViewById(R.id.tvNameType);
        //ImageButton btnImg = (ImageButton) convertView.findViewById(R.id.btnImg);

        String contact = arrayContact.get(position);


        txtLichSu.setText(contact);

//        btnImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //toi mang hinh 12
//                Intent intent = new Intent(context, ListOder.class);
//                startActivity(intent);
//            }
//        });

        return convertView;

    }
}

