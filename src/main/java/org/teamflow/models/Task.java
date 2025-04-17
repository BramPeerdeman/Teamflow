package org.teamflow.models;

public class Task
{
    private int id;
    private String title;
    private String content;
    private boolean isPinned;
    private int assignedUserId;

    public Task(int id, String title, String content, int assignedUserId)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.assignedUserId = assignedUserId;
        this.isPinned = false;
    }

    public Task() {} // For Gson

    public int getId()
    {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent()
    {
        return content;
    }

    public boolean isPinned()
    {
        return isPinned;
    }

    public int getAssignedUserId()
    {
        return assignedUserId;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setPinned(boolean pinned)
    {
        this.isPinned = pinned;
    }

    public void setAssignedUserId(int assignedUserId)
    {
        this.assignedUserId = assignedUserId;
    }

    public void pinTask()
    {
        this.isPinned = true;
    }

    public void unpinTask()
    {
        this.isPinned = false;
    }
}
