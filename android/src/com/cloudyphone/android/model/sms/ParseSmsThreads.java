package com.cloudyphone.android.model.sms;

import org.json.JSONArray;

import com.cloudyphone.android.model.MyParseObject;

public class ParseSmsThreads extends MyParseObject {

	private static final String THREADS = "threads";

	public ParseSmsThreads(JSONArray smsThreads) {
		super(ParseSmsThreads.class.getSimpleName());

		put(THREADS, smsThreads);
	}
}
