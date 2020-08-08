package dev.uraxys.taskable;

import dev.uraxys.taskable.task.ITask;

public interface TaskEvent {

	/**
	 * @param   task
	 *          The ITask for the event.
	 *
	 * @param   index
	 *          The index of the ITask, -1 will be used if this is an important task.
	 */
	void call(ITask task, int index);

}
