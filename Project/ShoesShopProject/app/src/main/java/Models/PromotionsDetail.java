package Models;

/**
 * Created by kk on 01/04/2018.
 */

public class PromotionsDetail {
    private int id, discount, point;
    private int promotions;
    private String product, gift;


    public PromotionsDetail() {
    }

    public PromotionsDetail(int id, int discount, int promotions, String product, String gift, int point) {
        this.id = id;
        this.discount = discount;
        this.promotions = promotions;
        this.product = product;
        this.gift = gift;
        this.point = point;
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

    public int getPromotions() {
        return promotions;
    }

    public void setPromotions(int promotions) {
        this.promotions = promotions;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
