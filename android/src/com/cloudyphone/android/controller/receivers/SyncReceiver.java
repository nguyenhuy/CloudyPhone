package com.cloudyphone.android.controller.receivers;

import org.json.JSONObject;

import com.cloudyphone.android.controller.sync.SyncThread;

import android.content.Context;

public class SyncReceiver extends PushReceiver {

	@Override
	public void execute(Context context, JSONObject data) {
		new SyncThread(context).start();
	}
}
