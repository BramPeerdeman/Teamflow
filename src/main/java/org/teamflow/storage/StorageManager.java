package org.teamflow.storage;

import org.teamflow.models.Epic;
import org.teamflow.models.Task;
import org.teamflow.models.User;
import org.teamflow.utils.JSONHelper;

import java.util.ArrayList;

public class StorageManager
{
    public ArrayList<User> loadUsers()
    {
        return JSONHelper.loadUsers();
    }

    public void saveUsers(ArrayList<User> users)
    {
        JSONHelper.saveUsers(users);
    }

    public ArrayList<Epic> loadEpics()
    {
        return JSONHelper.loadEpics();
    }

    public void saveEpics(ArrayList<Epic> epics)
    {
        JSONHelper.saveEpics(epics);
    }

    public ArrayList<Task> loadTasks()
    {
        return JSONHelper.loadTasks();
    }

    public void saveTasks(ArrayList<Task> tasks)
    {
        JSONHelper.saveTasks(tasks);
    }


}
