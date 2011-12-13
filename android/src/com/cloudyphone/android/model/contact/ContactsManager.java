package com.cloudyphone.android.model.contact;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContactsManager {

	/**
	 * Gets all Contacts which has phone numbers
	 * 
	 * @param cr
	 * @returns contacts if there is any contact.
	 */
	public Contacts getAllContacts(ContentResolver cr) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);

		if (cur != null && cur.getCount() > 0) {
			try {
				while (cur.moveToNext()) {
					// check if the contact has phone or not
					boolean hasPhone = Integer
							.parseInt(cur.getString(cur
									.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0;

					if (hasPhone) {
						// Has phone, get the information and add to contacts
						// list
						String id = cur.getString(cur
								.getColumnIndex(ContactsContract.Contacts._ID));
						String name = cur
								.getString(cur
										.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

						// get all phone numbers
						ArrayList<String> phoneNumbers = getPhoneNumbers(id, cr);

						Contact c = new Contact(Long.parseLong(id), name,
								phoneNumbers);
						contacts.add(c);
					}
				}
			} finally {
				cur.close();
			}
		}

		return new Contacts(contacts);
	}

	/**
	 * Returns phone numbers of the contact with that id
	 * 
	 * @param id
	 * @param cr
	 * @return
	 */
	private ArrayList<String> getPhoneNumbers(String id, ContentResolver cr) {

		ArrayList<String> phones = new ArrayList<String>();

		Cursor pCur = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
				new String[] { id }, null);

		if (pCur != null) {
			try {
				if (pCur.getCount() > 0) {
					while (pCur.moveToNext()) {
						phones.add(pCur.getString(pCur
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
					}
				}
			} finally {
				pCur.close();
			}
		}
		return (phones);
	}
}
