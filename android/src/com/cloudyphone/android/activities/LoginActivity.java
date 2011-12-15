package com.cloudyphone.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controllers.callbacks.MyLoginCallback;
import com.cloudyphone.android.controllers.listeners.LoginClickListener;
import com.cloudyphone.android.controllers.listeners.StartSignupClickListener;

public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		setFocus();

		setListeners();
	}

	private void setFocus() {
		findViewById(R.id.signup_btn).requestFocus();
	}

	private void setListeners() {
		EditText username = (EditText) findViewById(R.id.username_et);
		EditText password = (EditText) findViewById(R.id.password_et);
		Button loginBtn = (Button) findViewById(R.id.login_btn);

		loginBtn.setOnClickListener(new LoginClickListener(username, password,
				new MyLoginCallback(this)));

		Button signupBtn = (Button) findViewById(R.id.signup_btn);
		signupBtn.setOnClickListener(new StartSignupClickListener(this));
	}
}
