package tdc.edu.vn.shoesshop.Khanh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

import Adapters.PromotionExpandableListAdapter;
import Models.Product;
import Models.Promotion;
import Models.PromotionsDetail;
import Models.Shop;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Thanh.EditingPromotionDetail;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;

public class Promotions extends Activity implements SearchView.OnQueryTextListener {

    ExpandableListView lvPromotions;
    SearchView svSearchPromotions;
    ImageButton btnAdd, btnBack;
    HashMap<Promotion,ArrayList<PromotionsDetail>> list = new HashMap<>();
    static ArrayList<Promotion> listParent = new ArrayList<>(), listCopy = new ArrayList<>();
    ArrayList<PromotionsDetail> promotionsDetailArrayList = new ArrayList<>();

    //public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static PromotionExpandableListAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotions_activity);

        // Write data to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnAdd = (ImageButton)findViewById(R.id.btnAddPromotions);
        lvPromotions = (ExpandableListView) findViewById(R.id.lvListPromotions);
        svSearchPromotions = (SearchView) findViewById(R.id.svSearch);
        svSearchPromotions.setOnQueryTextListener(this);

//        if(listParent.size() == 0) {
//            TakeData();
//            listCopy.addAll(listParent);
//        }
//        else
//        {
//            listCopy.clear();
//            listCopy.addAll(listParent);
//        }
//
//        for(int i = 0; i < promotionsDetailArrayList.size(); i++)
//        {
//            PromotionsDetail promotionsDetail = promotionsDetailArrayList.get(i);
//            myRef.child("PromotionsDetail").push().setValue(promotionsDetail);
//        }
//
//        for(int i = 0; i< listParent.size(); i++)
//        {
//            Promotion promotion = listParent.get(i);
//            myRef.child("Promotions").push().setValue(promotion);
//        }

        Query allPromtions = myRef.child("Promotions");
        listParent.clear();
        list.clear();

       myRef.child("PromotionsDetail").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               PromotionsDetail promotionsDetail = dataSnapshot.getValue(PromotionsDetail.class);
               promotionsDetailArrayList.add(promotionsDetail);
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


        allPromtions.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Promotion por = dataSnapshot.getValue(Promotion.class);
                listParent.add(por);
                list.put(por, null);
                //Log.d("key", listParent.size() + "");
                if(por.getListDetail() != null) {
                    String a[] = por.getListDetail().split("#");
                    ArrayList<PromotionsDetail> arr = new ArrayList<>();
                    for (String str : a) {
                        for (PromotionsDetail p : promotionsDetailArrayList) {
                            if (p.getId() == Integer.parseInt(str)) {
                                arr.add(p);
                            }

                        }
                    }
                    list.put(por, arr);
                }

                adapter.notifyDataSetChanged();
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

        adapter = new PromotionExpandableListAdapter(Promotions.this, listParent, list);
        lvPromotions.setAdapter(adapter);

