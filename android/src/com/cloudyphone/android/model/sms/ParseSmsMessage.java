package com.cloudyphone.android.model.sms;

import com.parse.ParseObject;

public class ParseSmsMessage extends ParseObject implements
		Comparable<ParseSmsMessage> {

	private static final String MESSAGE_ID = "messageId",
			THREAD_ID = "threadId", DATE = "date", TYPE = "type",
			PHONE_NUMBER = "phoneNumber", BODY = "body";

	public ParseSmsMessage(long messageId, long threadId, String phoneNumber,
			long date, String body, long type) {
		super(ParseSmsMessage.class.getSimpleName());

		put(MESSAGE_ID, messageId);
		put(THREAD_ID, threadId);
		put(PHONE_NUMBER, phoneNumber);
		put(DATE, date);
		put(BODY, body);
		put(TYPE, type);
	}

	private long getDate() {
		return getLong(DATE);
	}

	@Override
	public int compareTo(ParseSmsMessage arg0) {
		long date = getDate(), anotherDate = arg0.getDate();

		if (date == anotherDate) {
			return 0;
		} else if (date > anotherDate) {
			return 1;
		}
		return -1;
	}
}
