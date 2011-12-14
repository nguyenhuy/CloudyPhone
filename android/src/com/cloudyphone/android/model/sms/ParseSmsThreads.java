package com.cloudyphone.android.model.sms;

import java.util.Collection;

import org.json.JSONArray;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class ParseSmsThreads extends ParseObject {

	private static final String THREADS = "threads";

	private Collection<JSONSmsThread> smsThreads;

	public ParseSmsThreads(Collection<JSONSmsThread> smsThreads) {
		super(ParseSmsThreads.class.getSimpleName());
		this.smsThreads = smsThreads;
	}

	@Override
	public void save() throws ParseException {
		saveSmsThreads();
		super.save();
	}

	@Override
	public void saveInBackground(SaveCallback callback) {
		saveSmsThreads();
		super.saveInBackground(callback);
	}

	private void saveSmsThreads() {
		put(THREADS, new JSONArray(smsThreads));
	}
}
