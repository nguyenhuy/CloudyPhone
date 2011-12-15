package com.cloudyphone.android.activities;

import android.app.Activity;
import android.os.Bundle;

import com.cloudyphone.android.Constants;
import com.cloudyphone.android.R;
import com.cloudyphone.android.controllers.sync.SyncThread;
import com.parse.Parse;

public class CloudyPhoneActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Init Parse
		Parse.initialize(this, Constants.PARSE_APP_ID,
				Constants.PARSE_CLIENT_KEY);

		// testSynce();
	}

	private void testSync() {
		new SyncThread(this).start();
	}
}