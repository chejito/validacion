package ob.proyecto.validacion.services;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Clase que implementa la subida de una imagen al
 * servicio de alojamiento en la nube Cloudinary
 */
@Service
public class SubirImagenCloudinaryServiceImpl implements SubirImagenService {

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
     * @throws IOException Si no se puede efectuar la subida del archivo
     */
    @Override
    public String uploadImage(File photo) throws IOException {
        System.out.println(cloudName + " " + apiKey + " " + apiSecret  );
        Map response = cloudinary.uploader().upload((photo),
                ObjectUtils.asMap("public_id", "olympic_flag"));

        return response.get("secure_url").toString();
    }
}
