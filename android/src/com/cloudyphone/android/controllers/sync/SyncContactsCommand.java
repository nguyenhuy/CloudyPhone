package com.cloudyphone.android.controllers.sync;

import android.content.ContentResolver;

import com.cloudyphone.android.model.contact.ContactsManager;
import com.cloudyphone.android.model.contact.ParseContacts;
import com.parse.ParseException;

public class SyncContactsCommand implements Command {
	private ContentResolver cr;
	private boolean isFinished = false;

	public SyncContactsCommand(ContentResolver cr) {
		this.cr = cr;
	}

	@Override
	public void execute() {
		ParseContacts contacts = new ContactsManager().getAllContacts(cr);

		try {
			contacts.save();
		} catch (ParseException e) {
		}

		isFinished = true;

		// Save contacts images
		// TODO uncomment this to store images
		// new SyncContactsImagesCommand(cr, contacts).execute();

		// TODO may notify server
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}
}
