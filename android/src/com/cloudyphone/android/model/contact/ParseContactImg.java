package com.cloudyphone.android.model.contact;

import java.io.IOException;
import java.io.InputStream;

import com.parse.ParseFile;

public class ParseContactImg extends ParseFile {

	public ParseContactImg(long contactId, byte[] data) {
		super(createFileName(contactId), data);
	}

	public static final String createFileName(long contactId) {
		return contactId + ".jpg";
	}

	public static final byte[] convertFileToByte(InputStream is, long fileLength) {
		try {
			// You cannot create an array using a long type.
			// It needs to be an int type.
			// Before converting to an int type, check
			// to ensure that file is not larger than Integer.MAX_VALUE.
			if (fileLength > Integer.MAX_VALUE) {
				// File is too large
				return null;
			}

			// Create the byte array to hold the data
			byte[] bytes = new byte[(int) fileLength];

			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				return null;
			}

			return bytes;
		} catch (IOException e) {
		} finally {
			// Close the input stream
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

		return null;
	}
}
