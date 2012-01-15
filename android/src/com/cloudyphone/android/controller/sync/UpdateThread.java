package com.cloudyphone.android.controller.sync;

import com.cloudyphone.android.controller.commands.Command;


/**
 * Update parse objects by find all of objects in the same class, delete them
 * and execute the command
 * 
 * @author nguyenthanhhuy
 * 
 */
public class UpdateThread extends Thread {
	private Command command;

	public UpdateThread(Command command) {
		// this.parseObjectClass = parseObjectClass;
		this.command = command;
	}

	@Override
	public void run() {

		// This is to find and delete all existing objects
		// ParseQuery query = new ParseQuery(parseObjectClass);
		//
		// try {
		// Collection<ParseObject> objects = query.find();
		//
		// for (ParseObject object : objects) {
		// object.delete();
		// }

		command.execute();
		// } catch (ParseException e) {
		// }
	}
}
