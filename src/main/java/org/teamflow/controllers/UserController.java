package org.teamflow.controllers;
import org.teamflow.models.User;

public class UserController
{
    private User currentUser;

    public void createUser(String name)
    {
        currentUser = new User(name);
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public String getCurrentUserName()
    {
        return currentUser != null ? currentUser.getName() : null;
    }
}
