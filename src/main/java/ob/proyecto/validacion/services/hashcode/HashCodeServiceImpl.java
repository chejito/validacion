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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * Servicio que gestiona la actualización y validación de códigos hash de usuarios.
 */
@Service
public class HashCodeServiceImpl implements HashCodeService {

    private final HashCodeRepository hashCodeRepository;
    private final UserRepository userRepository;
    private final HashCodeUtils utils;

    Logger log = LoggerFactory.getLogger(HashCodeServiceImpl.class);

    public HashCodeServiceImpl(HashCodeRepository hashCodeRepository, UserRepository userRepository, HashCodeUtils utils) {
        this.hashCodeRepository = hashCodeRepository;
        this.userRepository = userRepository;
        this.utils = utils;
    }

    /**
     * Método que actualiza un código hash de un usuario para poder volver a usarlo.
     * Renueva la duración máxima del código hash.
     *
     * @param username Nombre de usuario.
     * @return Un mensaje y el código hash en caso positivo. Un mensaje de error en caso negativo.
     */
    @Override
    public ResponseEntity<?> update(String username) {
        try {
            Optional<User> oUser = userRepository.findByUsername(username);
            if (oUser.isPresent()) {
                User user = oUser.get();
                Integer hashcode = utils.updateHash(user);
                String message = "Nuevo HashCode del usuario '" + username + "': " + hashcode;
                log.warn(message);

                return ResponseEntity.ok(new HashCodeResponseDto(message, hashcode));
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

    /**
     * Método que valida un código hash de un usuario para poder acceder al OnBoarding.
     * @param hash Código hash del usuario.
     * @return Un mensaje y el código hash en caso positivo. En caso negativo, un mensaje de error.
     */
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
