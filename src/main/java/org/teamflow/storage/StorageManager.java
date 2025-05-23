package org.teamflow.storage;

import org.teamflow.models.*;
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

    public ArrayList<Message> loadMessages()
    {
        return JSONHelper.loadMessages();
    }

    public void saveMessages(ArrayList<Message> messages)
    {
        JSONHelper.saveMessages(messages);
    }

    public ArrayList<Task> loadTasks()
    {
        return JSONHelper.loadTasks();
    }

    public void saveTasks(ArrayList<Task> tasks)
    {
        JSONHelper.saveTasks(tasks);
    }

    public ArrayList<UserStory> loadUserStories()
    {
        return JSONHelper.loadUserStories();
    }

    public void saveUserStories(ArrayList<UserStory> userStories)
    {
        JSONHelper.saveUserStories(userStories);
    }
}
