package ob.proyecto.validacion.exceptions;

public class SessionExpiredException extends Exception {
    public SessionExpiredException(String message) {
        super("La sesión del usuario '"
                + message + "' ha expirado");
    }
}
