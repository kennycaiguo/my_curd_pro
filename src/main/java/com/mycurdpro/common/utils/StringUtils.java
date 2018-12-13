package com.mycurdpro.common.utils;


import com.google.common.base.Strings;
import com.jfinal.kit.AesKit;
import com.jfinal.kit.PropKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具
 */
public class StringUtils {

    private final  static Logger LOG = LoggerFactory.getLogger(StringUtils.class);

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



    /**
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexString(byte[] bytes) {
        char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }
        return new String(buf);
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }


    private final static String AES_KEY = PropKit.use("config.properties").get("aesKey");

    /**
     * 字符串先aes 加密为 byte[],再转 hex String
     *
     * @return
     */
    public static String encryptAesHex(String str) {
        return bytesToHexString(AesKit.encrypt(str, AES_KEY));
    }

    /**
     * hex 字符串先转 byte[], 再aes 解密
     *
     * @param str
     * @return
     */
    public static String decryptAesHex(String str) {
        String resultStr = null;
        try {
            resultStr = AesKit.decryptToStr(hexStringToBytes(str), AES_KEY);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return resultStr;
    }

}
