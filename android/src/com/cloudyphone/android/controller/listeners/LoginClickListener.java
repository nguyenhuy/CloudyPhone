package com.cloudyphone.android.controller.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseUser;

public class LoginClickListener implements OnClickListener {
	private EditText email, password;
	private LogInCallback logInCallback;

	public LoginClickListener(EditText email, EditText password,
			LogInCallback loginCallback) {
		this.email = email;
		this.password = password;
		this.logInCallback = loginCallback;
	}

	@Override
	public void onClick(View v) {
		ParseUser.logInInBackground(email.getText().toString(), password
				.getText().toString(), logInCallback);
	}
}
