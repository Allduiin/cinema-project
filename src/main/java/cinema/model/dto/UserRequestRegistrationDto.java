package cinema.model.dto;

import cinema.validations.EmailConstraint;
import cinema.validations.PasswordRequirementsConstraint;
import cinema.validations.PasswordsEqualsConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@PasswordsEqualsConstraint
public class UserRequestRegistrationDto {
    @NotNull
    @EmailConstraint
    private String email;
    @PasswordRequirementsConstraint
    private String password;
    private String repeatPassword;
}

