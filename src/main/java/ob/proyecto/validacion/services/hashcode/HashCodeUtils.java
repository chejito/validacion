package ob.proyecto.validacion.services.hashcode;

import ob.proyecto.validacion.entities.HashCode;
import ob.proyecto.validacion.entities.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class HashCodeUtils {

    public HashCode generateHashCode(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        Integer hash = getHash(username, password, timeStamp);

        return new HashCode(hash, timeStamp, user);
    }

    public Integer getHash(String username, String password, Timestamp timeStamp) {
        Integer hashcode = (username.hashCode() * password.hashCode() * timeStamp.hashCode());
        if (hashcode < 0) {
            hashcode *= -1;
        }
        return hashcode;
    }

    public Boolean validateHashCode(HashCode hashCode) {
        Long now = new Timestamp(System.currentTimeMillis()).getTime();
        Long hashTime = hashCode.getTimeStamp().getTime();
        long sessionLimit = 900000L;

        return (now - hashTime) <= sessionLimit;
    }

}
