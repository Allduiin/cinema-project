package cinema.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
        ConstraintValidator<EmailConstraint, String> {
    private static final String EMAIL_REGEX = "^\\S+@\\S+\\.\\S+$";

    @Override
    public void initialize(EmailConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext cxt) {
        return email.matches(EMAIL_REGEX);
    }
}
