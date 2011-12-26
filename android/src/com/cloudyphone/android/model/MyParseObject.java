package com.cloudyphone.android.model;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class MyParseObject extends ParseObject {
	private static final String USERNAME = "username";

	public MyParseObject(String theClassName) {
		super(theClassName);

		ParseUser user = ParseUser.getCurrentUser();
		// Save username to this object for later query
		put(USERNAME, user.getUsername());
	}
}
