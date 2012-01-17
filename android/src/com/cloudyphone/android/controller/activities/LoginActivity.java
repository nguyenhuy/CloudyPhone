package com.cloudyphone.android.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.listeners.LoginClickListener;

public class LoginActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Show login view
		setContentView(R.layout.login);

		setListeners();
	}

	private void setListeners() {
		EditText email = (EditText) findViewById(R.id.email_et);
		EditText password = (EditText) findViewById(R.id.password_et);
		Button loginBtn = (Button) findViewById(R.id.login_btn);
		Button resetBtn = (Button) findViewById(R.id.reset_password_btn);

		// login button click listener
		loginBtn.setOnClickListener(new LoginClickListener(this, email,
				password));

		// resets button click listener
		resetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(LoginActivity.this,
						ResetPasswordActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
	}
}
