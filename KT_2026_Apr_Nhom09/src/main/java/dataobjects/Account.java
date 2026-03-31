package dataobjects;

public class Account {
    // 1. Các thuộc tính (Fields)
    private String username;
    private String password;

    // 2. Hàm khởi tạo (Constructor) - Giúp tạo nhanh đối tượng Account
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 3. Các hàm Getter và Setter (Để lấy và gán giá trị)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
