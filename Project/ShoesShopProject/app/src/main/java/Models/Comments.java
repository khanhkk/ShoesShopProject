package Models;

public class Comments {
    private String thoiGian, noiDung, ten;

    public Comments(String thoiGian, String noiDung, String ten) {
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
        this.ten = ten;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
