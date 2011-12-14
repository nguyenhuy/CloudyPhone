package com.cloudyphone.android.controllers.sync;

import android.content.ContentResolver;

import com.cloudyphone.android.model.sms.ParseSmsThreads;
import com.cloudyphone.android.model.sms.SmsManager;
import com.parse.ParseException;

public class SyncSmsThreadsCommand implements Command {
	private ContentResolver cr;

	public SyncSmsThreadsCommand(ContentResolver cr) {
		this.cr = cr;
	}

	@Override
	public void execute() {
		ParseSmsThreads smsThreads = new SmsManager().getSmsThreads(cr);

		// Save contacts and messages in Parse
		try {
			smsThreads.save();
		} catch (ParseException e) {
		}

		// TODO may notify server
	}
}
