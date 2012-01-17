package com.cloudyphone.android.controller.listeners;

import java.lang.ref.WeakReference;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.callbacks.MyResetPasswordCallback;
import com.cloudyphone.android.model.InputValidator;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ResetPasswordClickListener implements OnClickListener {
	private Context context;
	private WeakReference<EditText> emailReference;

	public ResetPasswordClickListener(Context context, EditText email) {
		this.context = context;
		this.emailReference = new WeakReference<EditText>(email);
	}

	@Override
	public void onClick(View v) {
		EditText email = emailReference.get();

		String emailString = email.getText().toString();

		boolean validEmail = InputValidator.validateEmail(emailString);

		if (!validEmail) {
			email.setError(context.getString(R.string.email_error));
		} else {
			// everything is ok
			ProgressDialog progressDialog = ProgressDialog.show(context, "",
					context.getString(R.string.resetting));
			progressDialog.setCancelable(true);

			RequestPasswordResetCallback callback = new MyResetPasswordCallback(
					context, progressDialog);

			ParseUser.requestPasswordResetInBackground(emailString, callback);
		}
	}
}
