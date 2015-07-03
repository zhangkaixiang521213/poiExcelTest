package test;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/** *//**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class RSAUtils {

    /** *//**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    
    /** *//**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /** *//**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     * 
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * 
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
    	BASE64Decoder decoder = new BASE64Decoder(); 
    	byte[] keyBytes = decoder.decodeBuffer(privateKey); 
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        BASE64Encoder encode = new BASE64Encoder();
        return encode.encode(signature.sign());
    }
    
    public static void main(String args[]) throws Exception{
    	//使用模和指数生成RSA私钥
    	String priExp = "1C73A0D2E655E651DABAD52704C65C403FFA2D441D3CCEEFD6A9896176DAC5CD88A153A8A42571F9BBFB4841EA25A3D72FF4468E4E293AD660FD48E7C169B65FB61FF2E0D039D081361113953BC5A146359CD52DE547F818C9214F2CED362DE9D0790551D2224F9B69FA6194B21B84AB3A43F82B228F25F7085782BA2F27CA41";
    	String pubModel = "A5B38B9483257014C57B2F90D826C9D8EBEC023C6F212664A2766F5032825E996EB222941840BF70E79CE89E358ACB5A8FA0C8411470F3806ABBA2A8E6421AEDBCD6E21B748CA840AB139A16B34274E7E93D6B9374678C7CC68342D71E8173ACCB118D6933CC0952F52CFCD5FEC17EF3E6CB59EEC5550DB8C43106254550003D";
    	RSAPrivateKeySpec priSpec = new RSAPrivateKeySpec(new BigInteger(pubModel,16), 
    			new BigInteger(priExp,16));
    	KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    	PrivateKey privateK = keyFactory.generatePrivate(priSpec);
    	
    	String str = "merchantId=152143030798880&merchantUserId=0001111111117329733&mobile=18222140933";
    	byte[] signData = sign(str.getBytes(), privateK);
//    	byte[] signData = sign(str.getBytes(), KEY_ALGORITHM);
//    	byte[] signData = sign(str.getBytes("UTF-8"), privateK);
    	for (int i = 0; i < signData.length; i++) {
			System.out.println(signData[i]);
		}
//    	System.out.println("signData = " + new String(signData,"UTF-8"));
    	System.out.println("signData = " + new String(signData));
    	
//    	String ok = sign(str.getBytes(), KEY_ALGORITHM);
//    	System.out.println("ok = " + ok);
    }
    
//    public static final boolean verify(byte[] paramArrayOfByte1,
//			PublicKey paramPublicKey, byte[] paramArrayOfByte2) {
//		boolean bool = false;
//		try {
//			Signature localSignature = Signature.getInstance(SIGNATURE_ALGORITHM);
//			localSignature.initVerify(paramPublicKey);
//			localSignature.update(paramArrayOfByte1);
//			bool = localSignature.verify(paramArrayOfByte2);
//		} catch (Exception localException) {
//		}
//		return bool;
//	}

    public static final byte[] sign(byte[] paramArrayOfByte,
			PrivateKey paramPrivateKey) {
		byte[] arrayOfByte = null;
		try {
			Signature localSignature = Signature.getInstance(SIGNATURE_ALGORITHM);
			localSignature.initSign(paramPrivateKey);
			localSignature.update(paramArrayOfByte);
			arrayOfByte = localSignature.sign();
		} catch (Exception localException) {
		}
		return arrayOfByte;
	}
    
    

}
