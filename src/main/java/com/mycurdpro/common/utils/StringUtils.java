package com.mycurdpro.common.utils;


/**
 * 字符串工具
 */
public class StringUtils {

    /**
     * 判断字符串是 null 或者  空格
     * @param str 需要验证的字符串
     * @return true 是，false 否
     */
    public  static  boolean isEmpty(String str){
        if (str == null ||  str.length()==0) {
            return true;
        }
        return false;
    }

    /**
     * 字符串 不是 null 且 不是 空格
     * @param str 需要验证的字符串
     * @return true 是，false 否
     */
    public static  boolean notEmpty(String str){
        return  !isEmpty(str);
    }
}
