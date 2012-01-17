package com.cloudyphone.android.controller.callbacks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cloudyphone.android.controller.activities.WelcomeActivity;
import com.parse.ParseException;
import com.parse.RequestPasswordResetCallback;

public class MyResetPasswordCallback extends RequestPasswordResetCallback {
	private Context context;
	private ProgressDialog progressDialog;

	public MyResetPasswordCallback(Context context,
			ProgressDialog progressDialog) {
		this.context = context;
		this.progressDialog = progressDialog;
	}

	@Override
	public void done(ParseException e) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}

		if (e == null) {
			// TODO: Show toast here
			Intent intent = new Intent(context, WelcomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			context.startActivity(intent);
		} else {
			Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
