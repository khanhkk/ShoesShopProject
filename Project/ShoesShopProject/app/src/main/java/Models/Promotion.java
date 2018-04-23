package Models;

/**
 * Created by kk on 01/04/2018.
 */

public class Promotion {
    private int id;
    private String dateStart, dateEnd, shop, title, content, image, listDetail;

    public Promotion() {
    }

    public Promotion(int id, String title , String shop, String content, String dateStart, String dateEnd,String image, String listDetail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.listDetail = listDetail;
        this.shop = shop;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getListDetail() {
        return listDetail;
    }

    public void setListDetail(String listDetail) {
        this.listDetail = listDetail;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //    public HashMap<String, Integer> getDetails() {
//        return details;
//    }
//
//    public void setDetails(HashMap<String, Integer> details) {
//        this.details = details;
//    }

    //    public ArrayList<PromotionsDetail> getListDetail() {
//        return listDetail;
//    }
//
//    public void setListDetail(ArrayList<PromotionsDetail> listDetail) {
//        this.listDetail = listDetail;
//    }
}
