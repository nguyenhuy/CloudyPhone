package com.cloudyphone.android.model.contact;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONContact extends JSONObject {

	private static final String ID = "id", NAME = "name",
			PHONE_NUMBERS = "phoneNumbers";

	public JSONContact(long id, String name, Collection<String> phoneNumbers) {
		try {
			put(ID, id);
			put(NAME, name);
			put(PHONE_NUMBERS, new JSONArray(phoneNumbers));
		} catch (JSONException e) {
		}
	}

	public long getId() {
		try {
			return getLong(ID);
		} catch (JSONException e) {
		}

		return 0;
	}
}
