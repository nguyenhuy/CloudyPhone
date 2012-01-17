package com.cloudyphone.android.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cloudyphone.android.Constants;
import com.cloudyphone.android.R;
import com.parse.Parse;
import com.parse.ParseUser;

public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Init Parse
		Parse.initialize(this, Constants.PARSE_APP_ID,
				Constants.PARSE_CLIENT_KEY);
	}

	@Override
	protected void onResume() {
		super.onResume();

		checkUserLogin();
	}

	private void checkUserLogin() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
			// Show login view
			setContentView(R.layout.welcome);

			setListeners();
		} else {
			// Logged in, go to main page
			Intent i = new Intent(this, CloudyPhoneActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		}
	}

	private void setListeners() {
		Button loginBtn = (Button) findViewById(R.id.login_btn);
		Button signupBtn = (Button) findViewById(R.id.signup_btn);

		// login button click listener
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});

		// signup button click listener
		signupBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(WelcomeActivity.this, SignupActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
	}
}
