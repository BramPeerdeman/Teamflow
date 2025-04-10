package org.teamflow.models;

public class Task {
    private String content;
    private boolean isPinned;

    public Task(String content) {
        this.content = content;
        this.isPinned = false;
    }

    public void pinTask() {
        this.isPinned = true;
    }

    public void unpinTask() {
        this.isPinned = false;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public boolean isPinned() {
        return isPinned;
    }
}
