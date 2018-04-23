package Models;

/**
 * Created by kk on 30/03/2018.
 */

public class Shop {
    private String name, phone, email, bankAccount, address, image;
    //private int image;

    public Shop() {
    }

    public Shop(String name, String phone, String email, String bankAccount, String address, String image) {
        //this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bankAccount = bankAccount;
        //this.representative = representative;
        this.address = address;
        //this.listPromotions = listPromotions;
        this.image = image;
    }

//    public Shop(String name, String email, String phone, String address, String bankAccount) {
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//        this.bankAccount = bankAccount;
//        this.address = address;
//    }
//    public Shop(String id, String name,String bankAccount) {
//        this.id = id;
//        this.name = name;
//        this.bankAccount = bankAccount;
//    }
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

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

//    public String getRepresentative() {
//        return representative;
//    }
//
//    public void setRepresentative(String representative) {
//        this.representative = representative;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public String getListPromotions() {
//        return listPromotions;
//    }
//
//    public void setListPromotions(String listPromotions) {
//        this.listPromotions = listPromotions;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
