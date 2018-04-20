package Models;

/**
 * Created by kk on 30/03/2018.
 */

public class Shop {
    private String id, name, phone, email, bankAccount, representative, address, listPromotions;
    private int image;

    public Shop() {
    }

    public Shop(String id, String name, String address, String phone, String email, String bankAccount, String representative, int image, String list) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bankAccount = bankAccount;
        this.representative = representative;
        this.image = image;
        this.address = address;
        this.listPromotions = list;
    }

    public Shop(String id, String name, String bankAccount) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getListPromotions() {
        return listPromotions;
    }

    public void setListPromotions(String listPromotions) {
        this.listPromotions = listPromotions;
    }
}
