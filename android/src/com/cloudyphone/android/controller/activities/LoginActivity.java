package com.cloudyphone.android.controller.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.listeners.LoginClickListener;
import com.cloudyphone.android.controller.listeners.StartSignupClickListener;

public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		setListeners();
	}

	private void setListeners() {
		EditText email = (EditText) findViewById(R.id.email_et);
		EditText password = (EditText) findViewById(R.id.password_et);
		Button loginBtn = (Button) findViewById(R.id.login_btn);
		Button signupBtn = (Button) findViewById(R.id.signup_btn);

		// login button click listener
		loginBtn.setOnClickListener(new LoginClickListener(this, email,
				password));
		// signup button click listener
		signupBtn.setOnClickListener(new StartSignupClickListener(this));
	}
}
