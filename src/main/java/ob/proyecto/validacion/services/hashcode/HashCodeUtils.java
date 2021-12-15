package ob.proyecto.validacion.services.hashcode;

import ob.proyecto.validacion.entities.HashCode;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.repositories.HashCodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class HashCodeUtils {

    HashCodeRepository repository;

    @Value("${app.hashcode.expiration-ms}")
    private long expiration;

    public HashCodeUtils(HashCodeRepository repository) {
        this.repository = repository;
    }

    public HashCode generateHashCode(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        Integer hash = getHash(username, password, timeStamp);

        return new HashCode(hash, timeStamp, user);
    }

    public Integer getHash(String username, String password, Timestamp timeStamp) {
        int hashcode = (username.hashCode() * password.hashCode() * timeStamp.hashCode());
        if (hashcode < 0) {
            hashcode *= -1;
        }
        return hashcode;
    }

    public Boolean validateHashCode(HashCode hashCode) {
        Long now = new Timestamp(System.currentTimeMillis()).getTime();
        Long hashTime = hashCode.getTimeStamp().getTime();

        return (now - hashTime) <= expiration;
    }

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
