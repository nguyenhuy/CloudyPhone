package com.cloudyphone.android.utils;

import android.util.Log;

public class Logger {
	private static final String LOG = "CloudyPhone";

	public static void print(Object caller, String message) {
		Log.v(LOG, caller.getClass().getName() + "::" + message);
	}
}
