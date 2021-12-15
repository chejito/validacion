package ob.proyecto.validacion.exceptions;

/**
 * Excepción para el caso en que el usuario no se encuentre en la base de datos.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super("Usuario no encontrado: " + message);
    }
}
