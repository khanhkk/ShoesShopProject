package Models;

/**
 * Created by kk on 30/03/2018.
 */

public class BillDetail {
    private int quantity;
    private  String id;
    private String product;
    private double price;
    private String shop;
    private String detail;

    public BillDetail() {
    }

    public BillDetail(String id, String codeOfProduct, int quantity, double price, String shop, String detail) {
        this.id = id;
        this.quantity = quantity;
        this.product = codeOfProduct;
        this.price = price;
        this.shop = shop;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String Product) {
        this.product = Product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
