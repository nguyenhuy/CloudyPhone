package com.cloudyphone.android.controller.receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MakeCallReceiver extends PushReceiver {

	@Override
	public void execute(Context context, JSONObject data) {
		try {
			String number = data.getString("number");

			Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ number));
			callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(callIntent);
		} catch (JSONException e) {
			// Wrong message, ignore this message
		}
	}
}
