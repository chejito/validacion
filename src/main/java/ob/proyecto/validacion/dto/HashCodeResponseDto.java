package ob.proyecto.validacion.dto;

/**
 * Clase DTO para devolver el hashcode de un usuario
 * desde la base de datos.
 */
public class HashCodeResponseDto {

    private String message;
    private Integer hashcode;

    public HashCodeResponseDto() {}

    public HashCodeResponseDto(String message, Integer hashcode) {
        this.message = message;
        this.hashcode = hashcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHashcode() {
        return hashcode;
    }

    public void setHashcode(Integer hashcode) {
        this.hashcode = hashcode;
    }
}
