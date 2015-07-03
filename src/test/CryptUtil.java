package test;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptUtil {

	public static byte[]  decryptBASE64(String deStr) throws IOException{
		BASE64Decoder decoder = new BASE64Decoder(); 
		byte[] keyBytes = decoder.decodeBuffer(deStr); 
		return keyBytes;
	}
	
	public static String encryptBASE64(byte[] keyBytes){
		BASE64Encoder encoder = new BASE64Encoder(); 
		return encoder.encodeBuffer(keyBytes);
	}
}
