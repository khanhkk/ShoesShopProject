package Models;

/**
 * Created by kk on 01/04/2018.
 */

public class PromotionsDetail {
    private int id, discount;
    private Promotion promotions;
    private Product product;
    private String gift;

    public PromotionsDetail() {
    }

    public PromotionsDetail(int id, int discount, Promotion promotions, Product product, String gift) {
        this.id = id;
        this.discount = discount;
        this.promotions = promotions;
        this.product = product;
        this.gift = gift;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Promotion getPromotions() {
        return promotions;
    }

    public void setPromotions(Promotion promotions) {
        this.promotions = promotions;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }
}
