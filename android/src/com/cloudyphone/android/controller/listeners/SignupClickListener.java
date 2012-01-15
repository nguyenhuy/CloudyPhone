package com.cloudyphone.android.controller.listeners;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.callbacks.MySignUpCallback;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupClickListener implements OnClickListener {
	private EditText password, email;
	private Context context;

	public SignupClickListener(Context context, EditText password,
			EditText email) {
		this.context = context;
		this.password = password;
		this.email = email;
	}

	@Override
	public void onClick(View v) {
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
