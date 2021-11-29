package ob.proyecto.validacion.security.payload;

/**
 * Clase DTO para devolver un token JWT como respuesta.
 */
public class JwtResponse {
    private String token;

    public JwtResponse() { }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
