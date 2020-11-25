package sample.utils;

/**
 * @author zhangpenghao
 * @ClassName : AESUtils
 * @description: aes
 * @date 2020-11-20 16:27
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    public static String encrypt(String sSrc, String sKey) {
        if (sKey != null && sKey.length() == 16) {
            try {
                byte[] raw = sKey.getBytes("utf-8");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(1, skeySpec);
                byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
                return parseByte2HexStr(encrypted);
            } catch (Exception var6) {
                return "aes encrypt error";
            }
        } else {
            return "The length of the key should be 16";
        }
    }

    public static String decrypt(String sSrc, String sKey) {
        if (sKey != null && sKey.length() == 16) {
            try {
                byte[] raw = sKey.getBytes("utf-8");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(2, skeySpec);
                byte[] original = cipher.doFinal(parseHexStr2Byte(sSrc));
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception var7) {
                return "aes decrypt error";
            }
        } else {
            return "The length of the key should be 16";
        }
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for(int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }
}
