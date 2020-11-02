package com.jin10.spidermanage.annotation.valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class URLValid implements ConstraintValidator<URL, String> {

    private static final String REGEX_URL = "/(http|https):\\/\\/([\\w.]+\\/?)\\S*/";

    @Override
    public boolean isValid(String url , ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isNotBlank(url)) {
            String[] schemes = {"http","https"}; // DEFAULT schemes ="http","https","ftp"
            UrlValidator urlValidator = new UrlValidator(schemes);
            return urlValidator.isValid(url);
        }
        return false;
    }
}
