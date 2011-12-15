package com.cloudyphone.android.controllers.sync;

import com.cloudyphone.android.model.infor.ParsePhoneInfor;
import com.parse.ParseException;

public class SyncPhoneInforCommand implements Command {
	private boolean isFinished = false;
	
	@Override
	public void execute() {
		try {
			new ParsePhoneInfor().save();
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
