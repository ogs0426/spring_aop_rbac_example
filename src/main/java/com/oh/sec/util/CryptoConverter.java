package com.oh.sec.util;

import org.springframework.lang.Nullable;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class CryptoConverter {

	private static final Charset CC_DEFAULT = StandardCharsets.UTF_8;

	public static byte[] encryptCipher(String transformationRule, String keyAlgorithm, Boolean isENCRYPT, byte[] plain, byte[] key, @Nullable byte[] iv) throws Exception {
		Cipher cipher = Cipher.getInstance(transformationRule);

		int mode = Cipher.DECRYPT_MODE;

		if(isENCRYPT)
			mode = Cipher.ENCRYPT_MODE;

		if(iv != null)
			cipher.init(mode, new SecretKeySpec(key, keyAlgorithm), new IvParameterSpec(iv));
		else
			cipher.init(mode, new SecretKeySpec(key, keyAlgorithm));

		return cipher.doFinal(plain);
	}

	/*
	 * MD2, MD4, MD5, SHA-1, SHA-256, SHA-512
	 */
	public static String genderHash(String algorithm, String str, @Nullable String salt) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance(algorithm);

		md.update(str.getBytes(CC_DEFAULT));

		byte[] datas = md.digest();

		if (salt != null)
			datas = md.digest(salt.getBytes(CC_DEFAULT));

		StringBuilder signature = new StringBuilder();

		for (byte data : datas) {
			signature.append(Integer.toString((data & 0xff) + 0x100, 16).substring(1));
		}

		return signature.toString();

	}

	public static String toBase64(String str, Boolean isENCODE) {
		if (isENCODE)
			return new String(Base64.getEncoder().encode(str.getBytes()));
		else
			return new String(Base64.getDecoder().decode(str));
	}

	public static byte[] toBase64(byte[] bs, Boolean isENCODE) {
		if (isENCODE)
			return Base64.getEncoder().encode(bs);
		else
			return Base64.getDecoder().decode(bs);
	}
}
