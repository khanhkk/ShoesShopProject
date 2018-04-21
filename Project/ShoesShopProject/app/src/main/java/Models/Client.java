package Models;

public class Client {
    private String name, email, address, phone;
    private int images;

    public Client() {
    }
    public Client(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
    public Client(String name, String email, String address, String phone, int images) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.images = images;
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

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
