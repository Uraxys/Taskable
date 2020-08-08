package dev.uraxys.taskable.test.tasks;

import dev.uraxys.taskable.test.task.ExecutingTask;

public class PrintTask extends ExecutingTask {

	private String message;

	public PrintTask(String message) {
		this.message = message;
	}

	@Override
	public void execute() {
		System.out.println(this.message);
		setDone(true);
	}
}
