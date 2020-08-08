package dev.uraxys.taskable.test.tasks;

import dev.uraxys.taskable.test.task.TickingTask;

public class CountTask extends TickingTask {

	private int target;
	private int current = 0;

	public CountTask(int target) {
		this.target = target;
	}

	@Override
	public void init() {
		System.out.println("Starting ticking task, target: " + this.target);
		this.current = 0;
	}

	@Override
	public void tick() {
		this.current++;
		System.out.println("Counting... (" + this.current + "/" + this.target + ")");
		if (this.current >= this.target) {
			setDone(true);
		}
	}
}
