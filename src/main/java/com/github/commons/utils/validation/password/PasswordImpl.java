package com.github.commons.utils.validation.password;

import com.github.commons.utils.validation.ValidationUtil;
import tk.mybatis.mapper.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordImpl implements ConstraintValidator<Password,String> {

    private boolean required;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required){
            return ValidationUtil.isPassword(value);
        }else{
            if(StringUtil.isEmpty(value)){
                return true;
            }else{
                return ValidationUtil.isPassword(value);
            }
        }
    }
}
