package com.cloudyphone.android.controller.listeners;

import android.content.Context;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.model.InputValidator;

public class LoginTextWatcher implements android.text.TextWatcher {
	private Context context;
	private EditText email, password;
	private Button loginBtn;

	public LoginTextWatcher(Context context, EditText email, EditText password,
			Button loginBtn) {
		this.context = context;
		this.email = email;
		this.password = password;
		this.loginBtn = loginBtn;
	}

	@Override
	public void afterTextChanged(Editable s) {
		boolean validEmail = InputValidator.validateEmail(context, email
				.getText().toString());
		boolean validPassword = InputValidator.validatePassword(context,
				password.getText().toString());

		if (!validEmail) {
			email.setError(context.getString(R.string.email_error));
			loginBtn.setEnabled(false);
		}

		if (!validPassword) {
			password.setError(context.getString(R.string.password_error));
			loginBtn.setEnabled(false);
		}

		if (validEmail && validPassword) {
			loginBtn.setEnabled(true);
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
