package com.cloudyphone.android.controller.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

/**
 * An async task that will run commands
 * 
 * @author nguyenthanhhuy
 * 
 */
public class CommandsAsyncTask extends AsyncTask<Command, Void, Void> {
	private Command preExecuteCommand, callbackCommand;
	private Context context;
	private boolean showDialog;
	private String dialogMessage;
	private ProgressDialog dialog;

	/**
	 * 
	 * @param preExecuteComand
	 * @param executeCommand
	 * @param callbackCommand
	 * @param showDialog
	 *            true if you want to show a dialog onPreExecute
	 * @param context
	 *            must be provided if showDialog is true
	 * @param dialogMessage
	 *            must be provided if showDialog is true
	 */
	public CommandsAsyncTask(Command preExecuteComand, Command callbackCommand,
			boolean showDialog, Context context, String dialogMessage) {
		this.preExecuteCommand = preExecuteComand;
		this.callbackCommand = callbackCommand;
		this.showDialog = showDialog;
		this.dialogMessage = dialogMessage;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		if (showDialog && context != null && !TextUtils.isEmpty(dialogMessage)) {
			// Show a progress dialog
			dialog = ProgressDialog.show(context, "", dialogMessage);
		}

		if (preExecuteCommand != null) {
			preExecuteCommand.execute();
		}
	}

	@Override
	protected Void doInBackground(Command... commands) {
		if (commands == null) {
			return null;
		}

		for (Command c : commands) {
			c.execute();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);

		if (callbackCommand != null) {
			callbackCommand.execute();
		}

		if (showDialog && dialog != null) {
			dialog.dismiss();
		}
	}
}
