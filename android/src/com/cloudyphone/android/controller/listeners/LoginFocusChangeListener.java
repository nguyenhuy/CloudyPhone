package com.cloudyphone.android.controller.listeners;

import android.content.Context;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.model.InputValidator;

public class LoginFocusChangeListener implements OnFocusChangeListener {
	private Context context;
	private EditText email, password;
	private Button loginBtn;

	public LoginFocusChangeListener(Context context, EditText email,
			EditText password, Button loginBtn) {
		this.context = context;
		this.email = email;
		this.password = password;
		this.loginBtn = loginBtn;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			// user is typing
			return;
		}

		boolean validEmail = InputValidator.validateEmail(context, email
				.getText().toString());
		boolean validPassword = InputValidator.validatePassword(context,
				password.getText().toString());

		if (!validEmail && v == email) {
			email.setError(context.getString(R.string.email_error));
			loginBtn.setEnabled(false);
			return;
		}

		if (!validPassword && v == password) {
			password.setError(context.getString(R.string.password_error));
			loginBtn.setEnabled(false);
			return;
		}

		// everything is ok
		loginBtn.setEnabled(true);
	}

}
