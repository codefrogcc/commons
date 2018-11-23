package com.github.commons.utils.validation.password;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    private String field;

    private String verifyField;

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.verifyField = constraintAnnotation.verifyField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String fieldValue= BeanUtils.getProperty(value,field);
            String verifyFieldValue = BeanUtils.getProperty(value,verifyField);
            boolean valid = (fieldValue == null) && (verifyFieldValue == null);
            if(valid){
                return true;
            }
            boolean match = (fieldValue!=null) && fieldValue.equals(verifyFieldValue);
            if(!match){
                String messageTemplate = context.getDefaultConstraintMessageTemplate();
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(messageTemplate)
                        .addNode(verifyField)
                        .addConstraintViolation();
            }
            return match;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
