package com.jcl.pbcms.util.validation.number;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {NumberValidationImpl.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberValidation {

    boolean required() default true;

    int verifyType() default 0;//验证类型

    String message() default "{constraint.not.NnmberValidation}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
