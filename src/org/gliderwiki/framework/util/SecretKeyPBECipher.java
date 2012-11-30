package org.gliderwiki.framework.util;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class SecretKeyPBECipher {
	private static PBEKeySpec keySpec;
	private static SecretKey key;
	private static SecretKeyFactory keyFactory;
	private static PBEParameterSpec parameterSpec;
	private static Cipher cipher;

	private static byte[] salt = { (byte) 0x24, (byte) 0x85, (byte) 0x62, (byte) 0x79, (byte) 0xFE, (byte) 0x10, (byte) 0xA6, (byte) 0xB2 };
	private static int iteration = 16;
	private static byte[] encryptedText;
	private static byte[] decryptedText;

	public static void initiate(String currentKey) throws Exception {
		System.out.println("currentKey : " + currentKey);
		keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		keySpec = new PBEKeySpec(currentKey.toCharArray());
		key = keyFactory.generateSecret(keySpec);
		parameterSpec = new PBEParameterSpec(salt, iteration);

		cipher = Cipher.getInstance("PBEWithMD5AndDES");
	}

	public static byte[] encrypt(String plainText) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
		encryptedText = cipher.doFinal(plainText.getBytes("euc-kr"));

		return encryptedText;
	}

	public static byte[] decrypt(byte[] encryptedText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
		decryptedText = cipher.doFinal(encryptedText);

		return decryptedText;
	}
	
	public static byte[] decrypt(String decrtyptText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
		decryptedText = cipher.doFinal(decrtyptText.getBytes("euc-kr"));
		return decryptedText;
	}

	
	public static void main(String[] args) {
		/*
		try {
			byte[] data;
			String genkey = stringBuffersChars(128);
			SecretKeyPBECipher.initiate("APvmoMKGaEHHaNfiHEleajPBmVZUlEOeRuRNZlXvHWWBpJNGwwpnpRaCIqHZMIvlpEvtoTEhEcjvOkCBvJeVqwvrFyvFKufxDtdTjvyWsCciSZfDqTSxoazMDfTIqsLm");
			data = SecretKeyPBECipher.encrypt("skagml");
			String password = Base64Coder.encodeString(data);
			System.out.println("1 : " + password);		
			
			data = SecretKeyPBECipher.decrypt(data);
			System.out.println("2 : " + new String(data, "euc-kr"));
			
			data = Base64Coder.decode("D6QIwc00+4s=");
			data = SecretKeyPBECipher.decrypt(data);
			System.out.println("3 : " + new String(data, "euc-kr"));
			
			//data = SecretKeyPBECipher.decrypt("7qXgar1JEeE3LjrWBBelwA==");
			//String password = Base64Coder.decodeString(new String(data, "euc-kr"));
			//System.out.println("2 : " + password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}


	public static String stringBuffersChars(int len) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"
				.split(",");

		for (int i = 0; i < len; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}

		System.out.println("buffer.toString() :" + buffer.toString());
		return buffer.toString();

	}
	
	/**
	 * 키와 암호화된 패스워드를 받아서 복호화한 후 리턴한다. 
	 * @param password
	 * @param key
	 * @return
	 */
	public static String getUserPassword(String password, String key) {
		String pwd = "";
		byte[] data;
		try {
			SecretKeyPBECipher.initiate(key);
			data = Base64Coder.decode(password);
			data = SecretKeyPBECipher.decrypt(data);
			
			pwd = new String(data, "euc-kr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pwd;
	}
	
	/**
	 * 키와 비밀번호 문자열을 받아 암호화 한다. 
	 * @param password
	 * @param key
	 * @return
	 */
	public static String setUserPassword(String password, String key) {
		String pwd = "";
		byte[] data;
		try {
			SecretKeyPBECipher.initiate(key);
			data = SecretKeyPBECipher.encrypt(password);
			pwd = Base64Coder.encodeString(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pwd;
	}
	
}
