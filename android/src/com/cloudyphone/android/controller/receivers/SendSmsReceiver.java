package com.cloudyphone.android.controller.receivers;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.SmsManager;
import android.text.TextUtils;

public class SendSmsReceiver extends PushReceiver {

	@Override
	public void execute(Context context, JSONObject data) {
		try {
			String number = data.getString("number");
			String message = data.getString("message");

			sendSms(context, number, message);
		} catch (JSONException e) {
			// Wrong message, ignore this message
		}
	}

	private void sendSms(Context ctx, String number, String message) {
		if (TextUtils.isEmpty(number) || TextUtils.isEmpty(message)) {
			return;
		}

		SmsManager sms = SmsManager.getDefault();
		ArrayList<String> parts = sms.divideMessage(message);
		sms.sendMultipartTextMessage(number, null, parts, null, null);
	}
}
