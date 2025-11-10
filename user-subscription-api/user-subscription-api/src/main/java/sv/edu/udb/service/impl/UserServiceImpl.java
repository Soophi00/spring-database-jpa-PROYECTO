package sv.edu.udb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.domain.User;
import sv.edu.udb.exception.ConflictException;
import sv.edu.udb.exception.NotFoundException;
import sv.edu.udb.repository.UserRepository;
import sv.edu.udb.resource.UserRequest;
import sv.edu.udb.resource.UserResponse;
import sv.edu.udb.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public UserResponse create(UserRequest r) {
        // ✅ Verifica si el correo ya está en uso
        if (repo.existsByEmail(r.getEmail())) {
            throw new ConflictException("Email already in use");
        }

        // ✅ Convierte el request a entidad User
        User e = toEntity(r);

        // ✅ Encripta la contraseña recibida y asigna roles desde el request
        e.setPassword(encoder.encode(r.getPassword()));
        e.setRoles(r.getRoles());

        // ✅ Guarda el usuario y devuelve la respuesta
        return toResp(repo.save(e));
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserRequest r) {
        User e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!e.getEmail().equalsIgnoreCase(r.getEmail()) && repo.existsByEmail(r.getEmail())) {
            throw new ConflictException("Email already in use");
        }

        e.setFirstName(r.getFirstName());
        e.setLastName(r.getLastName());
        e.setEmail(r.getEmail());
        e.setBirthDate(r.getBirthDate());
        // ⚙️ Si se envía nueva contraseña, actualízala también
        if (r.getPassword() != null && !r.getPassword().isBlank()) {
            e.setPassword(encoder.encode(r.getPassword()));
        }

        e.setRoles(r.getRoles());
        return toResp(repo.save(e));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        repo.delete(e);
    }

    @Override
    public UserResponse getById(Long id) {
        return repo.findById(id)
                .map(this::toResp)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public Page<UserResponse> list(String q, Pageable pageable) {
        String term = (q == null || q.isBlank()) ? null : q.trim();
        return repo.search(term, pageable).map(this::toResp);
    }

    // 🔹 Convierte de DTO a Entidad
    private User toEntity(UserRequest r) {
        User e = new User();
        e.setFirstName(r.getFirstName());
        e.setLastName(r.getLastName());
        e.setEmail(r.getEmail());
        e.setBirthDate(r.getBirthDate());
        return e;
    }

    // 🔹 Convierte de Entidad a DTO de respuesta
    private UserResponse toResp(User u) {
        return new UserResponse(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getEmail(),
                u.getBirthDate()
        );
    }
}
