package dev.uraxys.taskable.tasks;

import dev.uraxys.taskable.task.ExecutingTask;

public class MathTask extends ExecutingTask {

	private int numberOne;
	private int numberTwo;

	public MathTask(int numberOne, int numberTwo) {
		this.numberOne = numberOne;
		this.numberTwo = numberTwo;
	}

	@Override
	public void execute() {
		System.out.println("Result: " + (this.numberOne + this.numberTwo));
		setDone(true);
	}
}
