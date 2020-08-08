package dev.uraxys.taskable.task;

public abstract class TickingTask extends GenericTask {

	@Override
	public void executeTask() {
		if (isTaskDone()) return;
		this.tick();
	}

	public abstract void tick();
}
