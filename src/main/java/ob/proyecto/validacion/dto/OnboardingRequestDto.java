package ob.proyecto.validacion.dto;

import org.springframework.web.multipart.MultipartFile;

public class OnboardingRequestDto {

    private Long id;
    private String phone;
    private MultipartFile photo1;
    private MultipartFile photo2;

    public OnboardingRequestDto() {
    }

    public OnboardingRequestDto(Long id, String phone, MultipartFile photo1, MultipartFile photo2) {
        this.id = id;
        this.phone = phone;
        this.photo1 = photo1;
        this.photo2 = photo2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
