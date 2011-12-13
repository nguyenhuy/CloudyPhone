package com.cloudyphone.android.model.sms;

import java.util.TreeSet;

public class SmsThread {
	private long threadId;
	private String phoneNumber;
	private TreeSet<ParseSmsMessage> messages;

	public SmsThread(long threadId, String phoneNumber,
			TreeSet<ParseSmsMessage> messages) {
		this.threadId = threadId;
		this.phoneNumber = phoneNumber;
		this.messages = messages;
	}

	public TreeSet<ParseSmsMessage> getMessages() {
		return messages;
	}

	public ParseSmsThread toParseSmsThread() {
		return new ParseSmsThread(threadId, phoneNumber, messages);
	}
}
