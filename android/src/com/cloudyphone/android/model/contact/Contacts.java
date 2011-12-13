package com.cloudyphone.android.model.contact;

import java.util.Collection;

public class Contacts {
	private Collection<ParseContact> contacts;

	public Contacts(Collection<ParseContact> contacts) {
		this.contacts = contacts;
	}

	public Collection<ParseContact> getContacts() {
		return contacts;
	}
	
	public ParseContacts toParseContacts(){
		return new ParseContacts(contacts);
	}
}
