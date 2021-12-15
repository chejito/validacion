package ob.proyecto.validacion.exceptions;

/**
 * Excepción para el caso en que ambos archivos ya se han enviado
 * al servicio de almacenamiento y se vuelven a enviar más archivos.
 */
public class BothFilesAlreadyExistException extends Exception {
    public BothFilesAlreadyExistException(String message) {
        super(message);
    }
}
