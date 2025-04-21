package org.teamflow.models;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String inhoud;
    private User afzender;
    private LocalDateTime timestamp;
    private Integer epicId;
    private Integer userStoryId;
    private Integer taskId;
    private boolean pinned = false;

    public Message(String inhoud, User afzender, int id, Integer epicId, Integer userStoryId, Integer taskId, boolean pinned) {
        this.id = id;
        this.inhoud = inhoud;
        this.afzender = afzender;
        this.timestamp = LocalDateTime.now();
        this.epicId = epicId;
        this.userStoryId = userStoryId;
        this.taskId = taskId;
        this.pinned = pinned;
    }


    public boolean isPinned() {
        return pinned;
    }
    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }
    public int getId() { return id; }
    public String getInhoud() { return inhoud; }
    public User getAfzender() { return afzender; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Integer getEpicId() { return epicId; }
    public Integer getUserStoryId() { return userStoryId; }
    public Integer getTaskId() { return taskId; }
}
