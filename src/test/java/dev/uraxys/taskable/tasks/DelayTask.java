package dev.uraxys.taskable.tasks;

import dev.uraxys.taskable.task.ExecutingTask;

public class DelayTask extends ExecutingTask {

	private long delay;

	public DelayTask(long delay) {
		this.delay = delay;
	}

	@Override
	public void execute() {
		System.out.println("Running delay task, time to delay: " + this.delay);
		try {
			Thread.sleep(this.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished delay.");
		setDone(true);
	}
}
