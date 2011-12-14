package com.cloudyphone.android.model.sms;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONSmsThread extends JSONObject {
	private static final String THREAD_ID = "threadId",
			PHONE_NUMBER = "phoneNumber", MESSAGES = "messages";

	public JSONSmsThread(long threadId, String phoneNumber, JSONArray messages) {

		try {
			put(THREAD_ID, threadId);
			put(PHONE_NUMBER, phoneNumber);
			put(MESSAGES, messages);
		} catch (JSONException e) {
		}
	}

	public JSONArray getMessages() {
		try {
			return getJSONArray(MESSAGES);
		} catch (JSONException e) {
		}

		return new JSONArray();
	}
}
