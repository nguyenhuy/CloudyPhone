package com.cloudyphone.android.model.sms;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;

import com.parse.ParseObject;

public class ParseSmsThreads extends ParseObject {

	private static final String THREADS = "threads";

	public ParseSmsThreads(Collection<SmsThread> smsThreads) {
		super(ParseSmsThreads.class.getSimpleName());

		// Convert all Sms threads to parse sms threads
		Collection<ParseSmsThread> threads = new ArrayList<ParseSmsThread>();
		for (SmsThread smsThread : smsThreads) {
			threads.add(smsThread.toParseSmsThread());
		}

		put(THREADS, new JSONArray(threads));
	}
}
