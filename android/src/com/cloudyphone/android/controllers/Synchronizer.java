package com.cloudyphone.android.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import com.cloudyphone.android.model.contact.Contact;
import com.cloudyphone.android.model.contact.Contacts;
import com.cloudyphone.android.model.contact.ContactsManager;
import com.cloudyphone.android.model.sms.SmsManager;
import com.cloudyphone.android.model.sms.SmsThread;
import com.cloudyphone.android.model.sms.SmsThreads;

/* 
 * When this thread is run, it will sync with pc, it should be check network connection
 * before go here, a "working icon" should be display, this thread will send a message to
 * STOP the icon when it finish
 */
public class Synchronizer extends Thread {
	private Context context;
	private ContentResolver cr;
	private Contacts contacts;
	private SmsThreads smsThreads;

	public Synchronizer(Context context) {
		this.context = context;
		this.cr = context.getContentResolver();
	}

	@Override
	public void run() {
		super.run();

		syncAll();

		// TODO may notify server
	}

	private void syncAll() {
		createObjects();

		/* Send all contacts photos */
		short numOfContacts = 0;

		for (Contact contact : contacts.getContacts()) {
			try {
				sendPhoto(contact.getID());

				// Log.v(ProgramConstants.LOG_TAG, "HH: " +
				// contact.getName());
				// Limit the number of ID to get photos
				if (isLite) {
					++numOfContacts;
					if (numOfContacts >= VersionManager.PHOTOS_NUM) {
						break;
					}
				}
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

		// Save contacts and messages in Parse
		contacts.saveInBackground();
		smsThreads.saveInBackground();

		InforManager inforManager = InforManager.getInstance();
		sender.send(inforManager.getPhoneInforMessage());
		sender.send(inforManager.getBatteryMessage());
	}

	private void createObjects() {
		// get all the contacts
		contacts = new ContactsManager().getAllContacts(cr);
		smsThreads = new SmsManager().getSmsThreads(cr);
	}

	/*
	 * private void createFiles() { context.startService(new Intent(context,
	 * ContactsXmlCreator.class)); context.startService(new Intent(context,
	 * SmsXmlCreator.class)); }
	 */

	private void sendPhoto(long id) throws FileNotFoundException, IOException {

		Uri auri = getPhotoUri(id);
		InputStream is = cr.openInputStream(auri);
		int fileLength = is.available();

		if (fileLength > 0) {
			BinaryMessage message = new BinaryMessage(
					ProgramConstants.REQUEST_SEND_CONTACT_PHOTO
							+ ProgramConstants.STRING_DELIMS + id + ".jpg", is,
					fileLength);
			sender.send(message);
		}

		/**
		 * OLD version: send photos in sdcard that was created by PhotoCreator
		 * 
		 * 
		 * sender.send(new BinaryMessage(
		 * ProgramConstants.REQUEST_SEND_CONTACT_PHOTO +
		 * ProgramConstants.STRING_DELIMS + id + ".jpg",
		 * ProgramConstants.CONTACT_PHOTO_DIRECTORY + "/" + id + ".jpg"));
		 */
	}

	private Uri getPhotoUri(long contactId) {
		Uri contactUri = ContentUris.withAppendedId(Contacts.CONTENT_URI,
				contactId);
		Uri photoUri = Uri.withAppendedPath(contactUri,
				Contacts.Photo.CONTENT_DIRECTORY);
		return photoUri;
	}

}
