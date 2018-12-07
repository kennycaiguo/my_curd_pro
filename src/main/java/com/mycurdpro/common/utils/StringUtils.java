package com.mycurdpro.common.utils;


import com.google.common.base.Strings;

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
        return Strings.isNullOrEmpty(str);
    }

    /**
     * 字符串 不是 null 且 不是 空格
     * @param str 需要验证的字符串
     * @return true 是，false 否
     */
    public static  boolean notEmpty(String str){
        return  !isEmpty(str);
    }



    /**
     * 驼峰转下划线
     * @param camelCaseStr 驼峰格式字符串
     * @return 下划线格式字符串
     */
    public static String toUnderline(String camelCaseStr) {
        if (camelCaseStr == null || "".equals(camelCaseStr.trim())) {
            return "";
        }
        int len = camelCaseStr.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = camelCaseStr.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
