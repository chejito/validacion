package ob.proyecto.validacion.dto;

public class LoginUser {

    private String username;
    private String password;

    public LoginUser() {
    }

    public LoginUser(String username, String email) {
        this.username = username;
        this.password = email;
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
}
