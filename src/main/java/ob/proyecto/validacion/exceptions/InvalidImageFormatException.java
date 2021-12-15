package ob.proyecto.validacion.exceptions;

/**
 * Excepción para el caso en que el archivo recibido no sea del tipo permitido (.jpeg, .jpg y .png).
 */
public class InvalidImageFormatException extends Exception {
    public InvalidImageFormatException(String message) {
        super(message);
    }
}
