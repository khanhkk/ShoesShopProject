package Models;

public class Notification {
    private String hinh;
    private String client;
    private String hoatdong;
    private String thoiGian;
    private boolean status; // true: da doc, false: chua doc
    private String bill;

    public static String STR_HUY = " đã hủy đơn hàng ";
    public static String STR_VAN_CHUYEN = " đang vận chuyển đơn hàng ";
    public static String STR_DAT_HANG = " đã đặt hàng ";

    public Notification() {
    }

    public Notification(String hinh, String client, String hoatdong, String thoiGian, boolean status, String bill) {
        this.hinh = hinh;
        this.client = client;
        this.hoatdong = hoatdong;
        this.thoiGian = thoiGian;
        this.status = status;
        this.bill = bill;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String ten) {
        this.client = ten;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
