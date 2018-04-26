package Models;

import java.util.ArrayList;

/**
 * Created by kk on 30/03/2018.
 */

public class Product {
    private String guarantee, id, name, trademark, description , image1, imag2, image3, shop;
    private int accumulatedPoint, rating;
    private double listedPrice, salePrice;
    private ArrayList<ProductDetail> list;

    public Product() {
    }

    public Product(String id, String name, String trademark, String description, String shop, String guarantee, int accumulatedPoint, String image1, String imag2, String image3, double listedPrice, double salePrice, int stars , ArrayList<ProductDetail> details) {
        this.id = id;
        this.name = name;
        this.trademark = trademark;
        this.description = description;
        this.shop = shop;
        this.guarantee = guarantee;
        this.accumulatedPoint = accumulatedPoint;
        this.image1 = image1;
        this.imag2 = imag2;
        this.image3 = image3;
        this.listedPrice = listedPrice;
        this.salePrice = salePrice;
        this.rating = stars;
        this.list = details;
    }

//    public Product(String id, String name, double salePrice, String shop) {
//        this.id = id;
//        this.name = name;
//        this.salePrice = salePrice;
//        this.shop = shop;
//    }
//
//
//    public Product(String id, String name, double listedPrice, double salePrice, String shop, ArrayList<ProductDetail> details) {
//        this.id = id;
//        this.name = name;
//        this.listedPrice = listedPrice;
//        this.salePrice = salePrice;
//        this.shop = shop;
//        this.list = details;
//    }


    public ArrayList<ProductDetail> getList() {
        return list;
    }

    public void setList(ArrayList<ProductDetail> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public int getAccumulatedPoint() {
        return accumulatedPoint;
    }

    public void setAccumulatedPoint(int accumulatedPoint) {
        this.accumulatedPoint = accumulatedPoint;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImag2() {
        return imag2;
    }

    public void setImag2(String imag2) {
        this.imag2 = imag2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public double getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(double listedPrice) {
        this.listedPrice = listedPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
