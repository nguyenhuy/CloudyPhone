package com.cloudyphone.android.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
	/**
	 * Validate the email, with 1 condition: the email contains @
	 * 
	 * @param email
	 * @return
	 */
	public static final boolean validateEmail(String email) {
		return email.contains("@") && email.length() >= 5;
	}

	/**
	 * Validate the password, with 1 condition: at least 4 characters
	 * 
	 * @param context
	 * @param password
	 * @return
	 */
	public static final boolean validatePassword(String password) {
		return password.length() >= 4;
	}

	/**
	 * Validate a string using the format which is a regex
	 * 
	 * @param source
	 * @param format
	 * @return
	 */
	public static final boolean validate(String source, String format) {
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(source);
		return matcher.matches();
	}
}
