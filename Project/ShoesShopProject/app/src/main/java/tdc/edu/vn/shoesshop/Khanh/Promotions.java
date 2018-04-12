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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Adapters.PromotionExpandableListAdapter;
import Models.Product;
import Models.Promotion;
import Models.PromotionsDetail;
import Models.Shop;
import tdc.edu.vn.shoesshop.R;
import tdc.edu.vn.shoesshop.Toan.HomeForClient;

public class Promotions extends Activity implements SearchView.OnQueryTextListener {

    ExpandableListView lvPromotions;
    SearchView svSearchPromotions;
    ImageButton btnAdd, btnBack;
    static HashMap<Promotion,ArrayList<PromotionsDetail>> list = new HashMap<>();
    static ArrayList<Promotion> listParent, listCopy;


    public static PromotionExpandableListAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotions_activity);


        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnAdd = (ImageButton)findViewById(R.id.btnAddPromotions);
        lvPromotions = (ExpandableListView) findViewById(R.id.lvListPromotions);
        svSearchPromotions = (SearchView) findViewById(R.id.svSearch);
        svSearchPromotions.setOnQueryTextListener(this);

        if(listParent == null) {
            TakeData();
            listCopy.addAll(listParent);
        }

        adapter = new PromotionExpandableListAdapter(Promotions.this, listCopy, list);
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

        registerForContextMenu(lvPromotions);
    }

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
            menu.setHeaderTitle(item.getProduct().getName());
            menu.setHeaderIcon(R.mipmap.giay);

            menu.add(0, R.id.cmSua, 0, "Sua");
            menu.add(0, R.id.cmXoa, 0, "Xoa");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.cmSua:
                Toast.makeText(Promotions.this, "sua", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cmXoa:
                Toast.makeText(Promotions.this, "xoa", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    public static void TakeData()
    {
        listParent = new ArrayList<>();
        listCopy = new ArrayList<>();
        Shop shop = new Shop("SH0001", "MiuMiu", "01512151211");

        Product product = new Product("SP0001", "giay1dsfđsàdsffsádf", 1290000, 900000, shop, null);
        Product product2 = new Product("SP0002", "giay2dfasdfdsfsadffasd", 175000, 150000, shop, null);
        Product product3 = new Product("SP0003", "giay3dàdsfads", 239000, 200000, shop, null);
        Product product4 = new Product("SP0004", "giay4dfadsfsdfasdfsfsafdfdsfffdfdas fsd fdsfdsfdsfdsfsdf", 299000, 250000, shop, null);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Promotion promotions = new Promotion(1,"Chương trình khuyến mãi 1", "fjds hgfds fhasjfd jfhads jndsjfdsajfdsh jda hjfd hds fd hd", format.parse("10/04/2018"), format.parse("20/04/2018"), null);
            Promotion promotions2 = new Promotion(2,"Chương trình khuyến mãi 2", null, format.parse("11/04/2018"), format.parse("21/04/2018"), null);
            Promotion promotions3 = new Promotion(3,"Chương trình khuyến mãi 3", null, format.parse("12/04/2018"), format.parse("22/04/2018"), null);
            Promotion promotions4 = new Promotion(4,"Chương trình khuyến mãi 4", null, format.parse("13/04/2018"), format.parse("23/04/2018"), null);
            Promotion promotions5 = new Promotion(5,"Chương trình khuyến mãi 5", null, format.parse("14/04/2018"), format.parse("24/04/2018"), null);

            ArrayList<PromotionsDetail> promotionsDetailArrayList = new ArrayList<>();
            PromotionsDetail promotionsDetail = new PromotionsDetail(1, 10, promotions, product, "Vớ cao cấp");
            PromotionsDetail promotionsDetail2 = new PromotionsDetail(2, 20, promotions, product2, null);
            PromotionsDetail promotionsDetail3 = new PromotionsDetail(3, 15, promotions, product3, "Miếng lót giày");
            PromotionsDetail promotionsDetail4 = new PromotionsDetail(4, 0, promotions, product4, "vòng tay thời trang");

            promotionsDetailArrayList.add(promotionsDetail);
            promotionsDetailArrayList.add(promotionsDetail2);
            promotionsDetailArrayList.add(promotionsDetail3);
            promotionsDetailArrayList.add(promotionsDetail4);

//            promotions.setListDetail(promotionsDetailArrayList);
//            promotions2.setListDetail(promotionsDetailArrayList);
//            promotions3.setListDetail(promotionsDetailArrayList);
//            promotions4.setListDetail(promotionsDetailArrayList);
//            promotions5.setListDetail(promotionsDetailArrayList);

            //promotions.setListDetail(promotionsDetailArrayList);

            list.put(promotions, promotionsDetailArrayList);
            list.put(promotions2, promotionsDetailArrayList);
            list.put(promotions3, promotionsDetailArrayList);
            list.put(promotions4, promotionsDetailArrayList);
            list.put(promotions5, promotionsDetailArrayList);

            listParent.add(promotions);
            listParent.add(promotions2);
            listParent.add(promotions3);
            listParent.add(promotions4);
            listParent.add(promotions5);

        } catch (ParseException e) {
            e.printStackTrace();
        }
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
