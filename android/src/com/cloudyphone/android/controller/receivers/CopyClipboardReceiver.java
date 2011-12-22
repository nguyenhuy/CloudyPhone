package com.cloudyphone.android.controller.receivers;

import org.json.JSONObject;

import com.androidpcsuite.android.LaunchBrowserService;

import android.content.Context;
import android.content.Intent;
import android.text.ClipboardManager;
import android.text.TextUtils;

public class CopyClipboardReceiver extends PushReceiver {

	@Override
	public void execute(Context ctx, JSONObject data) {
		String string = data.getString("message");
		if (TextUtils.isEmpty(string)) {
			// No message, return
			return;
		}

		// Get clipboard manager
		ClipboardManager clipboard = (ClipboardManager) ctx
				.getSystemService(Context.CLIPBOARD_SERVICE);

		// set to clipboard
		clipboard.setText(string);
	}
}
