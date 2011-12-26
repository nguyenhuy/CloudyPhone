package com.cloudyphone.android.controller.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupClickListener implements OnClickListener {
	private EditText password, email;
	private SignUpCallback signUpCallback;

	public SignupClickListener(EditText password, EditText email,
			SignUpCallback signUpCallback) {
		this.password = password;
		this.email = email;
		this.signUpCallback = signUpCallback;
	}

	@Override
	public void onClick(View v) {
		ParseUser user = new ParseUser();
		user.setUsername(email.getText().toString());
		user.setPassword(password.getText().toString());

		user.signUpInBackground(signUpCallback);
	}
}
