package com.cloudyphone.android.controller.sync;

import com.cloudyphone.android.model.infor.ParsePhoneInfor;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SyncPhoneInforCommand implements Command {
	private boolean isFinished = false;

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

		ParsePhoneInfor infor = new ParsePhoneInfor();
		// set the sms threads to be accessible by the current user only
		infor.setACL(new ParseACL(parseUser));

		try {
			infor.save();
		} catch (ParseException e) {
		}

		isFinished = true;
		// TODO may notify server
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}
}
