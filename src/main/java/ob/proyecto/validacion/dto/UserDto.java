package ob.proyecto.validacion.dto;

import ob.proyecto.validacion.entities.Role;
import ob.proyecto.validacion.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

public class UserDto {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private String fullname;
    private String email;
    private String username;
    private String password;
    private String phone;
    private Set<Role> roles;
    private String urlDni1;
    private String urlDni2;
    private boolean validated;

    public UserDto(String fullname, String email, String username, String password) {
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserDto(User user){
        this.fullname = user.getFullname();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.urlDni1 = user.getUrlDni1();
        this.urlDni2 = user.getUrlDni2();
        this.validated = user.isValidated();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlDni1() {
        return urlDni1;
    }

    public void setUrlDni1(String urlDni1) {
        this.urlDni1 = urlDni1;
    }

    public String getUrlDni2() {
        return urlDni2;
    }

    public void setUrlDni2(String urlDni2) {
        this.urlDni2 = urlDni2;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        user.setPhone(phone);
        user.setRoles(roles);
        user.setUrlDni1(urlDni1);
        user.setUrlDni2(urlDni2);

        return user;
    }
}
