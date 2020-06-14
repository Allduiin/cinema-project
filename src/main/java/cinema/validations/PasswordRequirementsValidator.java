package cinema.validations;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;

public class PasswordRequirementsValidator
        implements ConstraintValidator<PasswordRequirementsConstraint, String> {

    @Override
    public void initialize(PasswordRequirementsConstraint constraint) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext cxt) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 16),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit),
                new CharacterRule(EnglishCharacterData.Special)
        ));
        return validator
                .validate(new PasswordData(password))
                .isValid();
    }
}
