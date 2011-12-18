package com.cloudyphone.android.controller.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupClickListener implements OnClickListener {
	private EditText username, password, email;
	private SignUpCallback signUpCallback;

	public SignupClickListener(EditText username, EditText password,
			EditText email, SignUpCallback signUpCallback) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.signUpCallback = signUpCallback;
	}

	@Override
	public void onClick(View v) {
		ParseUser user = new ParseUser();
		user.setUsername(username.getText().toString());
		user.setPassword(password.getText().toString());
		user.setEmail(email.getText().toString());

		user.signUpInBackground(signUpCallback);
	}
}
