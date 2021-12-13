package ob.proyecto.validacion.services.uploadimage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ob.proyecto.validacion.exceptions.EmptyImageException;
import ob.proyecto.validacion.exceptions.InvalidImageFormatException;
import ob.proyecto.validacion.services.hashcode.HashCodeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Clase que implementa la subida de una imagen al
 * servicio de alojamiento en la nube Cloudinary
 */
@Service
public class UploadImageCloudinaryServiceImpl implements UploadImageService {

    private final String cloudName = System.getenv("CLOUDINARY_CLOUD_NAME");
    private final String apiKey = System.getenv("CLOUDINARY_API_KEY");
    private final String apiSecret = System.getenv("CLOUDINARY_API_SECRET");
    private final Map params = ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
    );

    Logger log = LoggerFactory.getLogger(HashCodeServiceImpl.class);


    Cloudinary cloudinary = new Cloudinary(params);

    /**
     * Método que efectúa la subida del archivo
     *
     * @param photo Imagen que se recibe
     * @return String con la url de la imagen alojada
     */
    @Override
    public String uploadImage(MultipartFile photo) throws EmptyImageException, InvalidImageFormatException, IOException {
        log.warn(photo.getContentType());
        if (photo.isEmpty()) {
            String message = "Error: El archivo está vacío";
            log.error(message);
            throw new EmptyImageException(message);
//        } else if (!photo.getName().endsWith(".png") && !photo.getName().endsWith(".jpg")) {
        } else if (!Objects.requireNonNull(photo.getContentType()).equalsIgnoreCase("image/png") && !photo.getContentType().equalsIgnoreCase("image/jpeg")) {
            String message = "Error: El formato del archivo es incorrecto. Formatos admitidos '.png' y '.jpeg'";
            log.error(message);
            throw new InvalidImageFormatException(message);
        }
        Map response = cloudinary.uploader().upload((photo.getBytes()),
                ObjectUtils.emptyMap());

        return response.get("secure_url").toString();
    }
}
