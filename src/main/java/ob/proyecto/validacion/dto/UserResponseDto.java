package ob.proyecto.validacion.dto;

import ob.proyecto.validacion.entities.User;
/**
 * Clase DTO para el devolver un usuario en la respuesta, junto con un mensaje de texto.
 */
public class UserResponseDto {

    private String message;
    private User user;

    public UserResponseDto() {
    }

    public UserResponseDto(String message, User user) {
        this.message = message;
        this.user = user;
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
}
