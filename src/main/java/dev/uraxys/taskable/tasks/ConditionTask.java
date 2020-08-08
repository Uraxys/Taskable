package dev.uraxys.taskable.tasks;

import dev.uraxys.taskable.task.TickingTask;
import dev.uraxys.taskable.TaskCondition;

@SuppressWarnings("unused")
public class ConditionTask extends TickingTask {

	private final TaskCondition condition;

	public ConditionTask(TaskCondition condition) {
		this.condition = condition;
	}

	@Override
	public void tick() {
		if (this.condition.check()) setDone(true);
	}
}
