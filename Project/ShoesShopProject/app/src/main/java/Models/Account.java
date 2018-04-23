package Models;

public class Account {
    private String  name,email;
    private int level;

    public Account() {
    }

    public Account(String name, String email, int level) {
        this.name = name;
        this.email = email;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
