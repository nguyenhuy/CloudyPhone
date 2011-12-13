package com.cloudyphone.android.model.contact;

import java.util.Collection;

import org.json.JSONArray;

import com.parse.ParseObject;

public class ParseContacts extends ParseObject {
	private static final String CONTACTS = "contacts";

	public ParseContacts(Collection<ParseContact> contacts) {
		super(ParseContacts.class.getSimpleName());

		put(CONTACTS, new JSONArray(contacts));
	}
}
