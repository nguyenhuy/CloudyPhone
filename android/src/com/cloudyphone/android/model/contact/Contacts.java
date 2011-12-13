package com.cloudyphone.android.model.contact;

import java.util.Collection;

import org.json.JSONArray;

import com.parse.ParseObject;

public class Contacts extends ParseObject {
	private static final String CONTACTS = "contacts";

	public Contacts(Collection<Contact> contacts) {
		super(Contacts.class.getSimpleName());

		put(CONTACTS, new JSONArray(contacts));
	}
}
