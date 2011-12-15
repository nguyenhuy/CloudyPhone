package com.cloudyphone.android.model.sms;

import java.util.TreeMap;

import org.json.JSONArray;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class SmsManager {

	private static final Uri SMS_CONTENT_URI = Uri.parse("content://sms/");

	public ParseSmsThreads getSmsThreads(ContentResolver cr) {

		TreeMap<Long, JSONSmsThread> threads = new TreeMap<Long, JSONSmsThread>();

		Cursor cursor = cr.query(SMS_CONTENT_URI, new String[] { "_id",
				"thread_id", "address", "date", "body", "type" }, null, null,
				null);

		long threadId, messageId, date, type;
		String phoneNumber, body;
		JSONSmsThread t;
		JSONArray messages;

		while (cursor.moveToNext()) {
			threadId = cursor.getLong(1);

			if (!threads.containsKey(threadId)) {
				phoneNumber = cursor.getString(2);

				/* thread does not exist, create one */
				threads.put(threadId, new JSONSmsThread(threadId, phoneNumber,
						new JSONArray()));
			}

			/* get the thread with that ID and messages in that thread */
			t = threads.get(threadId);
			messages = t.getMessages();

			// get information
			messageId = cursor.getLong(0);
			date = cursor.getLong(3);
			body = cursor.getString(4);
			type = cursor.getLong(5);

			// put the new message to messages list of the thread
			messages.put(new JSONSmsMessage(messageId, date, body, type));
		}

		cursor.close();

		return new ParseSmsThreads(new JSONArray(threads.values()));
	}
}
