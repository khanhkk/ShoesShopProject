package Models;

/**
 * Created by kk on 30/03/2018.
 */

public class Shop {
    private String name, phone, email, bankAccount, address, image, nguoidaidien, fb;
    //private int image;

    public Shop() {
    }

    public Shop(String name, String phone, String email, String bankAccount, String address, String image) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bankAccount = bankAccount;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNguoidaidien() {
        return nguoidaidien;
    }

    public void setNguoidaidien(String nguoidaidien) {
        this.nguoidaidien = nguoidaidien;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

}
