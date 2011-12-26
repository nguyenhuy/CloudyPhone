package com.cloudyphone.android.model.infor;

import android.os.Build;

import com.cloudyphone.android.model.MyParseObject;

public class ParsePhoneInfor extends MyParseObject {

	private static final String BOARD = "board", BRAND = "brand",
			CPU_ABI = "cpuAbi", DEVICE = "device", DISPLAY = "display",
			FINGERPRINT = "fingerprint", HOST = "host", ID = "id",
			MANUFACTURER = "manufacterer", MODEL = "model",
			PRODUCT = "product", TAGS = "tags", TYPE = "type", USER = "user";

	public ParsePhoneInfor() {
		super(ParsePhoneInfor.class.getSimpleName());

		put(BOARD, Build.BOARD);
		put(BRAND, Build.BRAND);
		put(CPU_ABI, Build.CPU_ABI);
		put(DEVICE, Build.DEVICE);
		put(DISPLAY, Build.DISPLAY);
		put(FINGERPRINT, Build.FINGERPRINT);
		put(HOST, Build.HOST);
		put(ID, Build.ID);
		put(MANUFACTURER, Build.MANUFACTURER);
		put(MODEL, Build.MODEL);
		put(PRODUCT, Build.PRODUCT);
		put(TAGS, Build.TAGS);
		put(TYPE, Build.TYPE);
		put(USER, Build.USER);
	}
}
