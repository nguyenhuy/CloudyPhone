package com.cloudyphone.android.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.push.PushManager;
import com.parse.ParseUser;

public class CloudyPhoneActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		subscribePush();
	}

	private void subscribePush() {
		// Subscribe push notification
		try {
			PushManager.subscribe(this);
		} catch (Exception e) {
			showLogin();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// double check user login
		if (ParseUser.getCurrentUser() == null) {
			showLogin();
		}
	}

	private void showLogin() {
		// User did not log in
		// show the login activity
		Intent intent = new Intent(this, WelcomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	/*
	 * The menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_logout:
			ParseUser.logOut();
			showLogin();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}