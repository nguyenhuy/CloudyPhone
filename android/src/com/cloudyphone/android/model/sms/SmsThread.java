package com.cloudyphone.android.model.sms;

import java.util.TreeSet;

import org.json.JSONArray;

import com.parse.ParseObject;

public class SmsThread extends ParseObject {
	private static final String THREAD_ID = "threadId",
			PHONE_NUMBER = "phoneNumber", MESSAGES = "messages";

	private TreeSet<SmsMessage> messages;

	public SmsThread(long threadId, String phoneNumber,
			TreeSet<SmsMessage> messages) {
		super(SmsThread.class.getSimpleName());

		put(THREAD_ID, threadId);
		put(PHONE_NUMBER, phoneNumber);

		this.messages = messages;
	}

	public TreeSet<SmsMessage> getMessages() {
		return messages;
	}

	@Override
	public void saveInBackground() {
		// since messages are stored in memory at tree set, should put to
		// JSONArray before call super
		put(MESSAGES, new JSONArray(messages));

		super.saveInBackground();
	}
}
