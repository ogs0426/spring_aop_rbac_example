package com.oh.sec.security;

import com.oh.sec.util.CryptoConverter;

import java.security.NoSuchAlgorithmException;

public final class SnPasswordEncoder { // implements PasswordEncoder{

	public static String encode(CharSequence dest) {

		String ALGORITHM = "SHA-256";
		String SALT = "";

		try {
			return CryptoConverter.genderHash(ALGORITHM, dest.toString(), SALT);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(encode(rawPassword));
	}
}
