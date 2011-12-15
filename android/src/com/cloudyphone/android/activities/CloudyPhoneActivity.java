package com.cloudyphone.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cloudyphone.android.Constants;
import com.cloudyphone.android.R;
import com.cloudyphone.android.controllers.sync.SyncThread;
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

		// Check user login
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser == null) {
			// User did not log in
			// show the login activity
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		}

		setContentView(R.layout.main);
		// testSync();
	}

	private void testSync() {
		new SyncThread(this).start();
	}
}