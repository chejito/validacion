package ob.proyecto.validacion.security.payload;

/**
 * Clase DTO para devolver mensajes genéricos como respuesta.
 */
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
