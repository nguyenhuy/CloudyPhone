package com.cloudyphone.android.controllers.sync;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Looper;

import com.cloudyphone.android.model.contact.ParseContacts;
import com.cloudyphone.android.model.infor.ParsePhoneInfor;
import com.cloudyphone.android.model.sms.ParseSmsThreads;

/* 
 * When this thread is run, it will sync with pc, it should be check network connection
 * before go here, a "working icon" should be display, this thread will send a message to
 * STOP the icon when it finish
 */
public class SyncThread extends Thread {
	private ContentResolver cr;

	public SyncThread(Context context) {
		this.cr = context.getContentResolver();
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();

		// Find, delete and sync sms threads
		new UpdateThread(ParseSmsThreads.class.getSimpleName(),
				new SyncSmsThreadsCommand(cr)).start();

		// Find, delete and sync contacts
		new UpdateThread(ParseContacts.class.getSimpleName(),
				new SyncContactsCommand(cr)).start();

		// Find, delete and sync phone infor
		new UpdateThread(ParsePhoneInfor.class.getSimpleName(),
				new SyncPhoneInforCommand()).start();
	}
}
