package sv.edu.udb.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password; // ✅ nuevo campo
    private String roles;    // ✅ nuevo campo
    private LocalDate birthDate;
}
