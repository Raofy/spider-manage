package com.jin10.spidermanage.annotation.valid;

import com.jin10.spidermanage.bean.label.InsertBody;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotBlank;

public class CronValid implements ConstraintValidator<Cron, String> {


    @Override
    public boolean isValid(String cron , ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isNotBlank(cron)) {
            return CronExpression.isValidExpression(cron);
        }
        return false;
    }
}
