package dev.uraxys.taskable.test.task;

@SuppressWarnings("unused")
public interface ITask {

	default void print(Object object) {
		System.out.println("[Task - "+this.getClass().getSimpleName()+"] "+object);
	}

	void preInit();
	void init();
	void executeTask();
	boolean isTaskDone();

}
