package lk.ijse.dto;

public class AdminDto {
    private String username;
    private  String password;
    private String email;
    private  String type;

    public AdminDto(String username,String password,String email,String type){
        this.username=username;
        this.password=password;
        this.email=email;
        this.type=type;
    }

    public AdminDto(String username, String pw,String type) {
        this.username = username;
        this.password = pw;
        this.type = type;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
