package com.cloudyphone.android.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cloudyphone.android.Constants;
import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.push.PushManager;
import com.parse.Parse;
import com.parse.ParseUser;

public class CloudyPhoneActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Init Parse
		Parse.initialize(this, Constants.PARSE_APP_ID,
				Constants.PARSE_CLIENT_KEY);

		// TODO do like this as main workflow
		// checkUserLogin();

		// TODO test
		setContentView(R.layout.main);
		subscribePush();
	}

	private void checkUserLogin() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
			// Show login activity
			showLogin();
		} else {
			// Logged in
			setContentView(R.layout.main);

			subscribePush();
		}
	}

	private void subscribePush() {
		// Subscribe push notification
		try {
			new PushManager().subscribe(this);
		} catch (Exception e) {
			showLogin();
		}
	}

	private void showLogin() {
		// User did not log in
		// show the login activity
		startActivity(new Intent(this, LoginActivity.class));
		finish();
	}
}