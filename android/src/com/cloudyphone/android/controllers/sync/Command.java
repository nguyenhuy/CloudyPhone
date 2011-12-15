package com.cloudyphone.android.controllers.sync;

public interface Command {
	void execute();

	//TODO used for benchmark
	boolean isFinished();
}
