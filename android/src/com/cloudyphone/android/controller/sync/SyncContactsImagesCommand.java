package com.cloudyphone.android.controller.sync;

import java.util.Collection;

import android.content.ContentResolver;

import com.cloudyphone.android.model.contact.ContactsManager;
import com.cloudyphone.android.model.contact.ParseContactImg;
import com.cloudyphone.android.model.contact.ParseContacts;
import com.cloudyphone.android.utils.Logger;
import com.parse.ParseException;

public class SyncContactsImagesCommand implements Command {
	private ContentResolver cr;
	private ParseContacts contacts;
	private boolean isFinished = false;

	public SyncContactsImagesCommand(ContentResolver cr, ParseContacts contacts) {
		this.cr = cr;
		this.contacts = contacts;
	}

	@Override
	public void execute() {
		Collection<ParseContactImg> imgs = new ContactsManager()
				.getAllContactImages(cr, contacts.getContacts());

		// Save all images
		for (ParseContactImg img : imgs) {
			try {
				img.save();
				Logger.print(this, img.getUrl());
			} catch (ParseException e) {
			}
		}

		isFinished = true;

		// TODO may store file list in Parse
		// TODO may nofity server when all contact images are ok
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}
}
