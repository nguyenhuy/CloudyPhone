package com.cloudyphone.android.controller.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.listeners.SignupClickListener;
import com.cloudyphone.android.controller.listeners.SignupFocusChangedListener;

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
		EditText email = (EditText) findViewById(R.id.email_et);
		EditText password = (EditText) findViewById(R.id.password_et);
		EditText passwordRepeat = (EditText) findViewById(R.id.password_repeat_set);
		Button signupBtn = (Button) findViewById(R.id.signup_btn);

		// text change listener
		OnFocusChangeListener focusChangeListener = new SignupFocusChangedListener(
				this, email, password, passwordRepeat, signupBtn);
		email.setOnFocusChangeListener(focusChangeListener);
		password.setOnFocusChangeListener(focusChangeListener);
		passwordRepeat.setOnFocusChangeListener(focusChangeListener);

		// sign up btn click listener
		signupBtn.setOnClickListener(new SignupClickListener(this, email,
				password));
	}
}