        intent = getIntent();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Promotions.this, HomeForClient.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(Promotions.this, EdittingPromotions.class);
                startActivity(intent);
            }
        });

        //dang ky contextmenu cho list view
        registerForContextMenu(lvPromotions);

    }

    //khoi tao contextmenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child =	ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

            PromotionsDetail item = (PromotionsDetail) adapter.getChild(group, child);
            menu.setHeaderTitle(item.getProduct());
            menu.setHeaderIcon(R.mipmap.giay);

            menu.add(0, R.id.cmSua, 0, "Sua");
            menu.add(0, R.id.cmXoa, 0, "Xoa");
        }
    }

    //chọn item của context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child =	ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            PromotionsDetail promotionsDetail = (PromotionsDetail) adapter.getChild(group, child);
            switch (item.getItemId()) {
                case R.id.cmSua:
                    Toast.makeText(Promotions.this, "sua" + promotionsDetail.getProduct(), Toast.LENGTH_SHORT).show();
                    Intent itent = new Intent(Promotions.this, EditingPromotionDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("detail", promotionsDetail.getId()+ "");
                    startActivity(itent);
                    break;

                case R.id.cmXoa:
                    Toast.makeText(Promotions.this, "xoa" + promotionsDetail.getProduct(), Toast.LENGTH_SHORT).show();
                    list.get(listParent.get(group)).remove(child);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }

        return super.onContextItemSelected(item);
    }

    //tao du lieu cho activity
    void TakeData()
    {
        listParent.clear();
        listCopy.clear();
        Shop shop = new Shop("SH0001", "MiuMiu", "01512151211");

        Product product = new Product("SP0001", "giay1dsfđsàdsffsádf", 1290000, 900000, shop.getId(), null);
        Product product2 = new Product("SP0002", "giay2dfasdfdsfsadffasd", 175000, 150000, shop.getId(), null);
        Product product3 = new Product("SP0003", "giay3dàdsfads", 239000, 200000, shop.getId(), null);
        Product product4 = new Product("SP0004", "giay4dfadsfsdfasdfsfsafdfdsfffdfdas fsd fdsfdsfdsfdsfsdf", 299000, 250000, shop.getId(), null);



        Promotion promotions = new Promotion(1,"Chương trình khuyến mãi 1", "fjds hgfds fhasjfd jfhads jndsjfdsajfdsh jda hjfd hds fd hd","10/04/2018 07:00", "20/04/2018 10:00", null);
        Promotion promotions2 = new Promotion(2,"Chương trình khuyến mãi 2", null,"11/04/2018 10:00","21/04/2018 17:00", null);
        Promotion promotions3 = new Promotion(3,"Chương trình khuyến mãi 3", null, "12/04/2018 11:00","22/04/2018 18:00", null);
        Promotion promotions4 = new Promotion(4,"Chương trình khuyến mãi 4", null, "13/04/2018 07:00","23/04/2018 17:30", null);
        Promotion promotions5 = new Promotion(5,"Chương trình khuyến mãi 5", null,"14/04/2018 08:00", "24/04/2018 18:30", null);

//
        promotionsDetailArrayList.clear();
        PromotionsDetail promotionsDetail = new PromotionsDetail(1, 10, promotions.getId(), product.getId(), "Vớ cao cấp",0);
        PromotionsDetail promotionsDetail2 = new PromotionsDetail(2, 20, promotions.getId(), product2.getId(), null, 0);
        PromotionsDetail promotionsDetail3 = new PromotionsDetail(3, 15, promotions.getId(), product3.getId(), "Miếng lót giày", 0);
        PromotionsDetail promotionsDetail4 = new PromotionsDetail(4, 0, promotions.getId(), product4.getId(), "vòng tay thời trang",10);

//        HashMap<String,Integer> list = new HashMap<>();
//        list.put(promotionsDetail.getId()+"", promotionsDetail.getId());
//        list.put(promotionsDetail2.getId()+"", promotionsDetail2.getId());
//        list.put(promotionsDetail3.getId()+"", promotionsDetail3.getId());
//        list.put(promotionsDetail4.getId()+"", promotionsDetail4.getId());


//        promotions.setDetails(list);
//        promotions2.setDetails(list);
//        promotions3.setDetails(list);
//        promotions4.setDetails(list);
//        promotions5.setDetails(list);

            promotionsDetailArrayList.add(promotionsDetail);
            promotionsDetailArrayList.add(promotionsDetail2);
            promotionsDetailArrayList.add(promotionsDetail3);
            promotionsDetailArrayList.add(promotionsDetail4);

//            promotions.setListDetail(promotionsDetailArrayList);
//            promotions2.setListDetail(promotionsDetailArrayList);
//            promotions3.setListDetail(promotionsDetailArrayList);
//            promotions4.setListDetail(promotionsDetailArrayList);
//            promotions5.setListDetail(promotionsDetailArrayList);
//
//            promotions.setListDetail(promotionsDetailArrayList);

//            list.put(promotions, promotionsDetailArrayList);
//            list.put(promotions2, promotionsDetailArrayList);
//            list.put(promotions3, promotionsDetailArrayList);
//           list.put(promotions4, promotionsDetailArrayList);
//           list.put(promotions5, promotionsDetailArrayList);

        String s = "";
        for(int i = 0; i < promotionsDetailArrayList.size(); i++)
        {
            s += promotionsDetailArrayList.get(i).getId() + "#";
        }

        promotions.setListDetail(s);
//        promotions2.setListDetail(s);
//        promotions3.setListDetail(s);
//        promotions4.setListDetail(s);
//        promotions5.setListDetail(s);

        listParent.add(promotions);
        listParent.add(promotions2);
        listParent.add(promotions3);
        listParent.add(promotions4);
        listParent.add(promotions5);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(listParent.size() > 0) {
            if (newText.isEmpty())
            {
                listCopy.clear();
                listCopy.addAll(listParent);
            }
            else {
                listCopy.clear();
                for (int i = 0; i < listParent.size(); i++) {
                    if (listParent.get(i).getTitle().toString().contains(newText)) {
                        listCopy.add(listParent.get(i));
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        return true;
    }
}
