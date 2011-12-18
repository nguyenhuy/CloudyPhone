package com.cloudyphone.android.controller.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.cloudyphone.android.Constants;

public class ServerConnector {
	public void sendPost(final Map<String, String> keysValues) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();
				HttpPost request = new HttpPost(Constants.SERVER);

				// Create the name value pairs
				List<NameValuePair> nameValuePairs = createNameValuePairs(keysValues);

				try {
					request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					client.execute(request);
				} catch (Exception e) {
					// TODO may notify user here
				}
			}
		}).start();
	}

	/**
	 * Creates a list of NameValuePair which can be used to send post request to
	 * server
	 * 
	 * @param keysValues
	 * @return
	 */
	public List<NameValuePair> createNameValuePairs(
			Map<String, String> keysValues) {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
				keysValues.size());

		for (String key : keysValues.keySet()) {
			nameValuePairs
					.add(new BasicNameValuePair(key, keysValues.get(key)));
		}

		return nameValuePairs;
	}
}
