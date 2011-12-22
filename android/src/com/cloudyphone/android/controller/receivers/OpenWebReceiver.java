package com.cloudyphone.android.controller.receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

public class OpenWebReceiver extends PushReceiver {

	@Override
	public void execute(Context ctx, JSONObject data) {
		try {
			String url = data.getString("url");
			if (TextUtils.isEmpty(url)) {
				// No message, return
				return;
			}

			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setData(Uri.parse(url));
			ctx.startActivity(i);

		} catch (JSONException e) {
		}
	}
}
