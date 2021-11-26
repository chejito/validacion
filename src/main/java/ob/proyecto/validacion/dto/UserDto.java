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
    private String dni1;
    private String dni2;

    public UserDto(String fullname, String email, String username, String password) {
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getDni1() {
        return dni1;
    }

    public void setDni1(String dni1) {
        this.dni1 = dni1;
    }

    public String getDni2() {
        return dni2;
    }

    public void setDni2(String dni2) {
        this.dni2 = dni2;
    }

    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setPassword(encoder.encode(password));
        user.setPhone(phone);
        user.setRoles(roles);
        user.setDni1(dni1);
        user.setDni2(dni2);

        return user;
    }
}
