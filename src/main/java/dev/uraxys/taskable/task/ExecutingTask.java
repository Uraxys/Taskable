package dev.uraxys.taskable.task;

public abstract class ExecutingTask extends GenericTask {

	private boolean called = false;

	@Override
	public void preInit() {
		setDone(false);
		this.called = false;
	}

	@Override
	public void executeTask() {
		if (isTaskDone()) return;
		if (this.called) return;
		this.called = true;
		this.execute();
	}

	public abstract void execute();
}
