package org.teamflow.controllers;

import org.teamflow.models.Message;
import org.teamflow.models.User;
import org.teamflow.storage.StorageManager;

import java.util.ArrayList;

public class MessageController {
    private Message currentMessage;
    private ArrayList<Message> messages;
    private StorageManager storageManager;
    private User user;

    public MessageController(User user)
    {
        storageManager = new StorageManager();
        messages = storageManager.loadMessages();
        this.user = user;
    }

    public boolean createMessage(String content)
    {
        if (getMessageByContent(content) != null) return false;
        int nextId = generateNextEpicId();
        Message message = new Message(content, user, nextId);
        messages.add(message);
        storageManager.saveMessages(messages);
        currentMessage = message;
        return true;
    }

    private int generateNextEpicId()
    {
        int maxId = 0;
        for (Message message : messages)
        {
            if (message.getId() > maxId)
            {
                maxId = message.getId();
            }
        }
        return maxId + 1;
    }

    private Message getMessageByContent(String content)
    {
        for (Message message : messages) {
            if (message.getInhoud().equalsIgnoreCase(content))
            {
                return message;
            }
        }
        return null;
    }

    public Message getCurrentMessage() {
        return currentMessage;
    }

    public String getCurrentMessageContent() {
        return currentMessage != null ? currentMessage.getInhoud() : null;
    }
}
