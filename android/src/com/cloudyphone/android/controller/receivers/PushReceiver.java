package com.cloudyphone.android.controller.receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cloudyphone.android.utils.Logger;

public abstract class PushReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {

		Logger.print(this, "received message");

		// get the data object and call execute
		try {
			JSONObject data = new JSONObject(intent.getExtras().getString(
					"com.parse.Data"));

			execute(context, data);
		} catch (JSONException e) {
			// error in push message, ignore this
		}
	}

	public abstract void execute(Context context, JSONObject data);
}
