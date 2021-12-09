package ob.proyecto.validacion.services.uploadimage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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

    Cloudinary cloudinary = new Cloudinary(params);

    /**
     * Método que efectúa la subida del archivo
     *
     * @param photo Imagen que se recibe
     * @return String con la url de la imagen alojada
     */
    @Override
    public String uploadImage(MultipartFile photo) {
        try {
            Map response = cloudinary.uploader().upload((photo.getBytes()),
                    ObjectUtils.emptyMap());

            return response.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
    }
}


}
