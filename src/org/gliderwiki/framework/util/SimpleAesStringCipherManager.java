package org.gliderwiki.framework.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.gliderwiki.framework.exception.GliderwikiException;

import com.google.common.base.Preconditions;

public class SimpleAesStringCipherManager {

	private static final String CIPHER_TRANSFORMATION = "AES/ECB/PKCS5Padding";

	private static final String CIPHER_PROVIDER = "SunJCE";

	private final byte[] key;

	private SecretKey secretKey;

	public SimpleAesStringCipherManager(byte[] key) {
		this.key = key;

		populateSecretKey();
	}

	private void populateSecretKey() {
		secretKey = new SecretKeySpec(key, "AES");
	}

	public byte[] getKey() {
		return key;
	}

	public String encrypt(Integer i) {
		return encrypt(i.toString());
	}

	public String encrypt(String plainText) {
		Preconditions.checkNotNull(plainText, "암호화할 값을 입력하세요.");

		byte[] cipherData = null;
		try {
			Cipher encCipher = Cipher.getInstance(CIPHER_TRANSFORMATION, CIPHER_PROVIDER);
			encCipher.init(Cipher.ENCRYPT_MODE, secretKey);

			cipherData = encCipher.doFinal(plainText.getBytes());
		} catch (Exception ex) {
			throw new GliderwikiException(ex.getMessage(), ex);
		}

		return Base64.encodeBase64URLSafeString(cipherData);
	}

	public String decrypt(String encryptedText) {
		Preconditions.checkNotNull(encryptedText, "복호화할 암호화된 값을 입력하세요");

		byte[] encypted = Base64.decodeBase64(encryptedText.getBytes());
		byte[] decrypted = null;

		try {
			Cipher decCipher = Cipher.getInstance(CIPHER_TRANSFORMATION, CIPHER_PROVIDER);
			decCipher.init(Cipher.DECRYPT_MODE, secretKey);

			decrypted = decCipher.doFinal(encypted);
		} catch (Exception ex) {
			throw new GliderwikiException(ex.getMessage(), ex);
		}
		return new String(decrypted);
	}
}