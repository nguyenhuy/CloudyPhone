package com.cloudyphone.android.model.sms;

import java.util.TreeMap;
import java.util.TreeSet;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class SmsManager {

	private static final Uri SMS_CONTENT_URI = Uri.parse("content://sms/");

	public SmsThreads getSmsThreads(ContentResolver cr) {

		TreeMap<Long, SmsThread> smsThreads = new TreeMap<Long, SmsThread>();

		Cursor cursor = cr.query(SMS_CONTENT_URI, new String[] { "_id",
				"thread_id", "address", "date", "body", "type" }, null, null,
				null);

		if (cursor != null && cursor.getCount() > 0) {
			try {
				while (cursor.moveToNext()) {
					long threadId = cursor.getLong(1);
					String phoneNumber = cursor.getString(2);

					if (!smsThreads.containsKey(threadId)) {
						/* thread does not exist, create one */
						smsThreads.put(threadId, new SmsThread(threadId,
								phoneNumber, new TreeSet<SmsMessage>()));
					}

					/* get the thread with that ID */
					SmsThread t = smsThreads.get(threadId);

					if (t != null) {
						TreeSet<SmsMessage> messages = t.getMessages();
						long messageId = cursor.getLong(0);
						long date = cursor.getLong(3);
						String body = cursor.getString(4);
						long type = cursor.getLong(5);

						messages.add(new SmsMessage(messageId, threadId,
								phoneNumber, date, body, type));
					}
				}
			} finally {
				cursor.close();
			}
		}

		return new SmsThreads(smsThreads.values());
	}
}
