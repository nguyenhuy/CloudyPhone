package com.cloudyphone.android.controllers.sync;

import com.cloudyphone.android.model.infor.ParsePhoneInfor;
import com.parse.ParseException;

public class SyncPhoneInforCommand implements Command {

	@Override
	public void execute() {
		try {
			new ParsePhoneInfor().save();
		} catch (ParseException e) {
		}

		// TODO may notify server
	}
}
