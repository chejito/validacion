package ob.proyecto.validacion.dto;

/**
 * Clase DTO para devolver el hashcode de un usuario
 * desde la base de datos.
 */
public class HashCodeResponseDto {

    private String message;
    private Integer hashCode;

    public HashCodeResponseDto() {}

    public HashCodeResponseDto(String message, Integer hashcode) {
        this.message = message;
        this.hashCode = hashcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHashCode() {
        return hashCode;
    }

    public void setHashCode(Integer hashCode) {
        this.hashCode = hashCode;
    }
}
