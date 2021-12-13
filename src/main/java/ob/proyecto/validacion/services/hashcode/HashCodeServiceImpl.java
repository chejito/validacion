package ob.proyecto.validacion.services.hashcode;

import ob.proyecto.validacion.dto.HashCodeResponseDto;
import ob.proyecto.validacion.dto.UserResponseDto;
import ob.proyecto.validacion.entities.HashCode;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.exceptions.HashCodeNotFoundException;
import ob.proyecto.validacion.exceptions.SessionExpiredException;
import ob.proyecto.validacion.repositories.HashCodeRepository;
import ob.proyecto.validacion.repositories.UserRepository;
import ob.proyecto.validacion.security.payload.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


@Service
public class HashCodeServiceImpl implements HashCodeService {

    @Autowired
    private final HashCodeRepository hashCodeRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final HashCodeUtils utils;

    Logger log = LoggerFactory.getLogger(HashCodeServiceImpl.class);

    public HashCodeServiceImpl(HashCodeRepository hashCodeRepository, UserRepository userRepository, HashCodeUtils utils) {
        this.hashCodeRepository = hashCodeRepository;
        this.userRepository = userRepository;
        this.utils = utils;
    }


    @Override
    public ResponseEntity<?> update(String username) {
        try {
            Optional<User> oUser = userRepository.findByUsername(username);
            if (oUser.isPresent()) {
                User user = oUser.get();
                HashCode newHashCode = utils.generateHashCode(user);
                HashCode oldHashCode = hashCodeRepository.findByUser(user);
                Integer hashCode = newHashCode.getHash();
                Timestamp newTimestamp = newHashCode.getTimeStamp();
                oldHashCode.setHash(hashCode);
                oldHashCode.setTimeStamp(newTimestamp);
                hashCodeRepository.save(oldHashCode);

                String message = "Nuevo HashCode del usuario '" + username + "': " + hashCode;
                log.warn(message);

                return ResponseEntity.ok(new HashCodeResponseDto(message, hashCode));
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            log.error(message);

            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(message));
        }
    }

    @Override
    public ResponseEntity<?> validate(Integer hash) {
        ArrayList<HashCode> hashCodes = (ArrayList<HashCode>) hashCodeRepository.findAll();
        try {
            for (HashCode hashCode : hashCodes) {
                log.warn(hashCode.toString());
                if (Objects.equals(hashCode.getHash(), hash)) {
                    User user = hashCode.getUser();
                    if (utils.validateHashCode(hashCode)) {
                        String message = "Usuario autorizado: " + user.getUsername();
                        log.warn(message);

                        return ResponseEntity.ok(new UserResponseDto(message, user));
                    } else {
                        throw new SessionExpiredException(user.getUsername());
                    }
                }
            }

            throw new HashCodeNotFoundException(hash.toString());
        } catch (Exception e) {
            String message = e.getMessage();
            log.error(message);

            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(message));
        }


    }

}
