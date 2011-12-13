package com.cloudyphone.android.model.sms;

import com.parse.ParseObject;

public class SmsMessage extends ParseObject implements Comparable<SmsMessage> {

	private static final String MESSAGE_ID = "messageId",
			THREAD_ID = "threadId", DATE = "date", TYPE = "type",
			PHONE_NUMBER = "phoneNumber", BODY = "body";

	public SmsMessage(long messageId, long threadId, String phoneNumber,
			long date, String body, long type) {
		super(SmsMessage.class.getSimpleName());

		put(MESSAGE_ID, messageId);
		put(THREAD_ID, threadId);
		put(PHONE_NUMBER, phoneNumber);
		put(DATE, date);
		put(BODY, body);
		put(TYPE, type);
	}

	public long getDate() {
		return getLong(DATE);
	}

	@Override
	public int compareTo(SmsMessage another) {
		long date = getDate(), anotherDate = another.getDate();
		if (date == anotherDate) {
			return 0;
		} else if (date > anotherDate) {
			return 1;
		}

		return -1;
	}
}
