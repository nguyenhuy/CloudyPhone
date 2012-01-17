package com.cloudyphone.android.controller.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cloudyphone.android.R;
import com.cloudyphone.android.controller.listeners.ResetPasswordClickListener;

public class ResetPasswordActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password);

		setListeners();
	}

	private void setListeners() {
		EditText email = (EditText) findViewById(R.id.email_et);
		Button resetBtn = (Button) findViewById(R.id.reset_password_btn);

		// sign up btn click listener
		resetBtn.setOnClickListener(new ResetPasswordClickListener(this, email));
	}
}
