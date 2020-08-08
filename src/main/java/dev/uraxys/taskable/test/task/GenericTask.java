package dev.uraxys.taskable.test.task;

public abstract class GenericTask implements ITask {

	private boolean done = false;

	@Override
	public void preInit() {
		this.done = false;
	}

	@Override
	public boolean isTaskDone() {
		return this.done;
	}

	@Override
	public void init() {

	}

	public void setDone(boolean value) {
		this.done = value;
	}
}
