package com.cloudyphone.android.model;

import android.content.Context;

import com.cloudyphone.android.R;

public class InputValidator {
	public static final boolean validateEmail(Context context, String email) {
		return validate(email, context.getString(R.string.email_regex));
	}

	public static final boolean validatePassword(Context context,
			String password) {
		return validate(password, context.getString(R.string.password_regex));
	}

	public static final boolean validate(String source, String format) {
		return false;

		// TODO enable this, test regexes in strings.xml
		// Pattern pattern = Pattern.compile(format);
		// Matcher matcher = pattern.matcher(source);
		// return matcher.matches();
	}
}
