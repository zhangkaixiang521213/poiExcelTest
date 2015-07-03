package test;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * RSA �����㷨1978��ͳ����ˣ����ǵ�һ�������������ݼ���Ҳ����������ǩ�����㷨��<br>
 * RSAͬʱ������Կ�ף���Կ��˽Կ��ͬʱ֧������ǩ����<br>
 * ����ǩ�����������ڣ��Դ�����������ݽ���У�顣ȷ�������ڴ�������в����޸ġ�
 * <ul>
 * ���̷�����
 * <li>�׷�������Կ�Զ�������Կ�������ҷ�����˽Կ������</li>
 * <li>�׷�ʹ��˽Կ�������ݣ�Ȼ����˽Կ�Լ��ܺ������ǩ�������͸��ҷ�ǩ���Լ����ܺ�����ݣ��ҷ�ʹ�ù�Կ��ǩ������֤�����������Ƿ���Ч�������Чʹ�ù�Կ�����ݽ��ܡ�</li>
 * <li>�ҷ�ʹ�ù�Կ�������ݣ���׷����;������ܺ�����ݣ��׷���ü������ݣ�ͨ��˽Կ���ܡ� </li>
 * <ul>
 * 
 * @author Ice_Liu
 * 
 */
public class RSACryptUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
//    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
//    private static final String PUBLIC_KEY = "RSAPublicKey";
//    private static final String PRIVATE_KEY = "RSAPrivateKey";
    
    private static final String PRIVATE_KEY = "1C73A0D2E655E651DABAD52704C65C403FFA2D441D3CCEEFD6A9896176DAC5CD88A153A8A42571F9BBFB4841EA25A3D72FF4468E4E293AD660FD48E7C169B65FB61FF2E0D039D081361113953BC5A146359CD52DE547F818C9214F2CED362DE9D0790551D2224F9B69FA6194B21B84AB3A43F82B228F25F7085782BA2F27CA41";
    private static final String PUBLIC_KEY = "A5B38B9483257014C57B2F90D826C9D8EBEC023C6F212664A2766F5032825E996EB222941840BF70E79CE89E358ACB5A8FA0C8411470F3806ABBA2A8E6421AEDBCD6E21B748CA840AB139A16B34274E7E93D6B9374678C7CC68342D71E8173ACCB118D6933CC0952F52CFCD5FEC17EF3E6CB59EEC5550DB8C43106254550003D";

//    private static final String PUBLIC_KEY = "RSAPublicKey";
//    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * ��˽Կ����Ϣ��������ǩ��
     * 
     * @param data
     *            ��������
     * @param privateKey
     *            ˽Կ
     * 
     * @return
     * @throws Exception
*/
    public static String sign(byte[] data, String privateKey) throws Exception {
        // ������base64�����˽Կ
        byte[] keyBytes = CryptUtil.decryptBASE64(privateKey);
        // ����PKCS8EncodedKeySpec����
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM ָ���ļ����㷨
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // ȡ˽Կ�׶���
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // ��˽Կ����Ϣ��������ǩ��
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return CryptUtil.encryptBASE64(signature.sign());
    }

    /**
     * У������ǩ��
     * 
     * @param data
     *            ��������
     * @param publicKey
     *            ��Կ
     * @param sign
     *            ����ǩ��
     * 
     * @return У��ɹ�����true ʧ�ܷ���false
     * @throws Exception
     * 
*/
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        // ������base64����Ĺ�Կ
        byte[] keyBytes = CryptUtil.decryptBASE64(publicKey);
        // ����X509EncodedKeySpec����
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM ָ���ļ����㷨
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // ȡ��Կ�׶���
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // ��֤ǩ���Ƿ�����
        return signature.verify(CryptUtil.decryptBASE64(sign));
    }

    /**
     * ����<br>
     * ��˽Կ����
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        // ����Կ����
        byte[] keyBytes = CryptUtil.decryptBASE64(key);
        // ȡ��˽Կ
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // �����ݽ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * ����<br>
     * ��˽Կ����
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        // ����Կ����
        byte[] keyBytes = CryptUtil.decryptBASE64(key);
        // ȡ�ù�Կ
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // �����ݽ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * ����<br>
     * �ù�Կ����
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        // �Թ�Կ����
        byte[] keyBytes = CryptUtil.decryptBASE64(key);
        // ȡ�ù�Կ
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * ����<br>
     * ��˽Կ����
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        // ����Կ����
        byte[] keyBytes = CryptUtil.decryptBASE64(key);
        // ȡ��˽Կ
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * ȡ��˽Կ
     * 
     * @param keyMap
     * @return
     * @throws Exception
*/
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return CryptUtil.encryptBASE64(key.getEncoded());
    }

    /**
     * ȡ�ù�Կ
     * 
     * @param keyMap
     * @return
     * @throws Exception
*/
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return CryptUtil.encryptBASE64(key.getEncoded());
    }

    /**
     * ��ʼ����Կ
     * 
     * @return
     * @throws Exception
*/
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // ��Կ
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // ˽Կ
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * @param args
*/
    public static void main(String[] args) {
        try {
//            PBECryptUtil.main(args);
            System.out.println("****************************************");
            System.out.println("=====RSA���������=====");
            // ��ʼ����Կ,˽Կ
            Map<String, Object> keyMap = initKey();
            String publicKey = RSACryptUtil.getPublicKey(keyMap);
            String privateKey = RSACryptUtil.getPrivateKey(keyMap);
            System.out.println("��Կ: \n\r" + publicKey);
            System.out.println("˽Կ�� \n\r" + privateKey);
            System.out.println("��Կ���ܡ���˽Կ����");
            String inputStr = "merchantId=152143030798880&merchantUserId=0001111111117329733&mobile=18222140933";
//            String inputStr = "�����ŵĸ���";
            byte[] data = inputStr.getBytes("UTF-8");
            // ��Կ����
            byte[] encodedData = RSACryptUtil.encryptByPublicKey(data, publicKey);
            System.out.println("��Կ���ܺ�:" + new BigInteger(encodedData).toString(32));
            // ˽Կ����
            byte[] decodedData = RSACryptUtil.decryptByPrivateKey(encodedData, privateKey);
            String outputStr = new String(decodedData, "UTF-8");
            System.out.println("����ǰ: " + inputStr);
            System.out.println("���ܺ�: " + outputStr);

            System.out.println("˽Կ���ܡ�����Կ����");
            // ˽Կ����
            encodedData = RSACryptUtil.encryptByPrivateKey(data, privateKey);
            System.out.println("˽Կ���ܺ�:" + new BigInteger(encodedData).toString(32));
            // ��Կ����
            decodedData = RSACryptUtil.decryptByPublicKey(encodedData, publicKey);
            outputStr = new String(decodedData, "UTF-8");
            System.out.println("����ǰ: " + inputStr);
            System.out.println("���ܺ�: " + outputStr);

            System.out.println("˽Կǩ��������Կ��֤ǩ��");
            // ʹ��˽Կ����ǩ��
            String sign = RSACryptUtil.sign(encodedData, privateKey);
            System.out.println("ǩ��:" + sign);
            // ʹ�ù�����֤ǩ��
            boolean status = RSACryptUtil.verify(encodedData, publicKey, sign);
            System.err.println("��֤ǩ�����:" + status);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
