package org.teamflow.controllers;

import org.teamflow.models.User;
import org.teamflow.storage.StorageManager;
import org.teamflow.utils.JSONHelper;

import java.util.ArrayList;

public class UserController {
    private User currentUser;
    private ArrayList<User> users;
    private StorageManager storageManager;

    public UserController()
    {
        storageManager = new StorageManager();
        users = storageManager.loadUsers();
    }

    public boolean createUser(String name)
    {
        if (getUserByName(name) != null) return false;
        int nextId = generateNextUserId();
        User user = new User(nextId, name);
        users.add(user);
        storageManager.saveUsers(users);
        currentUser = user;
        return true;
    }

    private int generateNextUserId()
    {
        int maxId = 0;
        for (User user : users)
        {
            if (user.getId() > maxId)
            {
                maxId = user.getId();
            }
        }
        return maxId + 1;
    }

    public boolean login(String name)
    {
        User user = getUserByName(name);
        if (user != null)
        {
            currentUser = user;
            return true;
        }
        return false;
    }

    private User getUserByName(String name)
    {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name))
            {
                return u;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getCurrentUserName() {
        return currentUser != null ? currentUser.getName() : null;
    }

    public boolean setScrumMasterFlag(String username, boolean isScrumMaster) {
        ArrayList<User> users = JSONHelper.loadUsers();

        if (isScrumMaster) {
            for (User user : users) {
                if (Boolean.TRUE.equals(user.getIsScrumMaster()) && !user.getName().equals(username)) {
                    System.out.println("Er is al een Scrum Master: " + user.getName());
                    return false; // niet toegestaan
                }
            }
        }

        for (User user : users) {
            if (user.getName().equals(username)) {
                user.setIsScrumMaster(isScrumMaster);
                JSONHelper.saveUsers(users);
                return true;
            }
        }

        return false; // gebruiker niet gevonden
    }


}

