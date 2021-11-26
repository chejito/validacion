package ob.proyecto.validacion.controller;

import ob.proyecto.validacion.dto.ValidationDto;
import ob.proyecto.validacion.security.payload.MessageResponse;
import ob.proyecto.validacion.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/panel")
public class PanelController {

    private final UserServiceImpl userService;

    public PanelController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/validate")
    public ResponseEntity<MessageResponse> validate(@RequestBody ValidationDto validationDto){
        return userService.validate(validationDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/users")
    public ResponseEntity<?>  getUserDto(@RequestBody ValidationDto validationDto){
        return userService.getUserDto(validationDto);
    }
}
