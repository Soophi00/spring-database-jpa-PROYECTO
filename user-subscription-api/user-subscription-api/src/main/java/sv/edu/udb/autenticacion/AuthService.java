package sv.edu.udb.autenticacion;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sv.edu.udb.domain.User;
import sv.edu.udb.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository users, PasswordEncoder encoder) {
        this.users = users; this.encoder = encoder;
    }

    public User authenticate(String email, String rawPassword) {
        User u = users.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!encoder.matches(rawPassword, u.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return u;
    }
}
