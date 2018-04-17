package Models;



public class Contact {
    private int Hinh;
    private String Ten;

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

    public Contact(int hinh, String ten) {
        Hinh = hinh;
        Ten = ten;
    }
    @Override
    public String toString()  {
        return this.Ten;
    }

}

