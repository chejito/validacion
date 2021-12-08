package ob.proyecto.validacion.dto;

/**
 * Clase DTO para devolver el hashcode de un usuario
 * desde la base de datos.
 */
public class HashCodeResponseDto {

    private String message;
    private Integer hash;

    public HashCodeResponseDto() {}

    public HashCodeResponseDto(String message, Integer hash) {
        this.message = message;
        this.hash = hash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }
}
