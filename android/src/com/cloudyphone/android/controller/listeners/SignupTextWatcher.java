package com.cloudyphone.android.controller.listeners;

import android.content.Context;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.model.InputValidator;

public class SignupTextWatcher implements android.text.TextWatcher {
	private Context context;
	private EditText email, password, repeatPassword;
	private Button signupBtn;

	public SignupTextWatcher(Context context, EditText email,
			EditText password, EditText repeatPasword, Button signupBtn) {
		this.context = context;
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPasword;
		this.signupBtn = signupBtn;
	}

	@Override
	public void afterTextChanged(Editable s) {
		String emailString = email.getText().toString();
		String passwordString = password.getText().toString();
		String passwordRepeatString = repeatPassword.getText().toString();

		boolean validEmail = InputValidator.validateEmail(context, email
				.getText().toString());
		boolean validPassword = passwordString.equals(passwordRepeatString)
				&& InputValidator.validatePassword(context, passwordString);

		if (!validEmail) {
			email.setError(context.getString(R.string.email_error));
			signupBtn.setEnabled(false);
		}

		if (!validPassword) {
			password.setError(context.getString(R.string.password_error));
			signupBtn.setEnabled(false);
		}

		if (validEmail && validPassword) {
			signupBtn.setEnabled(true);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
