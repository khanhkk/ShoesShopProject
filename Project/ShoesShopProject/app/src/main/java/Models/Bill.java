package Models;

public class Bill {
    private String nameClient, phone, address, email, shop, time;

    public Bill() {
    }

    public Bill(String nameClient, String phone, String address, String email, String shop, String time) {
        this.nameClient = nameClient;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.shop = shop;
        this.time = time;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
