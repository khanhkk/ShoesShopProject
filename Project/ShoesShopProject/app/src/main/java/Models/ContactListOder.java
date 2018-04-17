package Models;

public class ContactListOder {
    private String imageHistory;
    private String Info;
    private String ChiTiet;

    public ContactListOder(String imageHistory, String Info, String ChiTiet) {
        this.imageHistory = imageHistory;
        this.Info = Info;
        this.ChiTiet = ChiTiet;
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
        return ChiTiet;
    }

    public void setChiTiet(String ChiTiet) {
        this.ChiTiet = ChiTiet;
    }
}
