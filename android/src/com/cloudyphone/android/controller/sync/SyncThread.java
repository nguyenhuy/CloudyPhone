package com.cloudyphone.android.controller.sync;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Looper;

import com.parse.ParseUser;

/* 
 * When this thread is run, it will sync with pc, it should be check network connection
 * before go here, a "working icon" should be display, this thread will send a message to
 * STOP the icon when it finish
 */
public class SyncThread extends Thread {
	private ContentResolver cr;

	public SyncThread(Context context) throws Exception {
		if (ParseUser.getCurrentUser() == null) {
			// not login yet
			throw new Exception("Not logged in yet.");
		}

		this.cr = context.getContentResolver();
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();

		// Sync sms threads
		Command c1 = new SyncSmsThreadsCommand(cr);
		new UpdateThread(c1).start();

		// Sync contacts
		Command c2 = new SyncContactsCommand(cr);
		new UpdateThread(c2).start();

		// sync phone infor
		// Command c3 = new SyncPhoneInforCommand();
		// new UpdateThread(c3).start();
	}
}
