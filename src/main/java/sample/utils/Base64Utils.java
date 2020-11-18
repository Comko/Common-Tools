package sample.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author zhangpenghao
 * @ClassName : Base64Utils
 * @description: base64
 * @date 2020-11-16 14:13
 */
public class Base64Utils {

    /**
     * BASE64解密
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     */
    public static String encryptBASE64(String str) throws Exception {
        byte[] key = str.getBytes();
        return (new BASE64Encoder()).encodeBuffer(key);
    }

}
