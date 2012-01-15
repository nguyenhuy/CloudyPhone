package com.cloudyphone.android.controller.callbacks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cloudyphone.android.controller.activities.CloudyPhoneActivity;
import com.parse.ParseException;
import com.parse.SignUpCallback;

public class MySignUpCallback extends SignUpCallback {
	private Context context;
	private ProgressDialog progressDialog;

	public MySignUpCallback(Context context, ProgressDialog progressDialog) {
		this.context = context;
		this.progressDialog = progressDialog;
	}

	@Override
	public void done(ParseException e) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}

		if (e == null) {
			Intent intent = new Intent(context, CloudyPhoneActivity.class);
			context.startActivity(intent);
		} else {
			Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}
}
