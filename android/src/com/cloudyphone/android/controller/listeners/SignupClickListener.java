package com.cloudyphone.android.controller.listeners;

import java.lang.ref.WeakReference;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.callbacks.MySignUpCallback;
import com.cloudyphone.android.model.InputValidator;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupClickListener implements OnClickListener {
	private Context context;
	private WeakReference<EditText> emailReference, passwordReference,
			repeatPasswordReference;

	public SignupClickListener(Context context, EditText email,
			EditText password, EditText repeatPassword) {
		this.context = context;
		this.emailReference = new WeakReference<EditText>(email);
		this.passwordReference = new WeakReference<EditText>(password);
		this.repeatPasswordReference = new WeakReference<EditText>(
				repeatPassword);
	}

	@Override
	public void onClick(View v) {
		EditText email = emailReference.get();
		EditText password = passwordReference.get();
		EditText repeatPassword = repeatPasswordReference.get();

		String emailString = email.getText().toString();
		String passwordString = password.getText().toString();
		String passwordRepeatString = repeatPassword.getText().toString();

		boolean validEmail = InputValidator.validateEmail(emailString);
		boolean validPassword = InputValidator.validatePassword(passwordString);
		boolean validRepeat = passwordString.equals(passwordRepeatString);

		if (!validEmail) {
			email.setError(context.getString(R.string.email_error));
		}

		if (!validPassword) {
			password.setError(context.getString(R.string.password_error));
		}

		if (!validRepeat) {
			repeatPassword.setError(context
					.getString(R.string.password_repeat_error));
		}

		if (validEmail && validPassword && validRepeat) {
			// everything is ok
			ProgressDialog progressDialog = ProgressDialog.show(context, "",
					context.getString(R.string.signing));
			progressDialog.setCancelable(true);

			ParseUser user = new ParseUser();
			user.setUsername(email.getText().toString());
			user.setPassword(password.getText().toString());

			SignUpCallback signUpCallback = new MySignUpCallback(context,
					progressDialog);

			user.signUpInBackground(signUpCallback);
		}
	}
}
