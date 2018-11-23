package com.github.commons.utils.validation.phone;

import com.github.commons.utils.validation.ValidationUtil;
import tk.mybatis.mapper.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidationImpl implements ConstraintValidator<PhoneValidation,String> {

    private boolean required = false;

    @Override
    public void initialize(PhoneValidation phoneValidation) {
        required = phoneValidation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return ValidationUtil.isPhone(value);
        }else{
            if(StringUtil.isEmpty(value)){
                return true;
            }else{
                return ValidationUtil.isPhone(value);
            }
        }
    }

    private boolean isPhone(String str,String regulation) {
        Pattern pattern = Pattern.compile(regulation);
        return pattern.matcher(str).matches();
    }

}
