package Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Bill;
import tdc.edu.vn.shoesshop.R;

public class CustumAdapterOder extends ArrayAdapter<Bill> {
    private AppCompatActivity context;
    private  int resource;
    private ArrayList<Bill> arrayContact;


    public CustumAdapterOder(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<Bill> objects) {
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
        TextView timeOder = (TextView) convertView.findViewById(R.id.txtInfo);
        TextView emailOder = (TextView) convertView.findViewById(R.id.txtEmailhistory);

        Bill contact = arrayContact.get(position);


        timeOder.setText(contact.getTime());
        emailOder.setText(contact.getId());


        return convertView;

    }

}
