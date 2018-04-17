package Models;

public class OrderShop {
    private int Hinh;
    private String Ten;
    private int Gia;
    private int GiaGoc;
    private int soLuong;


    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getGiaGoc() {
        return GiaGoc;
    }

    public void setGiaGoc(int giaGoc) {
        GiaGoc = giaGoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public OrderShop(int hinh, String ten, int gia, int giaGoc, int soLuong) {
        Hinh = hinh;
        Ten = ten;
        Gia = gia;
        GiaGoc = giaGoc;
        this.soLuong = soLuong;
    }
}