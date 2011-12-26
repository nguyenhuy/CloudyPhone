package com.cloudyphone.android.controller.receivers;

import org.json.JSONObject;

import android.content.Context;

import com.cloudyphone.android.controller.sync.SyncThread;

public class SyncReceiver extends PushReceiver {
	@Override
	public void execute(Context context, JSONObject data) {
		try {
			new SyncThread(context).start();
		} catch (Exception e) {
			// TODO notify user here, not logged in yet
		}
	}
}
