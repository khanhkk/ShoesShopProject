package Models;

import java.util.ArrayList;

/**
 * Created by kk on 30/03/2018.
 */

public class Product {
    private String id, name, trademark, description;
    private int guarantee, accumulatedPoint, image1, imag2, image3;
    private double listedPrice, salePrice;
    private String shop_id;
    private ArrayList<ProductDetail> list;

    public Product() {
    }

    public Product(String id, String name, String trademark, String description, String shop, int guarantee, int accumulatedPoint, int image1, int imag2, int image3, double listedPrice, double salePrice, ArrayList<ProductDetail> details) {
        this.id = id;
        this.name = name;
        this.trademark = trademark;
        this.description = description;
        this.shop_id = shop;
        this.guarantee = guarantee;
        this.accumulatedPoint = accumulatedPoint;
        this.image1 = image1;
        this.imag2 = imag2;
        this.image3 = image3;
        this.listedPrice = listedPrice;
        this.salePrice = salePrice;
        this.list = details;
    }

    public Product(String id, String name, double salePrice, String shop) {
        this.id = id;
        this.name = name;
        this.salePrice = salePrice;
        this.shop_id = shop;
    }


    public Product(String id, String name, double listedPrice, double salePrice, String shop, ArrayList<ProductDetail> details) {
        this.id = id;
        this.name = name;
        this.listedPrice = listedPrice;
        this.salePrice = salePrice;
        this.shop_id = shop;
        this.list = details;
    }


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
        return shop_id;
    }

    public void setShop(String shop) {
        this.shop_id = shop;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public int getAccumulatedPoint() {
        return accumulatedPoint;
    }

    public void setAccumulatedPoint(int accumulatedPoint) {
        this.accumulatedPoint = accumulatedPoint;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImag2() {
        return imag2;
    }

    public void setImag2(int imag2) {
        this.imag2 = imag2;
    }

    public int getImage3() {
        return image3;
    }

    public void setImage3(int image3) {
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
}
