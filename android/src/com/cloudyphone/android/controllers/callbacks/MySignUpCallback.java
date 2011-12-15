package com.cloudyphone.android.controllers.callbacks;

import android.content.Context;
import android.content.Intent;

import com.cloudyphone.android.activities.CloudyPhoneActivity;
import com.parse.ParseException;
import com.parse.SignUpCallback;

public class MySignUpCallback extends SignUpCallback {
	private Context context;

	public MySignUpCallback(Context context) {
		this.context = context;
	}

	@Override
	public void done(ParseException e) {
		Intent intent = new Intent(context, CloudyPhoneActivity.class);
		context.startActivity(intent);
	}
}
