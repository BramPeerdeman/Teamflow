package org.teamflow.controllers;

import org.teamflow.models.Epic;
import org.teamflow.models.UserStory;
import org.teamflow.storage.StorageManager;

import java.util.ArrayList;

public class EpicController {
    private Epic currentEpic;
    private ArrayList<Epic> epics;
    private StorageManager storageManager;
    private ArrayList<UserStory> userStories = new ArrayList<>();

    public EpicController()
    {
        storageManager = new StorageManager();
        epics = storageManager.loadEpics();
    }

    public boolean createEpic(String name)
    {
        if (getEpicByName(name) != null) return false;
        int nextId = generateNextEpicId();
        Epic epic = new Epic(nextId, name, userStories);
        epics.add(epic);
        storageManager.saveEpics(epics);
        currentEpic = epic;
        return true;
    }

    private int generateNextEpicId()
    {
        int maxId = 0;
        for (Epic epic : epics)
        {
            if (epic.getId() > maxId)
            {
                maxId = epic.getId();
            }
        }
        return maxId + 1;
    }

    public boolean login(String name)
    {
        Epic user = getEpicByName(name);
        if (user != null)
        {
            currentEpic = user;
            return true;
        }
        return false;
    }

    private Epic getEpicByName(String name)
    {
        for (Epic epic : epics) {
            if (epic.getTitel().equalsIgnoreCase(name))
            {
                return epic;
            }
        }
        return null;
    }

    public Epic getCurrentEpic() {
        return currentEpic;
    }

    public String getCurrentEpicName() {
        return currentEpic != null ? currentEpic.getTitel() : null;
    }
}
