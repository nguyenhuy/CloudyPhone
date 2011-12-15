package com.cloudyphone.android.model.sms;

import org.json.JSONArray;

import com.parse.ParseObject;

public class ParseSmsThreads extends ParseObject {

	private static final String THREADS = "threads";

	public ParseSmsThreads(JSONArray smsThreads) {
		super(ParseSmsThreads.class.getSimpleName());
		put(THREADS, smsThreads);
	}
}
