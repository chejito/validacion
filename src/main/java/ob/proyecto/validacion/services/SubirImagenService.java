package ob.proyecto.validacion.services;

import java.io.File;
import java.io.IOException;

/**
 * Interfaz que define el método para subir una imagen
 * a un servicio de alojamiento en la nube
 */
public interface SubirImagenService {
    String uploadImage(File photo) throws IOException;
}
