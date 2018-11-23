package com.github.commons.utils.validation.meanwhileExist;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class MeanwhileExistValidator implements ConstraintValidator<MeanwhileExist,Object> {

    private String field;

    private String verifyField;

    @Override
    public void initialize(MeanwhileExist constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.verifyField = constraintAnnotation.verifyField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String fieldValue = BeanUtils.getProperty(value, field);
            String verifyFieldValue = BeanUtils.getProperty(value,verifyField);
            boolean valid = (fieldValue == null) && (verifyFieldValue == null);
            if(valid){
                return true;
            }
            boolean validOne = (fieldValue != null) && (verifyFieldValue != null);
            return validOne;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;
    }
}
