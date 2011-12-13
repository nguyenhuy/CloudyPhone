package com.cloudyphone.android.model.sms;

import java.util.Collection;

import org.json.JSONArray;

import com.parse.ParseObject;

public class SmsThreads extends ParseObject {

	private static final String THREADS = "threads";

	public SmsThreads(Collection<SmsThread> threads) {
		super(SmsThreads.class.getSimpleName());

		put(THREADS, new JSONArray(threads));
	}
}
