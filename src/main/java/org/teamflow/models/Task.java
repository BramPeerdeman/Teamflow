package org.teamflow.models;

import java.util.ArrayList;
import java.util.List;

public class Task
{
    private String title;
    private boolean isPinned;
    private List<Message> messages;

    public Task(String title)
    {
        this.title = title;
        this.isPinned = false;
        this.messages = new ArrayList<>();
    }

    public void pinTask()
    {

    }

    public void addMessage(Message message)
    {
        this.messages.add(message);
    }
}
