package com.cloudyphone.android.controller.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.listeners.SignupClickListener;

public class SignupActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.signup);

		setListeners();
	}

	private void setListeners() {
		EditText email = (EditText) findViewById(R.id.email_et);
		EditText password = (EditText) findViewById(R.id.password_et);
		EditText passwordRepeat = (EditText) findViewById(R.id.password_repeat_set);
		Button signupBtn = (Button) findViewById(R.id.signup_btn);

		// sign up btn click listener
		signupBtn.setOnClickListener(new SignupClickListener(this, email,
				password, passwordRepeat));
	}
}
