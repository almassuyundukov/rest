package ru.easyum.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<EmailCheck, String> {

    private String endOfEmail;
    @Override
    public void initialize(EmailCheck emailCheck) {
        endOfEmail = emailCheck.value();
    }

    @Override
    public boolean isValid(String enteredValue, ConstraintValidatorContext constraintValidatorContext) {
        return enteredValue.endsWith(endOfEmail);
    }
}
