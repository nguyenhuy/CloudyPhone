package com.cloudyphone.android.controller.listeners;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.model.InputValidator;

public class SignupFocusChangedListener implements OnFocusChangeListener {
	private Context context;
	private Button signupBtn;
	private WeakReference<EditText> emailReference, passwordReference,
			repeatPasswordReference;

	public SignupFocusChangedListener(Context context, EditText emailEditText,
			EditText passwordEditText, EditText repeatPaswordEditText,
			Button signupBtn) {
		this.context = context;
		this.emailReference = new WeakReference<EditText>(emailEditText);
		this.passwordReference = new WeakReference<EditText>(passwordEditText);
		this.repeatPasswordReference = new WeakReference<EditText>(
				repeatPaswordEditText);
		this.signupBtn = signupBtn;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			// user is typing
			return;
		}

		EditText email = emailReference.get(), password = passwordReference
				.get(), repeatPassword = repeatPasswordReference.get();
		String passwordString = password.getText().toString();
		String passwordRepeatString = repeatPassword.getText().toString();

		boolean validEmail = InputValidator.validateEmail(context, email
				.getText().toString());
		boolean validPassword = InputValidator.validatePassword(context,
				passwordString);
		boolean validRepeat = passwordString.equals(passwordRepeatString);

		if (v == email && !validEmail) {
			email.setError(context.getString(R.string.email_error));
			signupBtn.setEnabled(false);
		} else if (v == password && !validPassword) {
			password.setError(context.getString(R.string.password_error));
			signupBtn.setEnabled(false);
		} else if (v == repeatPassword && !validRepeat) {
			repeatPassword.setError(context
					.getString(R.string.password_repeat_error));
			signupBtn.setEnabled(false);
		} else {
			// everything is ok
			signupBtn.setEnabled(true);
		}
	}
}
