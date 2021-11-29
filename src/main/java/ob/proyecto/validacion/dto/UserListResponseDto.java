package ob.proyecto.validacion.dto;

import ob.proyecto.validacion.entities.User;

import java.util.List;
/**
 * Clase DTO para listar usuarios de la base de datos.
 */
public class UserListResponseDto {

    private String message;
    private List<User> users;

    public UserListResponseDto() {
    }

    public UserListResponseDto(String message, List<User> users) {
        this.message = message;
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
