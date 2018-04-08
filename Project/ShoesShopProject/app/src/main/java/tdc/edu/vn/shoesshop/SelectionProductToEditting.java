package tdc.edu.vn.shoesshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.ProductAdapter;
import Models.Product;
import Models.ProductDetail;
import Models.Shop;

public class SelectionProductToEditting extends AppCompatActivity {

    ProductAdapter productAdapter;
    ListView lvList;
    Button btnFinish;
    ImageButton btnBack;
    ArrayList<Product> list = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_product_to_editting_activity);

        lvList = (ListView) findViewById(R.id.lvListProduct);
        btnFinish = (Button) findViewById(R.id.btnFinishEdition);
        btnBack = (ImageButton) findViewById(R.id.btnBack);


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(SelectionProductToEditting.this, HomeForShop.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(SelectionProductToEditting.this, HomeForShop.class);
                startActivity(intent);
            }
        });

        creatList();

        productAdapter = new ProductAdapter(SelectionProductToEditting.this, R.layout.product_item_layout, list);

        lvList.setAdapter(productAdapter);
    }

    private  void creatList()
    {
        Shop shop = new Shop("SH0001", "MiuMiu", "01512151211");
        ArrayList<ProductDetail> details = new ArrayList<>();



        Product product = new Product("SP0001", "giay1dsf Ä‘sÃ dsf fsÃ¡df", 1290000, 900000, shop, null);
        Product product2 = new Product("SP0002", "giay2 dfasd fdsfsa dffasd", 175000, 150000, shop, null);
        Product product3 = new Product("SP0003", "giay3dÃ dsfad sfdfd fdfdsfsf fdfsdf", 239000, 200000, shop, null);
        Product product4 = new Product("SP0004", "giay4d fadsfs dfa sdfsfsaf dfdsfff dfdas fsd dsfssfewfefEFQEFqefqwfdsfd sfds fdsfsdf", 299000, 250000, shop, null);

        ProductDetail pd = new ProductDetail(product,1, 38, "xanh la cay", 10);
        ProductDetail pd2 = new ProductDetail(product,2, 39, "xanh la cay", 10);
        ProductDetail pd3 = new ProductDetail(product,3, 40, "xanh la cay", 10);
        ProductDetail pd4 = new ProductDetail(product,4, 38, "xam nhat", 10);
        ProductDetail pd5 = new ProductDetail(product,5, 39, "xam nhat", 10);
        ProductDetail pd6 = new ProductDetail(product,6, 40, "xam nhat", 10);
        ProductDetail pd7 = new ProductDetail(product,7, 38, "do nau", 10);

        details.add(pd);
        details.add(pd2);
        details.add(pd3);
        details.add(pd4);
        details.add(pd5);
        details.add(pd6);
        details.add(pd7);

        product.setList(details);

        list.add(product);
        list.add(product2);
        list.add(product3);
        list.add(product4);
    }

}
