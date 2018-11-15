package com.jcl.pbcms.util.validation.meanwhileExist;

import javax.validation.Payload;

public @interface MeanwhileExist {

    String field();

    String verifyField();

    String message() default "{constraint.not.meanwhileExist}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
