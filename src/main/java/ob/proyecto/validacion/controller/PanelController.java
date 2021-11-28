package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panel")
public class PanelController {

    private final UserServiceImpl userService;

    public PanelController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/validate/{id}")
    public ResponseEntity<?> validate(@PathVariable Long id){
        return userService.validate(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/{username}")
    public ResponseEntity<?>  getUser(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?>  getAllUsers(){
        return userService.getAllUsers();
    }
}
