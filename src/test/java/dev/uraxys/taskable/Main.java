package dev.uraxys.taskable;

import dev.uraxys.taskable.tasks.CountTask;
import dev.uraxys.taskable.tasks.DelayTask;
import dev.uraxys.taskable.tasks.MathTask;
import dev.uraxys.taskable.tasks.PrintTask;
import org.junit.jupiter.api.Test;

public class Main {

	private TaskManager taskManager;
	private int completed = 0;

	@Test
	public void test() {
		this.taskManager = new TaskManager();
		this.taskManager.addTasks(
				new PrintTask("Hello! This is the first task!"),
				new MathTask(1000, 987),
				new DelayTask(1000),
				new CountTask(25),
				new PrintTask("Finished all tasks, repeating.")
		);

		this.taskManager.setOnTaskListCompleteEvent((task, index) -> {
			this.completed++;
			System.out.println("Task list completed.");
		});

		while (completed < 5) {
			this.taskManager.tick();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
