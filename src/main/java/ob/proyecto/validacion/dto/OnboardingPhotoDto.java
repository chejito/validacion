package ob.proyecto.validacion.dto;

import org.springframework.web.multipart.MultipartFile;

public class OnboardingPhotoDto {

    private String username;
    private MultipartFile photo;

    public OnboardingPhotoDto() {
    }

    public OnboardingPhotoDto(String username, MultipartFile photo) {
        this.username = username;
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
