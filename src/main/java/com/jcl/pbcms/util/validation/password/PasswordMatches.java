package com.jcl.pbcms.util.validation.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String field();

    String verifyField();

    String message() default "{constraint.not.passwordMatches}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
