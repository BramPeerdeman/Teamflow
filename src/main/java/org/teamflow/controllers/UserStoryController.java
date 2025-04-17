package org.teamflow.controllers;

import org.teamflow.models.Task;
import org.teamflow.models.UserStory;
import org.teamflow.storage.StorageManager;

import java.util.ArrayList;

public class UserStoryController {
    private static UserStoryController instance;
    private UserStory currentUserStory;
    private ArrayList<UserStory> userStories;
    private StorageManager storageManager;
    private ArrayList<Task> tasks = new ArrayList<>();

    private UserStoryController() {
        storageManager = new StorageManager();
        userStories = storageManager.loadUserStories();
    }

    public static UserStoryController getInstance() {
        if (instance == null) {
            instance = new UserStoryController();
        }
        return instance;
    }

    public boolean createEpic(String name) {
        if (getUserStoryByName(name) != null) return false;
        int nextId = generateNextEpicId();
        UserStory userStory = new UserStory(name, nextId, tasks);
        userStories.add(userStory);
        storageManager.saveUserStories(userStories);
        currentUserStory = userStory;
        return true;
    }


    private int generateNextEpicId()
    {
        int maxId = 0;
        for (UserStory userStory : userStories)
        {
            if (userStory.getId() > maxId)
            {
                maxId = userStory.getId();
            }
        }
        return maxId + 1;
    }

    private UserStory getUserStoryByName(String name)
    {
        for (UserStory userStory : userStories) {
            if (userStory.getTitel().equalsIgnoreCase(name))
            {
                return userStory;
            }
        }
        return null;
    }

    public UserStory getCurrentUserStory() {
        return currentUserStory;
    }

    public String getCurrentUserStoryName() {
        return currentUserStory != null ? currentUserStory.getTitel() : null;
    }

    public void saveUserStories() {
        storageManager.saveUserStories(userStories);
    }
}
