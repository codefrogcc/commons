package com.github.commons.utils;import java.math.BigDecimal;import java.math.BigInteger;import java.math.MathContext;import java.math.RoundingMode;import java.text.DecimalFormat;import java.text.NumberFormat;import java.util.Random;import java.util.UUID;/** * Number工具类 */public abstract class NumberUtil {    //------------常量定义-------------//    /** 零 */    public static BigDecimal ZERO = BigDecimal.ZERO;    /** 一百 */    public static BigDecimal ONE_HUNDRED = new BigDecimal("100");    private static NumberFormat nf = new DecimalFormat("0000");    public static MathContext mc = new MathContext(16);     /**     * double 相加     * @param dd 可变参数     * @return double 和     */    public static double doubleAdd(double... dd) {        BigDecimal result = BigDecimal.ZERO;        for (double n : dd) {            result = result.add(new BigDecimal("" + n));        }        return result.doubleValue();    }    /**     * BigDecimal 相加     *     * @param dd 可变参数     * @return BigDecimal 和     */    public static BigDecimal bigDecimalAdd(BigDecimal... dd){        BigDecimal result = BigDecimal.ZERO;        for (BigDecimal n : dd) {            result = result.add(n);        }        return result;    }    /**     * double 相加 - 四舍五入     *     * @param dd 可变参数     * @return double 和     */    public static double doubleAddScaled(double... dd) {        BigDecimal result = BigDecimal.ZERO;        for (double n : dd) {            result = result.add(new BigDecimal("" + n));        }        return scale(result);    }    /**     * BigDecimal 相加 - 四舍五入     *     * @param dd 可变参数     * @return BigDecimal 和     */    public static BigDecimal bigDecimalAddScaled(BigDecimal... dd){        BigDecimal result = BigDecimal.ZERO;        for (BigDecimal n : dd) {            result = result.add(n);        }        return bigDecimalScale(result);    }    /**     * double 相减     *     * @param d1 减数     * @param d2 被减数     * @return double 差     */    public static double doubleSubtract(double d1, double d2) {        return new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)).doubleValue();    }    /**     * BigDecimal 相减     *     * @param b1 减数     * @param b2 被减数     * @return BigDecimal 差     */    public static BigDecimal bigDecimalSubtract(BigDecimal b1,BigDecimal b2){        return b1.subtract(b2);    }    /**     * double 相减 - 四舍五入     *     * @param d1 减数     * @param d2 被减数     * @return double 差     */    public static double doubleSubtractScaled(double d1, double d2) {        return scale(new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)));    }    /**     * BigDecimal 相减 - 四舍五入     *     * @param b1 减数     * @param b2 被减数     * @return BigDecimal 差     */    public static BigDecimal bigDecimalSubtractScaled(BigDecimal b1,BigDecimal b2){        return bigDecimalScale(b1.subtract(b2));    }    /**     * 四舍五入 - RoundingMode.HALF_UP     *     * @param decimal 四舍五入前的数字     * @return double 四舍五入后的数字     */    private static double scale(BigDecimal decimal) {        return decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();    }    private static BigDecimal bigDecimalScale(BigDecimal decimal){        return decimal.setScale(2, RoundingMode.HALF_UP);    }    /**     *     * 货币形式格式字符串     * (####,###0.00)     * @param format 格式     * @param value 货币     * @return String 转化后的格式     */    public static String formatNumberString(String format, String value) {        BigDecimal bd = new BigDecimal(value);        DecimalFormat df = new DecimalFormat(format);        return df.format(bd);    }    /**     * double 相除 取位 d1/d2 - 四舍五入     *     * @param d1 除数     * @param d2 被除数     * @param scale 保留的位数     * @return double 商     */    public static double doubleDivide(double d1, double d2, int scale) {        BigDecimal bd1 = new BigDecimal(d1);        BigDecimal bd2 = new BigDecimal(d2);        BigDecimal bd3 = bd1.divide(bd2, mc);        return bd3.setScale(scale, RoundingMode.HALF_UP).doubleValue();    }    /**     * BigDecimal 相除 取位 d1/d2 - 四舍五入     *     * @param d1 除数     * @param d2 被除数     * @param scale 保留的位数     * @return BigDecimal 商     */    public static BigDecimal bigDecimalDivide(BigDecimal d1,BigDecimal d2,int scale){        return d1.divide(d2, mc).setScale(scale, RoundingMode.HALF_UP);    }    /**     * double 相乘 取位 d1*d2 - 四舍五入 默认保留2位     *     * @param d1 乘数     * @param d2 被乘数     * @return double 积     */    public static double doubleMultiply(double d1, double d2) {        return doubleMultiply(d1, d2, 2);    }    /**     * BigDecimal 相乘 取位 d1*d2 - 四舍五入 默认保留2位     *     * @param d1 乘数     * @param d2 被乘数     * @return BigDecimal 积     */    public static BigDecimal bigDecimalMultiply(BigDecimal d1,BigDecimal d2){        return bigDecimalMultiply(d1,d2,2);    }    /**     * double 相乘 取位 d1*d2 - 四舍五入     *     * @param d1 乘数     * @param d2 被乘数     * @param scale 保留的位数     * @return double 积     */    public static double doubleMultiply(double d1, double d2, int scale) {        BigDecimal bd1 = new BigDecimal(Double.toString(d1));        BigDecimal bd2 = new BigDecimal(Double.toString(d2));        return bd1.multiply(bd2, mc).setScale(scale, RoundingMode.HALF_UP).doubleValue();    }    /**     * BigDecimal 相乘 取位 d1*d2 - 四舍五入     *     * @param d1 乘数     * @param d2 被乘数     * @param scale 保留的位数     * @return BigDecimal 积     */    public static BigDecimal bigDecimalMultiply(BigDecimal d1,BigDecimal d2,int scale){        return d1.multiply(d2,mc).setScale(scale, RoundingMode.HALF_UP);    }    /**     * double 相乘 取位 d1*d2 - 四舍五入     *     * @param scale 保留的位数     * @param dd 可变参数     * @return double 积     */    public static double doubleMultiplyScale(int scale, double... dd) {        BigDecimal result = BigDecimal.ONE;        for (double n : dd) {            result = result.multiply(new BigDecimal("" + n), mc);        }        return result.setScale(scale, RoundingMode.HALF_UP).doubleValue();    }    /**     * BigDecimal 相乘 取位 d1*d2 - 四舍五入     *     * @param scale 保留的位数     * @param dd 可变参数     * @return BigDecimal 积     */    public static BigDecimal bigDecimalMultiplyScale(int scale,BigDecimal... dd){        BigDecimal result = BigDecimal.ONE;        for (BigDecimal n : dd) {            result = result.multiply(n, mc);        }        return result.setScale(scale, RoundingMode.HALF_UP);    }    /**     * 将整数转化为四位数字     *     * @param number 整数     * @return String 转化的数字     */    public static String formatFourNumber(Integer number) {        return nf.format(number);    }    /**     * 获取带千分符并保留两位小数的金额     *     * @param amount 浮点数     * @return String 获取带千分符并保留两位小数的金额     */    public static String formatNumber(double amount) {        String result = NumberFormat.getCurrencyInstance().format(amount).substring(1);        return result;    }    public static BigDecimal format(BigDecimal number) {        BigDecimal setScale = new BigDecimal("0");        if(number != null) {            setScale = number.setScale(2, BigDecimal.ROUND_HALF_UP);        }        return setScale;    }    public static Double format(Double number) {        BigDecimal setScale = new BigDecimal(number);        if(number != null) {            setScale = setScale.setScale(2, BigDecimal.ROUND_HALF_UP);        }        return setScale.doubleValue();    }    public static String getOrderIdByUUId() {        int first = new Random(10).nextInt(8) + 1;        int hashCodeV = UUID.randomUUID().toString().hashCode();        if (hashCodeV < 0) {//有可能是负数            hashCodeV = -hashCodeV;        }        // 0 代表前面补充0        // 4 代表长度为4        // d 代表参数为正数型        //System.out.println("first:"+ first);        return first + String.format("%015d", hashCodeV);    }    /**     * 生成随机数方法     * @param places 需生成的几位随机数     * @return String 随机数     */    public static String createRandomNumber(Integer places){        Integer i = power1(10, places - 1);        Integer i1 = (int) ((Math.random() * 9 + 1) * i);        return String.valueOf(i1);    }    private static int power1(int i, int j) {        int y = 1;        String ab = Integer.toBinaryString(j);        for(int a = 0; a < ab.length();a ++)        {            int s = Integer.parseInt(String.valueOf(ab.charAt(a)));//char类型转化为int            y = y*y;            if(s == 1){                y = y*i;            }        }        return y;    }    /**     * 十进制转换成二进制 ()     * @param decimalSource 十进制的字符串     * @return 二进制的字符串     */    public static String decimalToBinary(String decimalSource) {        BigInteger bi = new BigInteger(decimalSource);	//转换成BigInteger类型        return bi.toString(2);	//参数2指定的是转化成X进制，默认10进制    }    /**     * 二进制转换成十进制     * @param binarySource 二进制的字符串     * @return Integer 十进制的字符串     */    public static Integer binaryToDecimal(String binarySource) {        BigInteger bi = new BigInteger(binarySource, 2);    //转换为BigInteger类型        return Integer.parseInt(bi.toString());        //转换成十进制    }    public static String getUUid(){        return UUID.randomUUID().toString().replace("-", "").toLowerCase();    }}