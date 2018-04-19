package Models;

public class Notification {
   private int hinh;
    private String ten;
    private String hoatdong;
    private String thoiGian;

    public Notification(int hinh, String ten, String hoatdong, String thoiGian) {
        this.hinh = hinh;
        this.ten = ten;
        this.hoatdong = hoatdong;
        this.thoiGian = thoiGian;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHoatdong() {
        return hoatdong;
    }

    public void setHoatdong(String hoatdong) {
        this.hoatdong = hoatdong;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
