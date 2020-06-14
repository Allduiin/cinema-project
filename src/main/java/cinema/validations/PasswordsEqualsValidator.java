package cinema.validations;

import cinema.model.dto.UserRequestRegistrationDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualsValidator
        implements ConstraintValidator<PasswordsEqualsConstraint, UserRequestRegistrationDto> {

    @Override
    public void initialize(PasswordsEqualsConstraint constraint) {
    }

    @Override
    public boolean isValid(UserRequestRegistrationDto userDto, ConstraintValidatorContext cxt) {
        return userDto.getPassword().equals(userDto.getRepeatPassword());
    }
}
