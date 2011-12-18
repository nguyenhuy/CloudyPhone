package com.cloudyphone.android.controller.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.cloudyphone.android.controller.activities.SignupActivity;

public class StartSignupClickListener implements OnClickListener {
	private Context context;

	public StartSignupClickListener(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(context, SignupActivity.class);
		context.startActivity(i);
	}
}
