package com.cloudyphone.android.controller.sync;

import android.content.ContentResolver;

import com.cloudyphone.android.model.sms.ParseSmsThreads;
import com.cloudyphone.android.model.sms.SmsManager;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SyncSmsThreadsCommand implements Command {
	private ContentResolver cr;

	public SyncSmsThreadsCommand(ContentResolver cr) {
		this.cr = cr;
	}

	@Override
	public void execute() {
		// Get the current logged in user
		ParseUser parseUser = ParseUser.getCurrentUser();
		if (parseUser == null) {
			// User did not log in
			// Since the sms threads need to be accessible only from the logged
			// in user
			// This thread should stop now
			return;
		}

		// Get the sms threads
		ParseSmsThreads smsThreads = SmsManager.getSmsThreads(cr);
		// set the sms threads to be accessible by the current user only
		smsThreads.setACL(new ParseACL(parseUser));

		// Save contacts and messages in Parse
		try {
			smsThreads.save();
		} catch (ParseException e) {
		}

		// TODO may notify server about the result
	}
}
