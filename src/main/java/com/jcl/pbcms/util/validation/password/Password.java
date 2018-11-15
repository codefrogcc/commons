package com.jcl.pbcms.util.validation.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {PasswordImpl.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    boolean required() default true;

    String message() default "{constraint.not.password}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
