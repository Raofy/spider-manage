package com.jin10.spidermanage.annotation.valid;

import org.apache.http.annotation.Contract;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 时间Cron表达式校验
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CronValid.class)
public @interface Cron {
    String message() default "cron表达式不合法呀";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //关联类

}
