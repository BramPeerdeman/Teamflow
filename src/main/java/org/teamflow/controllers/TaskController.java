package org.teamflow.controllers;

import org.teamflow.models.Task;
import org.teamflow.storage.StorageManager;

import java.util.ArrayList;

public class TaskController {
    private ArrayList<Task> tasks;
    private StorageManager storageManager;

    public TaskController() {
        storageManager = new StorageManager();
        tasks = storageManager.loadTasks();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task createTask(String title, String content, int userId) {
        int nextId = generateNextId();
        Task task = new Task(nextId, title, content, userId);
        tasks.add(task);
        storageManager.saveTasks(tasks);
        return task;
    }

    public ArrayList<Task> getTasksForUser(int userId) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getAssignedUserId() == userId) {
                result.add(task);
            }
        }
        return result;
    }

    public boolean pinTask(int taskId, int userId) {
        for (Task task : tasks) {
            if (task.getId() == taskId && task.getAssignedUserId() == userId) {
                task.pinTask();
                storageManager.saveTasks(tasks);
                return true;
            }
        }
        return false;
    }

    public boolean unpinTask(int taskId, int userId) {
        for (Task task : tasks) {
            if (task.getId() == taskId && task.getAssignedUserId() == userId) {
                task.unpinTask();
                storageManager.saveTasks(tasks);
                return true;
            }
        }
        return false;
    }

    private int generateNextId() {
        int maxId = 0;
        for (Task t : tasks) {
            if (t.getId() > maxId) maxId = t.getId();
        }
        return maxId + 1;
    }
}
