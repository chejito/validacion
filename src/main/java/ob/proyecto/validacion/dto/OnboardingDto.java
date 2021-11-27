package ob.proyecto.validacion.dto;

import org.springframework.web.multipart.MultipartFile;

public class OnboardingDto {

    private String username;
    private String phone;
    private String dni1;
    private MultipartFile foto1;
    private String dni2;
    private MultipartFile foto2;

    public OnboardingDto() {
    }

    public OnboardingDto(String username, String phone, String dni1, MultipartFile foto1, String dni2, MultipartFile foto2) {
        this.username = username;
        this.phone = phone;
        this.dni1 = dni1;
        this.foto1 = foto1;
        this.dni2 = dni2;
        this.foto2 = foto2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDni1() {
        return dni1;
    }

    public void setDni1(String dni1) {
        this.dni1 = dni1;
    }

    public MultipartFile getFoto1() {
        return foto1;
    }

    public void setFoto1(MultipartFile foto1) {
        this.foto1 = foto1;
    }

    public String getDni2() {
        return dni2;
    }

    public void setDni2(String dni2) {
        this.dni2 = dni2;
    }

    public MultipartFile getFoto2() {
        return foto2;
    }

    public void setFoto2(MultipartFile foto2) {
        this.foto2 = foto2;
    }
}
