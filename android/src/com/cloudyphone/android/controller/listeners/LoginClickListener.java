package com.cloudyphone.android.controller.listeners;

import java.lang.ref.WeakReference;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.callbacks.MyLoginCallback;
import com.cloudyphone.android.model.InputValidator;
import com.parse.ParseUser;

public class LoginClickListener implements OnClickListener {
	private Context context;
	private WeakReference<EditText> emailReference, passwordReference;

	public LoginClickListener(Context context, EditText email, EditText password) {
		this.context = context;
		this.emailReference = new WeakReference<EditText>(email);
		this.passwordReference = new WeakReference<EditText>(password);
	}

	@Override
	public void onClick(View v) {
		EditText email = emailReference.get();
		EditText password = passwordReference.get();

		String emailString = email.getText().toString();
		String passwordString = password.getText().toString();

		boolean validEmail = InputValidator.validateEmail(context, emailString);
		boolean validPassword = InputValidator.validatePassword(context,
				passwordString);

		if (!validEmail) {
			email.setError(context.getString(R.string.email_error));
		}

		if (!validPassword) {
			password.setError(context.getString(R.string.password_error));
		}

		if (validEmail && validPassword) {
			// everything is ok
			// Show Progress dialog
			ProgressDialog pd = ProgressDialog.show(context, "",
					context.getString(R.string.logining));
			pd.setCancelable(true);

			ParseUser.logInInBackground(emailString, passwordString,
					new MyLoginCallback(context, pd));
		}
	}
}
