package com.cloudyphone.android.controller.sync;

public interface Command {
	void execute();

	//TODO used for benchmark
	boolean isFinished();
}
