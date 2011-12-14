package com.cloudyphone.android.model.sms;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONSmsMessage extends JSONObject implements
		Comparable<JSONSmsMessage> {

	private static final String MESSAGE_ID = "messageId", DATE = "date",
			TYPE = "type", BODY = "body";

	public JSONSmsMessage(long messageId, long date, String body, long type) {

		try {
			put(MESSAGE_ID, messageId);
			put(DATE, date);
			put(BODY, body);
			put(TYPE, type);
		} catch (JSONException e) {
		}
	}

	private long getDate() {
		try {
			return getLong(DATE);
		} catch (JSONException e) {
		}

		return 0;
	}

	@Override
	public int compareTo(JSONSmsMessage arg0) {
		long date = getDate(), anotherDate = arg0.getDate();

		if (date == anotherDate) {
			return 0;
		} else if (date > anotherDate) {
			return 1;
		}
		return -1;
	}
}
