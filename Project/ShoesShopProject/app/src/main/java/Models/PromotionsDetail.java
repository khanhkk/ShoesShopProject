package Models;

import java.io.Serializable;

/**
 * Created by kk on 01/04/2018.
 */

public class PromotionsDetail implements Serializable {
    private int discount, point;
    private String id, product, gift,promotions;


    public PromotionsDetail() {
    }

    public PromotionsDetail(String id, int discount, String promotions, String product, String gift, int point) {
        this.id = id;
        this.discount = discount;
        this.promotions = promotions;
        this.product = product;
        this.gift = gift;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPromotions() {
        return promotions;
    }

    public void setPromotions(String promotions) {
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
