package com.cloudyphone.android.controller.listeners;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.model.InputValidator;

public class LoginFocusChangeListener implements OnFocusChangeListener {
	private Context context;
	private WeakReference<EditText> emailReference, passwordReference;
	private Button loginBtn;

	public LoginFocusChangeListener(Context context, EditText email,
			EditText password, Button loginBtn) {
		this.context = context;
		this.emailReference = new WeakReference<EditText>(email);
		this.passwordReference = new WeakReference<EditText>(password);
		this.loginBtn = loginBtn;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			// user is typing
			return;
		}

		EditText email = emailReference.get(), password = passwordReference
				.get();
		boolean validEmail = InputValidator.validateEmail(context, email
				.getText().toString());
		boolean validPassword = InputValidator.validatePassword(context,
				password.getText().toString());

		if (v == email && !validEmail) {
			email.setError(context.getString(R.string.email_error));
			loginBtn.setEnabled(false);
		} else if (v == password && !validPassword) {
			password.setError(context.getString(R.string.password_error));
			loginBtn.setEnabled(false);
		} else {
			// everything is ok
			loginBtn.setEnabled(true);
		}
	}
}
