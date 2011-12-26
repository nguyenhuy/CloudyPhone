package com.cloudyphone.android.model.contact;

import java.util.Collection;

import org.json.JSONArray;

import com.cloudyphone.android.model.MyParseObject;
import com.parse.ParseException;
import com.parse.SaveCallback;

public class ParseContacts extends MyParseObject {

	private static final String CONTACTS = "contacts";

	private Collection<JSONContact> contacts;

	public ParseContacts(Collection<JSONContact> contacts) {
		super(ParseContacts.class.getSimpleName());

		this.contacts = contacts;
	}

	public Collection<JSONContact> getContacts() {
		return contacts;
	}

	@Override
	public void save() throws ParseException {
		saveContacts();
		super.save();
	}

	@Override
	public void saveInBackground(SaveCallback callback) {
		saveContacts();
		super.saveInBackground(callback);
	}

	private void saveContacts() {
		put(CONTACTS, new JSONArray(contacts));
	}
}
