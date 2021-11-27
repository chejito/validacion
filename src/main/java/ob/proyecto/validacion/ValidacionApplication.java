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

		Role userRole = new Role (null, "USER");
		Role adminRole = new Role (null, "ADMIN");

		roleRepository.save(userRole);
		roleRepository.save(adminRole);

//		User user1 = new User();
//		user1.setUsername("admin");
//		user1.setEmail("admin@admin.com");
//		user1.setPassword(encoder.encode("admin"));
//		Set<Role> roles = new HashSet<>();
//		roles.add(userRole);
//		roles.add(adminRole);
//		user1.setRoles(roles);

//		userRepository.save(user1);

		/*File photo1 = new File("src/main/resources/images/imagen1.png");

		try {
			String url = upload.uploadImage(photo1);
			System.out.println("La url de la imagen es: " +  url);
		} catch (Exception e) {
			System.err.println("No se ha podido guardar la imagen. Error: " + e.getMessage());
		}*/

	}

}
