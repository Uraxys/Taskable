package dev.uraxys.taskable.test.tasks;

import dev.uraxys.taskable.test.TaskCondition;
import dev.uraxys.taskable.test.task.TickingTask;

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
