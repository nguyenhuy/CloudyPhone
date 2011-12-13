package com.cloudyphone.android.activities;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controllers.SyncThread;
import com.parse.Parse;
import com.parse.PushService;

public class CloudyPhoneActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Init Parse
		Parse.initialize(this, "j6ojFCdGbegeJGCM4CGkeFmb9Z96OE1bK4AYioO1",
				"WjTB6ovHnZdG8cMhRZ8BKt87Rxc5GOutIk6Ua9kw");

		// Subcribe push notification
		PushService.subscribe(this, "test", CloudyPhoneActivity.class);

		Set<String> setOfAllSubscriptions = PushService.getSubscriptions(this);
		for (String s : setOfAllSubscriptions) {
			Log.v("CloudyPhone", s);
		}

		testParse();
	}

	private void testParse() {
		new SyncThread(this).start();
	}
}