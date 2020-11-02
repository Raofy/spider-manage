package com.jin10.spidermanage.annotation.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * URL校验
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = URLValid.class)
public @interface URL {
    String message() default "URL不合法呀!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //关联类

}
