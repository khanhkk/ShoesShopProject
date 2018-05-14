package Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Son.OrderInformationForClient;

public class CustumAdapterOder extends ArrayAdapter<String> {
    private AppCompatActivity context;
    private  int resource;
    private ArrayList<String> arrayContact;


    public CustumAdapterOder(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayContact = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        //convertView = LayoutInflater.from(context).inflate(R.layout.layout_oder,parent,false);
        convertView = inflater.inflate(resource, parent, false);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.imgViews12);
        TextView txtLichSu = (TextView) convertView.findViewById(R.id.txtInfo);
        TextView emailOder = (TextView) convertView.findViewById(R.id.txtEmailhistory);

        String contact = arrayContact.get(position);


        txtLichSu.setText(contact);
        txtLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toi mang hinh 23
                Intent intent = new Intent(context, OrderInformationForClient.class);
                context.startActivity(intent);
            }
        });


        return convertView;

    }

}
