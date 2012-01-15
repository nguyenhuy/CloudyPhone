package com.cloudyphone.android.controller.listeners;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.callbacks.MyLoginCallback;
import com.parse.ParseUser;

public class LoginClickListener implements OnClickListener {
	private EditText email, password;
	private Context context;

	public LoginClickListener(Context context, EditText email, EditText password) {
		this.email = email;
		this.password = password;
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		// Show Progress dialog
		ProgressDialog pd = ProgressDialog.show(context, "",
				context.getString(R.string.logining));
		pd.setCancelable(true);

		ParseUser.logInInBackground(email.getText().toString(), password
				.getText().toString(), new MyLoginCallback(context, pd));
	}
}
