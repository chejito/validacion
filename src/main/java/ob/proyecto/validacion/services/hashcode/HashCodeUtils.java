package ob.proyecto.validacion.services.hashcode;

import ob.proyecto.validacion.entities.HashCode;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.repositories.HashCodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Clase de utilidad de Códigos Hash.
 */
@Component
public class HashCodeUtils {

    HashCodeRepository repository;

    @Value("${app.hashcode.expiration-ms}")
    private long expiration;

    public HashCodeUtils(HashCodeRepository repository) {
        this.repository = repository;
    }

    /**
     * Método que genera un objeto de la clase HashCode a partir de un usuario.
     *
     * @param user Usuario
     * @return Objeto de la clase HashCode
     */
    public HashCode generateHashCode(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        Integer hash = getHash(username, password, timeStamp);

        return new HashCode(hash, timeStamp, user);
    }

    /**
     * Método que devuelve un código hash positivo a partir de los datos del usuario.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param timeStamp Marca de tiempo de la operación.
     * @return Código hash.
     */
    public Integer getHash(String username, String password, Timestamp timeStamp) {
        int hashcode = (username.hashCode() * password.hashCode() * timeStamp.hashCode());
        if (hashcode < 0) {
            hashcode *= -1;
        }
        return hashcode;
    }

    /**
     * Método que valida si un código hash ha expirado, comparando la marca de tiempo
     * del objeto HashCode con la marca de tiempo del sistema.
     *
     * @param hashCode Objeto de la clase HashCode.
     * @return True si aún no ha expirado, false si ha expirado.
     */
    public Boolean validateHashCode(HashCode hashCode) {
        Long now = new Timestamp(System.currentTimeMillis()).getTime();
        Long hashTime = hashCode.getTimeStamp().getTime();

        return (now - hashTime) <= expiration;
    }

    /**
     * Método que actualiza el objeto HashCode con la nueva marca de tiempo, reiniciando su duración máxima.
     * También genera un nuevo código hash, que sobreescribe el antiguo.
     *
     * @param user Usuario.
     * @return Código hash.
     */
    public Integer updateHash(User user) {
        HashCode newHashCode = generateHashCode(user);
        HashCode oldHashCode = repository.findByUser(user);
        Integer hashCodeInteger = newHashCode.getHash();
        Timestamp newTimestamp = newHashCode.getTimeStamp();
        oldHashCode.setHash(hashCodeInteger);
        oldHashCode.setTimeStamp(newTimestamp);
        repository.save(oldHashCode);

        return hashCodeInteger;
    }

}
