package org.teamflow.controllers;

import org.teamflow.models.Message;
import org.teamflow.models.User;
import org.teamflow.storage.StorageManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MessageController {
    private ArrayList<Message> messages;
    private StorageManager storageManager;

    public MessageController() {
        this.storageManager = new StorageManager();
        this.messages = storageManager.loadMessages();
    }

    public boolean createMessage(String inhoud, User afzender, Integer epicId, Integer userStoryId, Integer taskId, boolean pinned) {
        int newId = messages.stream().mapToInt(Message::getId).max().orElse(0) + 1;
        Message msg = new Message(inhoud, afzender, newId, epicId, userStoryId, taskId, pinned);
        msg.setPinned(pinned);
        messages.add(msg);
        storageManager.saveMessages(messages);
        return true;
    }

    public ArrayList<Message> getMessagesForEpic(int epicId) {
        return filterMessages(msg -> msg.getEpicId() != null && msg.getEpicId() == epicId);
    }

    public ArrayList<Message> getMessagesForUserStory(int storyId) {
        return filterMessages(msg -> msg.getUserStoryId() != null && msg.getUserStoryId() == storyId);
    }

    public ArrayList<Message> getMessagesForTask(int taskId) {
        return filterMessages(msg -> msg.getTaskId() != null && msg.getTaskId() == taskId);
    }

    private ArrayList<Message> filterMessages(java.util.function.Predicate<Message> predicate) {
        return messages.stream()
                .filter(predicate)
                .sorted((a, b) -> {
                    // Pinned messages first
                    if (a.isPinned() && !b.isPinned()) return -1;
                    if (!a.isPinned() && b.isPinned()) return 1;
                    // Otherwise, sort by timestamp
                    return a.getTimestamp().compareTo(b.getTimestamp());
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
