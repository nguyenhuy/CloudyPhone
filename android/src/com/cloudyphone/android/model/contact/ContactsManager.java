package com.cloudyphone.android.model.contact;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class ContactsManager {

	/**
	 * Gets all Contacts which has phone numbers
	 * 
	 * @param cr
	 * @returns contacts if there is any contact.
	 */
	public ParseContacts getAllContacts(ContentResolver cr) {
		Collection<JSONContact> contacts = new ArrayList<JSONContact>();

		// Contacts cursor
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);

		Cursor phonesCursor;
		String contactId, name;
		String selection;
		String equal = " = ";
		Collection<String> numbers;

		while (cursor.moveToNext()) {
			// get contact id
			contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));

			// Construct the selection for phone cursor
			selection = new StringBuilder(Phone.CONTACT_ID).append(equal)
					.append(contactId).toString();

			// the cursor to get phone numbers
			phonesCursor = cr.query(Phone.CONTENT_URI, null, selection, null,
					null);

			// get phone numbers
			numbers = new ArrayList<String>();
			while (phonesCursor.moveToNext()) {
				numbers.add(phonesCursor.getString(phonesCursor
						.getColumnIndex(Phone.NUMBER)));
			}
			phonesCursor.close();

			// Only add contacts which have at least phone number
			if (numbers.size() > 0) {
				name = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

				contacts.add(new JSONContact(Long.parseLong(contactId), name,
						numbers));
			}
		}

		cursor.close();

		return new ParseContacts(contacts);
	}

	public Collection<ParseContactImg> getAllContactImages(ContentResolver cr,
			Collection<JSONContact> contacts) {

		Collection<ParseContactImg> images = new ArrayList<ParseContactImg>();
		for (JSONContact c : contacts) {
			try {
				images.add(getContactImg(cr, c.getId()));
				// if exception is thrown, ignore this image
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
		}

		return images;
	}

	private ParseContactImg getContactImg(ContentResolver cr, long id)
			throws FileNotFoundException, IOException {

		Uri auri = getPhotoUri(id);
		InputStream is = cr.openInputStream(auri);
		int fileLength = is.available();

		if (fileLength > 0) {
			byte[] data = ParseContactImg.convertFileToByte(is, fileLength);
			return new ParseContactImg(id, data);
		}

		return null;
	}

	private Uri getPhotoUri(long contactId) {
		Uri contactUri = ContentUris.withAppendedId(
				android.provider.ContactsContract.Contacts.CONTENT_URI,
				contactId);
		Uri photoUri = Uri
				.withAppendedPath(
						contactUri,
						android.provider.ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
		return photoUri;
	}
}
