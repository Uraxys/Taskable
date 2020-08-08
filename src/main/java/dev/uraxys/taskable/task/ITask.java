package dev.uraxys.taskable.task;

import dev.uraxys.taskable.TaskManager;

@SuppressWarnings("unused")
public interface ITask {

	default void print(Object object) {
		TaskManager.PRINT_METHOD.print("[Task - "+this.getClass().getSimpleName()+"] "+object);
	}

	void preInit();
	void init();
	void executeTask();
	boolean isTaskDone();

}
