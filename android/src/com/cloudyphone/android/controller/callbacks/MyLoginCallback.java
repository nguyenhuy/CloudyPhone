package com.cloudyphone.android.controller.callbacks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.activities.CloudyPhoneActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MyLoginCallback extends LogInCallback {
	private Context context;
	private ProgressDialog progressDialog;

	public MyLoginCallback(Context context, ProgressDialog progressDialog) {
		this.context = context;
		this.progressDialog = progressDialog;
	}

	@Override
	public void done(ParseUser user, ParseException e) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}

		if (e == null && user != null) {
			// Hooray! The user is logged in.
			Intent i = new Intent(context, CloudyPhoneActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			context.startActivity(i);
		} else if (user == null) {
			// Sign up didn't succeed. The username or password was invalid.
			Toast.makeText(context,
					context.getString(R.string.invalid_email_password),
					Toast.LENGTH_LONG).show();
		} else {
			// There was an error. Look at the ParseException to see what
			// happened.
			Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
