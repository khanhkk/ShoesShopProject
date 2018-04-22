package Models;

public class Client {
    private String name, email, address, phone, image;
    //private int images;

    public Client() {
    }
//    public Client(String name, String email, String phone, String address) {
//        this.name = name;
//        this.email = email;
//        this.address = address;
//        this.phone = phone;
//    }
    public Client(String name, String email, String address, String phone, String image) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImages() {
        return image;
    }

    public void setImages(String images) {
        this.image = images;
    }
}
