package ob.proyecto.validacion.security.payload;

/**
 * Clase DTO para devolver un token JWT como respuesta.
 */
public class JwtResponse {
    private String token;

    private String role;

    private String username;

    public JwtResponse() { }

    public JwtResponse(String token) {
        this.token = token;
        this.role = null;
    }

    public JwtResponse(String token, String role) {
        this.token = token;
        this.role = role;
        this.username = null;
    }

    public JwtResponse(String token, String role, String username) {
        this.token = token;
        this.role = role;
        this.username = username;
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
}
