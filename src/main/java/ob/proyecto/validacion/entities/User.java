package ob.proyecto.validacion.entities;

import javax.persistence.*;
import java.util.Set;
/**
 * Entidad que gestiona los usuarios en la base de datos.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullname;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean validated = false;

    @Column
    private String urlDni1;

    @Column
    private String urlDni2;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID") })

    private Set<Role> roles;

    @OneToOne
    private HashCode hash;

    public User() {}

    public User(String fullname, String email, String username, String password, Set<Role> roles) {
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
