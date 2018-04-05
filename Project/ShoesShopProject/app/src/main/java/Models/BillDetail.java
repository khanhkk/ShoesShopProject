package Models;

/**
 * Created by kk on 30/03/2018.
 */

public class BillDetail {
    private int id, quantity;
    private  String codeOfBill;
    private Product product;

    public BillDetail() {
    }

    public BillDetail(int id, String codeOfBill, Product codeOfProduct, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.codeOfBill = codeOfBill;
        this.product = codeOfProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCodeOfBill() {
        return codeOfBill;
    }

    public void setCodeOfBill(String codeOfBill) {
        this.codeOfBill = codeOfBill;
    }

    public Product getProduct() {
        return product;
    }

    public void setCodeOfProduct(Product Product) {
        this.product = Product;
    }
}
