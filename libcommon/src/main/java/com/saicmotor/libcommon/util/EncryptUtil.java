package com.saicmotor.libcommon.util;

import java.security.MessageDigest;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <pre>
 *     author  : HuangYuanyang
 *     time    : 2018/10/12
 *     desc    :
 *     version : 1.0
 * </pre>
 */

public class EncryptUtil {

    public static final String DEFAULT_CODING = "utf-8";

    /**
     * md5 加密
     * @param content 需加密内容
     * @param byteNumber 加密后的字节数
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String md5Encrypt(String content, int byteNumber) throws Exception{

        byte[] inputMD5 = content.getBytes(DEFAULT_CODING);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(inputMD5);
        if(byteNumber == 16){
            return parseByte2HexStr(thedigest).substring(8,24);
        }
        else {
            return parseByte2HexStr(thedigest);
        }
    }

    /**
     * HmacSHA256 加密
     * @param content 需加密内容
     * @param pIv 向量值
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String HmacSHA256Encrypt(String content, String pIv) throws Exception{

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(pIv.getBytes(DEFAULT_CODING), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] thedigestHmac = sha256_HMAC.doFinal(content.getBytes(DEFAULT_CODING));

        return parseByte2HexStr(thedigestHmac);
    }

    /**
     * AES256(CBC) 加密
     * @param content 需加密内容
     * @param mKey 密钥
     * @param pIv 向量值
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String aes256Encrypt(String content, String mKey, String pIv) throws Exception {

        byte[] iv = pIv.getBytes(DEFAULT_CODING);
        byte[] input = content.getBytes(DEFAULT_CODING);

        SecretKeySpec key = new SecretKeySpec(mKey.getBytes(DEFAULT_CODING), "AES");

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        return parseByte2HexStr(cipherText);
    }

    /**
     * AES256(CBC) 解密
     * @param encrypted 已加密内容
     * @param mKey 密钥
     * @param pIv 向量值
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String aes256Decrypt(String encrypted, String mKey, String pIv)  {

        try{
            byte[] iv = pIv.getBytes(DEFAULT_CODING);
            SecretKeySpec key = new SecretKeySpec(mKey.getBytes(DEFAULT_CODING), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] clearbyte = cipher.doFinal(toByte(encrypted));

            return new String(clearbyte);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @param length 随机字符串字节数
     * @return 指定字节数的随机字符串
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 字符串转字节数组
     * @author lmiky
     * @date 2014-2-25
     * @param hexString
     * @return
     */
    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }

    /**
     * @param buf 字符数组
     * @return 16进制字符串
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
