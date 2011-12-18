package com.cloudyphone.android.controller.push;

import android.content.Context;

import com.cloudyphone.android.controller.activities.CloudyPhoneActivity;
import com.parse.ParseUser;
import com.parse.PushService;

public class PushManager {
	/**
	 * Returns push channnel of this user, which is username
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 *             if user was not logged in
	 */
	private String getChannel(Context context) throws Exception {
		ParseUser user = ParseUser.getCurrentUser();

		if (user == null) {
			// not login yet
			throw new Exception("Not logged in yet.");
		}

		return user.getUsername();
	}

	/**
	 * Subscribe to push service with the channel is current user id
	 * 
	 * @param context
	 * @throws Exception
	 *             if user was not logged in
	 */
	public void subscribe(Context context) throws Exception {
		// Since we don't launch activity when receive push notification
		// Activity can be set to whatever activity
		// PushService.subscribe(context, getChannel(context),
		// CloudyPhoneActivity.class);

		// TODO test only
		PushService.subscribe(context, "huy", CloudyPhoneActivity.class);
	}

	/**
	 * Unsubscribe to push service
	 * 
	 * @throws Exception
	 *             if user was not logged in
	 */
	public void unsubscribe(Context context) throws Exception {
		// PushService.unsubscribe(context, getChannel(context));

		// TODO test only
		PushService.unsubscribe(context, "huy");
	}
}
