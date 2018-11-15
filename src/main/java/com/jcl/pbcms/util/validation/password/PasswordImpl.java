package com.jcl.pbcms.util.validation.password;

import com.jcl.pbcms.util.validation.ValidationUtil;
import tk.mybatis.mapper.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 密码格式是否符合
 */
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
