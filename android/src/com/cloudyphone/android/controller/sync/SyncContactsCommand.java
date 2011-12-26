package com.cloudyphone.android.controller.sync;

import android.content.ContentResolver;

import com.cloudyphone.android.model.contact.ContactsManager;
import com.cloudyphone.android.model.contact.ParseContacts;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SyncContactsCommand implements Command {
	private ContentResolver cr;

	public SyncContactsCommand(ContentResolver cr) {
		this.cr = cr;
	}

	@Override
	public void execute() {
		// Get the current logged in user
		ParseUser parseUser = ParseUser.getCurrentUser();
		if (parseUser == null) {
			// User did not log in
			// Since the sms threads need to be accessible only from the logged
			// in user
			// This thread should stop now
			return;
		}

		ParseContacts contacts = new ContactsManager().getAllContacts(cr);
		// set the sms threads to be accessible by the current user only
		contacts.setACL(new ParseACL(parseUser));

		try {
			contacts.save();
		} catch (ParseException e) {
		}

		// Save contacts images
		// TODO uncomment this to store images
		// new SyncContactsImagesCommand(cr, contacts).execute();

		// TODO may notify server
	}
}
