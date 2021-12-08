package ob.proyecto.validacion.dto;

import ob.proyecto.validacion.entities.User;

/**
 * Clase DTO para el devolver un usuario en la respuesta
 * y su hashcode, junto con un mensaje de texto.
 */
public class UserRegisterResponseDto {

    private String message;
    private User user;
    private Integer hashcode;

    public UserRegisterResponseDto() {
    }

    public UserRegisterResponseDto(String message, User user) {
        this.message = message;
        this.user = user;
        this.hashcode = null;
    }

    public UserRegisterResponseDto(String message, User user, Integer hashcode) {
        this.message = message;
        this.user = user;
        this.hashcode = hashcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getHashcode() {
        return hashcode;
    }

    public void setHashcode(Integer hashcode) {
        this.hashcode = hashcode;
    }
}
