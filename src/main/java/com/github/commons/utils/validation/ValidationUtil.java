package com.github.commons.utils.validation;

import com.github.commons.utils.exception.GlobalException;
import com.github.commons.utils.result.CodeMsg;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    /*手机号格式验证*/
    private static final Pattern mobile_pattern = Pattern.compile("^1[3456789][0-9]{9}$");
    /*密码正确格式为：以字母开头，长度在6-18之间，只能包含字符、数字和下划线。^[a-zA-Z]\w{5,17}$*/
    /*必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间*/
    private static final Pattern password_pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,17}$");
    /*非负整数*/
    private static final Pattern non_negative_positive_integer = Pattern.compile("^\\d+$");
    /*非零正整数*/
    private static final Pattern nonzero_positive_integer = Pattern.compile("^\\+?[1-9][0-9]*$");
    /*验证两位小数的正实数*/
    private static final Pattern two_fractional_part_positive_integer = Pattern.compile("^[0-9]+(.[0-9]{2})?$");
    /*验证有零到两位小数的正实数*/
    private static final Pattern zero_two_fractional_part_positive_integer = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
    /*验证0到100有两位小数的数*/
    private static final Pattern zero_two_fractional_part_positive = Pattern.compile("^(\\d|[1-9]\\d|100)(\\.\\d{1,2})?$");
    /*验证非负浮点数*/
    private static final Pattern non_negative_positive_float = Pattern.compile("^\\d+(\\.\\d+)?$");
    /*验证0到1之间两位小数*/
    private static final Pattern zero_one = Pattern.compile("^(1|0(\\.\\d{1,2})?)$");
    /*验证中文*/
    private static final Pattern china = Pattern.compile("^[\\u4e00-\\u9fa5]+$");
    /*验证由26个英文字母组成的字符串*/
    private static final Pattern english = Pattern.compile("^[A-Za-z]+$");

    public static boolean isPhone(String src) {
        if(StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

    public static boolean isPassword(String src) {
        if(StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = password_pattern.matcher(src);
        return m.matches();
    }

    public static boolean isNumber(Object obj,Integer type){
        if(obj instanceof String || obj instanceof Double || obj instanceof BigDecimal || obj instanceof BigInteger || obj instanceof Long || obj instanceof Integer) {
            String src = String.valueOf(obj);
            if (StringUtils.isEmpty(src)) {
                return false;
            }
            if (type != null) {
                switch (type) {
                    case 0:
                        return non_negative_positive_integer.matcher(src).matches();
                    case 1:
                        return nonzero_positive_integer.matcher(src).matches();
                    case 2:
                        return two_fractional_part_positive_integer.matcher(src).matches();
                    case 3:
                        return zero_two_fractional_part_positive_integer.matcher(src).matches();
                    case 4:
                        boolean result = zero_two_fractional_part_positive.matcher(src).matches();
                        if(result){
                            BigDecimal a = new BigDecimal("100");
                            BigDecimal b = new BigDecimal(src);
                            if(b.compareTo(a) == 1){
                                result = false;
                            }
                        }
                        return result;
                    case 5:
                        return non_negative_positive_float.matcher(src).matches();
                    case 6:
                        return zero_one.matcher(src).matches();
                    case 7:
                        return china.matcher(src).matches();
                    case 8:
                        return english.matcher(src).matches();
                    default:
                        return non_negative_positive_integer.matcher(src).matches();
                }
            } else {
                return non_negative_positive_integer.matcher(src).matches();
            }
        }else{
            throw GlobalException.error(CodeMsg.INFO(500,"数据类型错误"));
        }
    }


    public static Boolean isInt(BigDecimal number,BigDecimal num){
        if(number == null || num == null){
            return false;
        }
        BigDecimal[] bigDecimals = number.divideAndRemainder(num);
        if(bigDecimals[0].compareTo(new BigDecimal("0")) == 1){
            if(bigDecimals[1].compareTo(new BigDecimal("0")) == 0){
                return true;
            }
        }
        return false;
    }
}
