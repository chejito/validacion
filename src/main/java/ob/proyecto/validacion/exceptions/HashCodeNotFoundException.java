package ob.proyecto.validacion.exceptions;

public class HashCodeNotFoundException extends Exception {
    public HashCodeNotFoundException(String message) {
        super("HashCode no encontrado: " + message);
    }
}
