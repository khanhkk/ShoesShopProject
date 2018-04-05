package Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kk on 01/04/2018.
 */

public class Promotion {
    private int id;
    private String title, content;
    private Date dateStart, dateEnd;
    private ArrayList<PromotionsDetail> listDetail;

    public Promotion() {
    }

    public Promotion(int id, String title, String content, Date dateStart, Date dateEnd, ArrayList<PromotionsDetail> listDetail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.listDetail = listDetail;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ArrayList<PromotionsDetail> getListDetail() {
        return listDetail;
    }

    public void setListDetail(ArrayList<PromotionsDetail> listDetail) {
        this.listDetail = listDetail;
    }
}
