package ob.proyecto.validacion.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super("Usuario no encontrado: " + message);
    }
}
