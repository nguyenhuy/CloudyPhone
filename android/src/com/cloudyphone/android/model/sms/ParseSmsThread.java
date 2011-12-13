package com.cloudyphone.android.model.sms;

import java.util.TreeSet;

import org.json.JSONArray;

import com.parse.ParseObject;

public class ParseSmsThread extends ParseObject {
	private static final String THREAD_ID = "threadId",
			PHONE_NUMBER = "phoneNumber", MESSAGES = "messages";

	public ParseSmsThread(long threadId, String phoneNumber,
			TreeSet<ParseSmsMessage> messages) {
		super(ParseSmsThread.class.getSimpleName());

		put(THREAD_ID, threadId);
		put(PHONE_NUMBER, phoneNumber);
		put(MESSAGES, new JSONArray(messages));
	}
}
