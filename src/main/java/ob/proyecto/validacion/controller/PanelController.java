package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.services.users.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador del proceso de Validación de la identidad de un usuario
 */
@RestController
@RequestMapping("/api/panel")
public class PanelController {

    private final UserServiceImpl userService;

    public PanelController(UserServiceImpl userService){
        this.userService = userService;
    }

    /**
     * Método que valida un usuario de la base de datos.
     * @param username Nombre de usuario del usuario a validar.
     * @return Usuario actualizado.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/validate/{username}")
    public ResponseEntity<?> validate(@PathVariable String username){
        return userService.validate(username);
    }

    /**
     * Método que lista un usuario de la base de datos.
     * @param username Nombre de usuario del usuario a listar.
     * @return Usuario solicitado.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/{username}")
    public ResponseEntity<?>  getUser(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
    /**
     * Método que lista todos los usuarios de la base de datos.
     * @return Lista de todos los usuarios.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?>  getAllUsers(){
        return userService.getAllUsers();
    }
}
