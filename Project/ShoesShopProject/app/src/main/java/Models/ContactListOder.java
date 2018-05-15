package Models;

public class ContactListOder {
    private String imageHistory;
    private String Info;
    private String EmailOder;


    public ContactListOder(String imageHistory, String Info, String EmailOder) {
        this.imageHistory = imageHistory;
        this.Info = Info;
        this.EmailOder = EmailOder;
    }

    public String getImageHistory() {
        return imageHistory;
    }

    public void setImageHistory(String imageHistory) {
        this.imageHistory = imageHistory;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public String getChiTiet() {
        return EmailOder;
    }

    public void setChiTiet(String ChiTiet) {
        this.EmailOder = ChiTiet;
    }
}
