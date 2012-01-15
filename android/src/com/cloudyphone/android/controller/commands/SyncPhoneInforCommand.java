package com.cloudyphone.android.controller.commands;

import com.cloudyphone.android.model.infor.ParsePhoneInfor;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SyncPhoneInforCommand implements Command {

	@Override
	public void execute() {
		ParseUser parseUser = ParseUser.getCurrentUser();

		// Get the current logged in user
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

		// TODO may notify server
	}
}
