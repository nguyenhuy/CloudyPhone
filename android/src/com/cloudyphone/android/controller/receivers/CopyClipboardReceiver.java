package com.cloudyphone.android.controller.receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.ClipboardManager;
import android.text.TextUtils;

public class CopyClipboardReceiver extends PushReceiver {

	@Override
	public void execute(Context ctx, JSONObject data) {
		try {
			String string = data.getString("message");

			if (TextUtils.isEmpty(string)) {
				// No message, return
				return;
			}

			// Get clipboard manager and set the text to it
			((ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE))
					.setText(string);
		} catch (JSONException e) {
		}
	}
}
