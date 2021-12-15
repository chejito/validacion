package ob.proyecto.validacion.exceptions;

/**
 * Excepción para el caso en que la duración máxima del código hash se haya superado.
 */
public class SessionExpiredException extends Exception {
    public SessionExpiredException(String message) {
        super("La sesión del usuario '"
                + message + "' ha expirado");
    }
}
