package sv.edu.udb.autenticacion;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.autenticacion.dto.LoginRequest;
import sv.edu.udb.autenticacion.dto.TokenResponse;
import sv.edu.udb.security.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService auth;
    private final JwtService jwt;

    public AuthController(AuthService auth, JwtService jwt) {
        this.auth = auth; this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest req) {
        var user = auth.authenticate(req.email(), req.password());
        String token = jwt.generateToken(user.getEmail(), user.getRoles());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
