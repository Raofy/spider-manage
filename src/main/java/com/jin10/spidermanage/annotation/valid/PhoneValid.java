package com.jin10.spidermanage.annotation.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValid implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
            Pattern p = Pattern.compile("/^[1]([3-9])[0-9]{9}$/");
            Matcher m = p.matcher(value);
            return m.matches();
        }
       return false;
    }
}
