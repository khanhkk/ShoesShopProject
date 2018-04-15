package Models;

import java.util.HashMap;

/**
 * Created by kk on 01/04/2018.
 */

public class Promotion {
    private int id;
    private String title, content;
    private String dateStart, dateEnd;
    //private ArrayList<PromotionsDetail> listDetail;
    private HashMap<String,String> details;

    public Promotion() {
    }

    public Promotion(int id, String title, String content, String dateStart, String dateEnd, HashMap<String,String> listDetail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.details = listDetail;
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

    public HashMap<String, String> getDetails() {
        return details;
    }

    public void setDetails(HashMap<String, String> details) {
        this.details = details;
    }

    //    public ArrayList<PromotionsDetail> getListDetail() {
//        return listDetail;
//    }
//
//    public void setListDetail(ArrayList<PromotionsDetail> listDetail) {
//        this.listDetail = listDetail;
//    }
}
