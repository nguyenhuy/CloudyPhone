package com.cloudyphone.android.controller.receivers;

import java.lang.reflect.Method;

import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;

public class DenyCallReceiver extends PushReceiver {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Context context, JSONObject data) {
		try {
			// Get the boring old TelephonyManager
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			// Get the getITelephony() method
			Class classTelephony = Class.forName(telephonyManager.getClass()
					.getName());
			Method methodGetITelephony = classTelephony
					.getDeclaredMethod("getITelephony");

			// Make the method become accessible
			methodGetITelephony.setAccessible(true);

			// Invoke getITelephony() to get the ITelephony interface
			Object telephonyInterface = methodGetITelephony
					.invoke(telephonyManager);

			// Get the endCall method from ITelephony
			Class telephonyInterfaceClass = Class.forName(telephonyInterface
					.getClass().getName());
			Method methodEndCall = telephonyInterfaceClass
					.getDeclaredMethod("endCall");

			// Invoke endCall()
			methodEndCall.invoke(telephonyInterface);
		} catch (Exception e) {
			// Can't do it, may notify server
		}
	}
}
