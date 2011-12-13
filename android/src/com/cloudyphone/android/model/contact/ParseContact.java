package com.cloudyphone.android.model.contact;

import java.util.Collection;

import org.json.JSONArray;

import com.parse.ParseObject;

public class ParseContact extends ParseObject {

	private static final String ID = "id", NAME = "name",
			PHONE_NUMBERS = "phoneNumbers", IMG = "img";

	public ParseContact(long id, String name, Collection<String> phoneNumbers) {
		super(ParseContact.class.getSimpleName());
		put(ID, id);
		put(NAME, name);
		put(PHONE_NUMBERS, new JSONArray(phoneNumbers));
	}

	public long getId() {
		return getLong(ID);
	}

	public void putImg(ParseContactImg img) {
		put(IMG, img);
	}
}
