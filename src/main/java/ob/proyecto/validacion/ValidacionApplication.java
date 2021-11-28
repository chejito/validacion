package ob.proyecto.validacion;

import ob.proyecto.validacion.entities.Role;
import ob.proyecto.validacion.repositories.RoleRepository;
import ob.proyecto.validacion.repositories.UserRepository;
import ob.proyecto.validacion.services.UploadImageCloudinaryServiceImpl;
import ob.proyecto.validacion.services.UploadImageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ValidacionApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ValidacionApplication.class, args);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		UserRepository userRepository = context.getBean(UserRepository.class);
		BCryptPasswordEncoder encoder = context.getBean(BCryptPasswordEncoder.class);
		UploadImageService upload = context.getBean(UploadImageCloudinaryServiceImpl.class);





	}

}
