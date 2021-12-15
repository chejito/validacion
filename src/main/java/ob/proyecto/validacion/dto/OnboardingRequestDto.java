package ob.proyecto.validacion.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase DTO para modificar un usuario de la base de datos, añadiendo dos
 * direcciones url de dos imágenes alojadas en la nube.
 */
public class OnboardingRequestDto {

    private MultipartFile photo1;
    private MultipartFile photo2;

    public OnboardingRequestDto() {
    }

    public OnboardingRequestDto(MultipartFile photo1, MultipartFile photo2) {
        this.photo1 = photo1;
        this.photo2 = photo2;
    }

    public MultipartFile getPhoto1() {
        return photo1;
    }

    public void setPhoto1(MultipartFile photo1) {
        this.photo1 = photo1;
    }

    public MultipartFile getPhoto2() {
        return photo2;
    }

    public void setPhoto2(MultipartFile photo2) {
        this.photo2 = photo2;
    }
}
