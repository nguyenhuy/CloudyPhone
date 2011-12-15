package com.cloudyphone.android.controllers.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseUser;

public class LoginClickListener implements OnClickListener {
	private EditText username, password;
	private LogInCallback logInCallback;

	public LoginClickListener(EditText username, EditText password,
			LogInCallback loginCallback) {
		this.username = username;
		this.password = password;
		this.logInCallback = loginCallback;
	}

	@Override
	public void onClick(View v) {
		ParseUser.logInInBackground(username.getText().toString(), password
				.getText().toString(), logInCallback);
	}
}
