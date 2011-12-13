package com.cloudyphone.android.model.contact;

import java.util.Collection;

import org.json.JSONArray;

import com.parse.ParseObject;

public class Contact extends ParseObject {

	private static final String ID = "id", NAME = "name",
			PHONE_NUMBERS = "phoneNumbers";

	public Contact(long id, String name, Collection<String> phoneNumbers) {
		super(Contact.class.getSimpleName());
		put(ID, id);
		put(NAME, name);
		put(PHONE_NUMBERS, new JSONArray(phoneNumbers));
	}
}
