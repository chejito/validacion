package ob.proyecto.validacion.dto;

import ob.proyecto.validacion.entities.User;

/**
 * Clase DTO para el devolver dos direcciones url en la respuesta, junto con un mensaje de texto.
 */
public class OnboardingResponseDto {

    private String message;
    private String urlDni1;
    private String urlDni2;

    public OnboardingResponseDto() {}

    public OnboardingResponseDto(String message, String urlDni1, String urlDni2) {
        this.message = message;
        this.urlDni1 = urlDni1;
        this.urlDni2 = urlDni2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrlDni1() {
        return urlDni1;
    }

    public void setUrlDni1(String urlDni1) {
        this.urlDni1 = urlDni1;
    }

    public String getUrlDni2() {
        return urlDni2;
    }

    public void setUrlDni2(String urlDni2) {
        this.urlDni2 = urlDni2;
    }
}
