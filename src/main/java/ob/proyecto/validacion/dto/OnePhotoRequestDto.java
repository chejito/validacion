package ob.proyecto.validacion.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase DTO para modificar un usuario de la base de datos, añadiendo
 * dos direcciones url de dos imágenes alojadas en la nube.
 */
public class OnePhotoRequestDto {

    private MultipartFile photo;

    public OnePhotoRequestDto() {}

    public OnePhotoRequestDto(Long id, MultipartFile photo) {
        this.photo = photo;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
