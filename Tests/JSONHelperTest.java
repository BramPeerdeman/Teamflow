import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamflow.cli.*;
import org.teamflow.controllers.*;
import org.teamflow.models.*;
import org.teamflow.storage.*;
import org.teamflow.utils.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONHelperTest {

    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("test", ".json");
        tempFile.deleteOnExit();
    }

    @Test
    public void testLoadUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe"));
        try (FileWriter writer = new FileWriter(tempFile)) {
            new Gson().toJson(users, writer);
        }

        ArrayList<User> loadedUsers = JSONHelper.loadUsers();
        assertEquals(1, loadedUsers.size());
        assertEquals("John Doe", loadedUsers.get(0).getName());
    }

    @Test
    public void testSaveUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe"));

        JSONHelper.saveUsers(users);

        ArrayList<User> loadedUsers = JSONHelper.loadUsers();
        assertEquals(1, loadedUsers.size());
        assertEquals("John Doe", loadedUsers.get(0).getName());
    }

    @Test
    public void testLoadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", "Task Content", 100));
        try (FileWriter writer = new FileWriter(tempFile)) {
            new Gson().toJson(tasks, writer);
        }

        ArrayList<Task> loadedTasks = JSONHelper.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertEquals("Task 1", loadedTasks.get(0).getTitle());
    }

    @Test
    public void testSaveTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", "Task Content", 100));

        JSONHelper.saveTasks(tasks);

        ArrayList<Task> loadedTasks = JSONHelper.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertEquals("Task 1", loadedTasks.get(0).getTitle());
    }

    @Test
    public void testLoadEpics() throws IOException {
        ArrayList<Epic> epics = new ArrayList<>();
        epics.add(new Epic(1, "Epic 1", new ArrayList<>()));
        try (FileWriter writer = new FileWriter(tempFile)) {
            new Gson().toJson(epics, writer);
        }

        ArrayList<Epic> loadedEpics = JSONHelper.loadEpics();
        assertEquals(1, loadedEpics.size());
        assertEquals("Epic 1", loadedEpics.get(0).getTitel());
    }

    @Test
    public void testSaveEpics() throws IOException {
        ArrayList<Epic> epics = new ArrayList<>();
        epics.add(new Epic(1, "Epic 1", new ArrayList<>()));

        JSONHelper.saveEpics(epics);

        ArrayList<Epic> loadedEpics = JSONHelper.loadEpics();
        assertEquals(1, loadedEpics.size());
        assertEquals("Epic 1", loadedEpics.get(0).getTitel());
    }

    @Test
    public void testLoadMessages() throws IOException {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Message 1", new User(1, "John Doe"), 1));
        try (FileWriter writer = new FileWriter(tempFile)) {
            new Gson().toJson(messages, writer);
        }

        ArrayList<Message> loadedMessages = JSONHelper.loadMessages();
        assertEquals(1, loadedMessages.size());
        assertEquals("Message 1", loadedMessages.get(0).getInhoud());
    }

    @Test
    public void testSaveMessages() throws IOException {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Message 1", new User(1, "John Doe"), 1));

        JSONHelper.saveMessages(messages);

        ArrayList<Message> loadedMessages = JSONHelper.loadMessages();
        assertEquals(1, loadedMessages.size());
        assertEquals("Message 1", loadedMessages.get(0).getInhoud());
    }

    @Test
    public void testLoadUserStories() throws IOException {
        ArrayList<UserStory> userStories = new ArrayList<>();
        userStories.add(new UserStory("User Story 1", 1, new ArrayList<>()));
        try (FileWriter writer = new FileWriter(tempFile)) {
            new Gson().toJson(userStories, writer);
        }

        ArrayList<UserStory> loadedUserStories = JSONHelper.loadUserStories();
        assertEquals(1, loadedUserStories.size());
        assertEquals("User Story 1", loadedUserStories.get(0).getTitel());
    }

    @Test
    public void testSaveUserStories() throws IOException {
        ArrayList<UserStory> userStories = new ArrayList<>();
        userStories.add(new UserStory("User Story 1", 1, new ArrayList<>()));

        JSONHelper.saveUserStories(userStories);

        ArrayList<UserStory> loadedUserStories = JSONHelper.loadUserStories();
        assertEquals(1, loadedUserStories.size());
        assertEquals("User Story 1", loadedUserStories.get(0).getTitel());
    }
}
