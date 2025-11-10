package sv.edu.udb.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import sv.edu.udb.domain.User;
import sv.edu.udb.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(UserRepository users, PasswordEncoder encoder) {
        return args -> {
            users.findByEmail("admin@demo.com").orElseGet(() -> {
                User u = new User();
                u.setFirstName("Admin");
                u.setLastName("Demo");
                u.setEmail("admin@demo.com");
                u.setPassword(encoder.encode("admin123")); // BCrypt
                u.setRoles("ROLE_ADMIN,ROLE_USER");
                return users.save(u);
            });
        };
    }

}

