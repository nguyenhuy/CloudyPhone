package com.cloudyphone.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controllers.callbacks.MySignUpCallback;
import com.cloudyphone.android.controllers.listeners.SignupClickListener;

public class SignupActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		setFocus();

		setListeners();
	}

	private void setFocus() {
		findViewById(R.id.signup_btn).requestFocus();
	}

	private void setListeners() {
		EditText username = (EditText) findViewById(R.id.username_et);
		EditText password = (EditText) findViewById(R.id.password_et);
		EditText email = (EditText) findViewById(R.id.email_et);
		Button signupBtn = (Button) findViewById(R.id.signup_btn);

		signupBtn.setOnClickListener(new SignupClickListener(username,
				password, email, new MySignUpCallback(this)));
	}
}
