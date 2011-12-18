package com.cloudyphone.android.controller.callbacks;

import android.content.Context;
import android.content.Intent;

import com.cloudyphone.android.controller.activities.CloudyPhoneActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MyLoginCallback extends LogInCallback {
	private Context context;

	public MyLoginCallback(Context context) {
		this.context = context;
	}

	@Override
	public void done(ParseUser user, ParseException e) {
		if (e == null && user != null) {
			// Hooray! The user is logged in.
			Intent i = new Intent(context, CloudyPhoneActivity.class);
			context.startActivity(i);
		} else if (user == null) {
			// Sign up didn't succeed. The username or password was invalid.
		} else {
			// There was an error. Look at the ParseException to see what
			// happened.
		}
	}

}
