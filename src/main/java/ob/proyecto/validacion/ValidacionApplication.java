package ob.proyecto.validacion;

import ob.proyecto.validacion.entities.Role;
import ob.proyecto.validacion.entities.User;
import ob.proyecto.validacion.repositories.RoleRepository;
import ob.proyecto.validacion.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ValidacionApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ValidacionApplication.class, args);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		UserRepository userRepository = context.getBean(UserRepository.class);
		BCryptPasswordEncoder encoder = context.getBean(BCryptPasswordEncoder.class);

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

	}

}
