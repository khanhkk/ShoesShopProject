package tdc.edu.vn.shoesshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Adapters.PromotionExpandableListAdapter;
import Models.Product;
import Models.Promotion;
import Models.PromotionsDetail;
import Models.Shop;

public class Promotions extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ExpandableListView lvPromotions;
    SearchView svSearchPromotions;
    ImageButton btnAdd, btnBack;
    //static HashMap<Promotion,ArrayList<PromotionsDetail>> list = new HashMap<>();
    static ArrayList<Promotion> listParent = new ArrayList<>();

    PromotionExpandableListAdapter adapter;
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

        TakeData();

        adapter = new PromotionExpandableListAdapter(Promotions.this, listParent);
        lvPromotions.setAdapter(adapter);
    }

    public static void TakeData()
    {
        listParent.clear();
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

            promotions.setListDetail(promotionsDetailArrayList);
            promotions2.setListDetail(promotionsDetailArrayList);
            promotions3.setListDetail(promotionsDetailArrayList);
            promotions4.setListDetail(promotionsDetailArrayList);
            promotions5.setListDetail(promotionsDetailArrayList);

            //promotions.setListDetail(promotionsDetailArrayList);

            //list.put(promotions, promotionsDetailArrayList);
            //list.put(promotions2, promotionsDetailArrayList);
            //list.put(promotions3, promotionsDetailArrayList);
            //list.put(promotions4, promotionsDetailArrayList);
            //list.put(promotions5, promotionsDetailArrayList);

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
//            if (newText.isEmpty())
//            {
//                listParent.clear();
//                listParent.addAll(list);
//            }
//            else {
//                listUse.clear();
//                for (int i = 0; i < list.size(); i++) {
//                    if (list.get(i).getTitle().toString().contains(newText)) {
//                        listUse.add(list.get(i));
//                    }
//                }
//            }
//            adapter.notifyDataSetChanged();
        }
        return true;
    }
}
