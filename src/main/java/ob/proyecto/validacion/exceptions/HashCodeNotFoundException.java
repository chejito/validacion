package ob.proyecto.validacion.exceptions;

/**
 * Excepción para el caso en que el código hash no se encuentre en la base de datos.
 */
public class HashCodeNotFoundException extends Exception {
    public HashCodeNotFoundException(String message) {
        super("HashCode no encontrado: " + message);
    }
}
