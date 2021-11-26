package ob.proyecto.validacion.dto;

import java.io.File;

public class OnboardingDto {

    private String username;
    private String phone;
    private String dni1;
    private File foto1;
    private String dni2;
    private File foto2;

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

    public String getDni2() {
        return dni2;
    }

    public void setDni2(String dni2) {
        this.dni2 = dni2;
    }

    public File getFoto1() {
        return foto1;
    }

    public void setFoto1(File foto1) {
        this.foto1 = foto1;
    }

    public File getFoto2() {
        return foto2;
    }

    public void setFoto2(File foto2) {
        this.foto2 = foto2;
    }
}
