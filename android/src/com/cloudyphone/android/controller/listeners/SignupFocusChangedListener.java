package com.cloudyphone.android.controller.listeners;

import android.content.Context;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.model.InputValidator;

public class SignupFocusChangedListener implements OnFocusChangeListener {
	private Context context;
	private EditText email, password, repeatPassword;
	private Button signupBtn;

	public SignupFocusChangedListener(Context context, EditText email,
			EditText password, EditText repeatPasword, Button signupBtn) {
		this.context = context;
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPasword;
		this.signupBtn = signupBtn;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			// user is typing
			return;
		}

		String passwordString = password.getText().toString();
		String passwordRepeatString = repeatPassword.getText().toString();

		boolean validEmail = InputValidator.validateEmail(context, email
				.getText().toString());
		boolean validPassword = InputValidator.validatePassword(context,
				passwordString);
		boolean validRepeat = passwordString.equals(passwordRepeatString);

		if (!validEmail && v == email) {
			email.setError(context.getString(R.string.email_error));
			signupBtn.setEnabled(false);
			return;
		}

		if (!validPassword && v == password) {
			password.setError(context.getString(R.string.password_error));
			signupBtn.setEnabled(false);
			return;
		}

		if (!validRepeat && v == repeatPassword) {
			repeatPassword.setError(context
					.getString(R.string.password_repeat_error));
			signupBtn.setEnabled(false);
			return;
		}

		// everything is ok
		signupBtn.setEnabled(true);
	}
}
