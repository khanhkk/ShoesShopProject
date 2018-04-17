package Models;

public class ContactHistoryTransaction {

    private String mimageView;
    private String mLichSu;
    private String mimageButtun;

    public ContactHistoryTransaction(String mimageView, String mLichSu, String mimageButtun) {
        this.mimageView = mimageView;
        this.mLichSu = mLichSu;
        this.mimageButtun = mimageButtun;
    }

    public String getMimageView() {
        return mimageView;
    }

    public void setMimageView(String mimageView) {
        this.mimageView = mimageView;
    }

    public String getmLichSu() {
        return mLichSu;
    }

    public void setmLichSu(String mLichSu) {
        this.mLichSu = mLichSu;
    }

    public String getMimageButtun() {
        return mimageButtun;
    }

    public void setMimageButtun(String mimageButtun) {
        this.mimageButtun = mimageButtun;
    }
}

