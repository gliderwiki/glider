package org.gliderwiki.framework.util;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.FactoryBean;

import com.google.common.base.Preconditions;

public class SimpleAesStringCipherManagerFactory implements FactoryBean<SimpleAesStringCipherManager> {

	private String base64EncodedKey;

	private byte[] key;

	private SimpleAesStringCipherManager cipherManager;

	@Override
	public SimpleAesStringCipherManager getObject() throws Exception {
		Preconditions.checkArgument(key != null && key.length > 0, "올바른 base64EncodedKey를 지정해야만 합니다.");

		return cipherManager;
	}

	@Override
	public Class<?> getObjectType() {
		return SimpleAesStringCipherManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setBase64EncodedKey(String base64EncodedKey) {
		this.base64EncodedKey = base64EncodedKey;
		decodeKey();
	}

	private void decodeKey() {
		key = Base64.decodeBase64(base64EncodedKey);
	}

	@PostConstruct
	public void createInstance() {
		cipherManager = new SimpleAesStringCipherManager(key);
	}
}
