package com.cloudyphone.android.controllers.sync;

import com.cloudyphone.android.utils.Logger;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Looper;

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

		int tryTime = 30;
		long[] times = new long[tryTime];

		for (int i = 0; i < tryTime; ++i) {
			long time = System.currentTimeMillis();

			// Find, delete and sync sms threads
			Command c1 = new SyncSmsThreadsCommand(cr);
			new UpdateThread(c1).start();

			// Find, delete and sync contacts
			Command c2 = new SyncContactsCommand(cr);
			new UpdateThread(c2).start();

			// Find, delete and sync phone infor
			Command c3 = new SyncPhoneInforCommand();
			new UpdateThread(c3).start();

			while (!c1.isFinished() || !c2.isFinished() || !c3.isFinished()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}

			times[i] = System.currentTimeMillis() - time;
			Logger.print(this, "Benchmark: " + times[i]);
		}

		// TODO benchmark
		long average = 0;
		for (long l : times) {
			average += l;
		}
		average /= tryTime;

		Logger.print(this, "Benchmark avarage: " + average);
	}
}
