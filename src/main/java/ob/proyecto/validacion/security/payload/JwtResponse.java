package ob.proyecto.validacion.security.payload;

/**
 * Clase DTO para devolver un token JWT como respuesta.
 */
public class JwtResponse {

    private String token;
    private String role;
    private String username;
    private Integer hashcode;

    public JwtResponse() { }

    public JwtResponse(String token, String role, String username, Integer hashcode) {
        this.token = token;
        this.role = role;
        this.username = username;
        this.hashcode = hashcode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHashcode() {
        return hashcode;
    }

    public void setHashcode(Integer hashcode) {
        this.hashcode = hashcode;
    }
}
