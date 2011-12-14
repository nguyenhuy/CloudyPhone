package com.cloudyphone.android.controllers.sync;

import java.util.Collection;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Update parse objects by find all of objects in the same class, delete them
 * and execute the command
 * 
 * @author nguyenthanhhuy
 * 
 */
public class UpdateThread extends Thread {
	private String parseObjectClass;
	private Command command;

	public UpdateThread(String parseObjectClass, Command command) {
		this.parseObjectClass = parseObjectClass;
		this.command = command;
	}

	@Override
	public void run() {
		ParseQuery query = new ParseQuery(parseObjectClass);

		try {
			Collection<ParseObject> objects = query.find();

			for (ParseObject object : objects) {
				object.delete();
			}

			command.execute();
		} catch (ParseException e) {
		}
	}
}
