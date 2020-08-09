package dev.uraxys.taskable;

import dev.uraxys.taskable.task.ExecutingTask;
import dev.uraxys.taskable.task.ITask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class TaskManager {

	public static PrintMethod PRINT_METHOD = System.out::println;

	// Important task
	private ITask currentImportantTask;
	private boolean newImportantTask = false;

	// Current task
	private ITask currentTask;
	private int currentTaskIndex = 0;

	// Tasks
	private final List<ITask> tasks = new ArrayList<>();
	private final List<ExecutingTask> globalTasks = new ArrayList<>();

	// Basic event system.
	private TaskEvent onTaskInit = null;
	private TaskEvent onTaskTick = null;
	private TaskEvent onTaskComplete = null;
	private TaskEvent onTaskListComplete = null;



	public TaskManager() {}

	public TaskManager(ITask... tasks) {
		this.tasks.addAll(Arrays.asList(tasks));
	}

	/**
	 * Tick the TaskManager, this is required to use all of the tasks.
	 */
	public void tick() {
		// Run global tasks if there isn't a important task currently running.
		if (this.currentImportantTask == null) {
			// No important tasks are running, then run the global tasks.
			for (ExecutingTask task:this.globalTasks) {
				task.init();
				task.executeTask();
			}
		}

		// If there isn't any other tasks then do nothing.
		if (this.tasks.isEmpty()) return;

		// Check if we have a important task.
		if (this.currentImportantTask != null && this.newImportantTask) {
			this.newImportantTask = false;
			this.currentTask = this.currentImportantTask;
			this.currentTask.preInit();
			this.currentTask.init();
			if (this.onTaskInit != null) this.onTaskInit.call(this.currentTask, -1);
		}

		// Make sure we got a task.
		if (getCurrentTask() == null) {
			// Make sure the task index isn't outside the bounds.
			if (this.currentTaskIndex >= this.tasks.size()) {
				if (this.onTaskListComplete != null) this.onTaskListComplete.call(null, -1);
				this.currentTaskIndex = 0;
			}

			// Get the next task.
			setCurrentTask(this.tasks.get(this.currentTaskIndex));
			if (getCurrentTask() != null) {
				ITask task = getCurrentTask();
				task.preInit();
				task.init();
				if (this.onTaskInit != null) this.onTaskInit.call(task, this.currentTaskIndex);
			}
		}

		ITask current = getCurrentTask();
		if (current == null) throw new NullPointerException("Current task is null!");

		// Check if the current task is done.
		if (!current.isTaskDone()) {
			if (this.currentImportantTask != null && current == this.currentImportantTask) {
				// If the current important task isn't null and the current task is the important one.
				if (this.onTaskTick != null) this.onTaskTick.call(currentTask, -1);
			} else {
				if (this.onTaskTick != null) this.onTaskTick.call(currentTask, this.currentTaskIndex);
			}
			current.executeTask();
			return;
		}

		// The task is done, go to the next task.
		if (this.currentImportantTask != null && current == this.currentImportantTask) {
			// If the current important task isn't null and the current task is the important one.
			if (this.onTaskComplete != null) this.onTaskComplete.call(current, -1);
			this.currentImportantTask = null;
		} else {
			if (this.onTaskComplete != null) this.onTaskComplete.call(current, this.currentTaskIndex);
			this.currentTaskIndex++;
		}
		setCurrentTask(null);
	}



	/*
	 * Modify the current important task.
	 */



	/**
	 * Set an important task, this will be called before anything else.
	 *
	 * @param   task
	 *          The important ITask.
	 */
	public void setImportantTask(ITask task) {
		this.currentImportantTask = task;
		this.newImportantTask = true;
	}



	/*
	 * Modify or access the current task.
	 */



	/**
	 * Set the current ITask.
	 *
	 * @param   task
	 *          The new ITask.
	 */
	public void setCurrentTask(ITask task) {
		this.currentTask = task;
	}

	/**
	 * Get the current ITask.
	 *
	 * @return  The current ITask.
	 */
	public ITask getCurrentTask() {
		return this.currentTask;
	}



	/*
	 * Task events.
	 */



	public void setOnTaskInitEvent(TaskEvent onTaskInit) {
		this.onTaskInit = onTaskInit;
	}

	public void setOnTaskTickEvent(TaskEvent onTaskTick) {
		this.onTaskTick = onTaskTick;
	}

	public void setOnTaskCompleteEvent(TaskEvent onTaskComplete) {
		this.onTaskComplete = onTaskComplete;
	}

	public void setOnTaskListCompleteEvent(TaskEvent onTaskListComplete) {
		this.onTaskListComplete = onTaskListComplete;
	}



	/*
	 * Handle the ITask list.
	 */



	/**
	 * Add a ITask to the task list.
	 *
	 * @param   task
	 *          The ITask to add.
	 */
	public void addTask(ITask task) {
		this.tasks.add(task);
	}

	/**
	 * Add one or more ITasks to the task list.
	 *
	 * @param   tasks
	 *          The ITasks as an array.
	 */
	public void addTasks(ITask... tasks) {
		this.tasks.addAll(Arrays.asList(tasks));
	}



	/*
	 * Handle the global ITask list.
	 */



	/**
	 * Add a ExecutingTask to the global task list.
	 *
	 * @param   task
	 *          The ExecutingTask to add.
	 */
	public void addGlobalTask(ExecutingTask task) {
		this.globalTasks.add(task);
	}

	/**
	 * Add one or more ExecutingTask to the global task list.
	 *
	 * @param   tasks
	 *          The ExecutingTask as an array.
	 */
	public void addGlobalTasks(ExecutingTask... tasks) {
		this.globalTasks.addAll(Arrays.asList(tasks));
	}

}
