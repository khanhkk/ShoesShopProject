package Models;

/**
 * Created by kk on 30/03/2018.
 */

public class BillDetail {
    private int quantity;
    private  String id;
    private Product product;

    public BillDetail() {
    }

    public BillDetail(String id, Product codeOfProduct, int quantity) {
        this.id = id;
        this.quantity = quantity;
        //this.price = gia;
        //this.codeOfBill = codeOfBill;
        this.product = codeOfProduct;
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

//    public String getCodeOfBill() {
//        return codeOfBill;
//    }
//
//    public void setCodeOfBill(String codeOfBill) {
//        this.codeOfBill = codeOfBill;
//    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product Product) {
        this.product = Product;
    }

//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
}
