package com.cloudyphone.android.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Looper;

import com.cloudyphone.android.model.contact.Contacts;
import com.cloudyphone.android.model.contact.ContactsManager;
import com.cloudyphone.android.model.contact.ParseContact;
import com.cloudyphone.android.model.contact.ParseContactImg;
import com.cloudyphone.android.model.contact.ParseContacts;
import com.cloudyphone.android.model.infor.PhoneInfor;
import com.cloudyphone.android.model.sms.ParseSmsThreads;
import com.cloudyphone.android.model.sms.SmsManager;

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

		syncAll();

		// TODO may notify server
	}

	private void syncAll() {
		ParseSmsThreads smsThreads = new SmsManager().getSmsThreads(cr);
		ParseContacts contacts = createContacts();

		// Save contacts and messages in Parse
		contacts.saveInBackground();
		smsThreads.saveInBackground();

		// Save phone infor
		new PhoneInfor().saveInBackground();
	}

	private ParseContacts createContacts() {
		// get all the contacts
		Contacts contacts = new ContactsManager().getAllContacts(cr);

		/* Get all contacts photos */
		for (ParseContact contact : contacts.getContacts()) {
			try {
				contact.putImg(getContactImg(contact.getId()));
			} catch (NullPointerException ex) {
				// Can't find image, ignore it
				continue;
			} catch (OutOfMemoryError ex) {
				System.gc();
				continue;
			} catch (FileNotFoundException ex) {
				continue;
			} catch (IOException ex) {
				continue;
			} catch (IllegalArgumentException ex) {
				continue;
			}
		}

		return contacts.toParseContacts();
	}

	private ParseContactImg getContactImg(long id)
			throws FileNotFoundException, IOException {

		Uri auri = getPhotoUri(id);
		InputStream is = cr.openInputStream(auri);
		int fileLength = is.available();

		if (fileLength > 0) {
			byte[] data = ParseContactImg.convertFileToByte(is, fileLength);
			return new ParseContactImg(id, data);
		}

		return null;
	}

	private Uri getPhotoUri(long contactId) {
		Uri contactUri = ContentUris.withAppendedId(
				android.provider.ContactsContract.Contacts.CONTENT_URI,
				contactId);
		Uri photoUri = Uri
				.withAppendedPath(
						contactUri,
						android.provider.ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
		return photoUri;
	}

}
