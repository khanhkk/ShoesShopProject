package Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Models.Product;
import tdc.edu.vn.shoesshop.R;

public class Adapter_info_product extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String key;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public ArrayList<byte[]> anh = new ArrayList<>();
    private int count;

    public Adapter_info_product(Context context, String key, int count) {
        this.context = context;
        this.key = key;
        this.count = count;
    }

    public Adapter_info_product(Context context) {
        this.context = context;
    }

    public Adapter_info_product(Context context, String key) {
        this.context = context;
        this.key = key;
    }

    @Override
    public int getCount() {
        return count;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.custom_info_product, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        database.child("Products").orderByChild("id").equalTo(key).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);



                if (product.getImage1() != null) {
                    try {
                        byte[] imageByteArray = Base64.decode(product.getImage1(), Base64.DEFAULT);
                        anh.add(imageByteArray);
                    } catch (Exception ex) {
                    }
                } if (product.getImage2() != null) {
                    try {

                        byte[] imageByteArray2 = Base64.decode(product.getImage2(), Base64.DEFAULT);
                        anh.add(imageByteArray2);
                    } catch (Exception ex) {
                    }

                } if (product.getImage3() != null) {
                    try {

                        byte[] imageByteArray3 = Base64.decode(product.getImage3(), Base64.DEFAULT);
                        anh.add(imageByteArray3);
                    } catch (Exception ex) {
                    }
                }

                if(anh.size() > 0) {
                    if (anh.get(position) != null) {
                        Glide.with(context)
                                .load(anh.get(position))
                                .into(imageView);
                    }
                }


//imageString[position]
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {
                    Toast.makeText(context, "Slide 1 Clicked", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    Toast.makeText(context, "Slide 2 Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Slide 3 Clicked", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);


    }

}

