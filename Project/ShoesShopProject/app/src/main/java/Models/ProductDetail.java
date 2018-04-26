package Models;

/**
 * Created by kk on 31/03/2018.
 */

public class ProductDetail {
    private String product, id;
    private int size, quantity;
    private String color;

    public ProductDetail() {
    }

    public ProductDetail(String product, String id, int size, String color, int quantity) {
        this.product = product;
        this.id = id;
        this.size = size;
        this.quantity = quantity;
        this.color = color;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
