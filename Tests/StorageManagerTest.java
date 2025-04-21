import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.teamflow.cli.*;
import org.teamflow.controllers.*;
import org.teamflow.models.*;
import org.teamflow.storage.*;
import org.teamflow.utils.*;

import java.util.ArrayList;

public class StorageManagerTest {

    private StorageManager storageManager;

    @Mock
    private JSONHelper jsonHelper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        storageManager = new StorageManager();
    }

    @Test
    public void testLoadUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe"));
        when(jsonHelper.loadUsers()).thenReturn(users);

        ArrayList<User> loadedUsers = storageManager.loadUsers();
        assertEquals(1, loadedUsers.size());
        assertEquals("John Doe", loadedUsers.get(0).getName());
    }

    @Test
    public void testSaveUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe"));

        storageManager.saveUsers(users);
        verify(jsonHelper).saveUsers(users);
    }

    @Test
    public void testLoadEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        epics.add(new Epic(1, "Epic 1", new ArrayList<>()));
        when(jsonHelper.loadEpics()).thenReturn(epics);

        ArrayList<Epic> loadedEpics = storageManager.loadEpics();
        assertEquals(1, loadedEpics.size());
        assertEquals("Epic 1", loadedEpics.get(0).getTitel());
    }

    @Test
    public void testSaveEpics() {
        ArrayList<Epic> epics = new ArrayList<>();
        epics.add(new Epic(1, "Epic 1", new ArrayList<>()));

        storageManager.saveEpics(epics);
        verify(jsonHelper).saveEpics(epics);
    }

    @Test
    public void testLoadMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Message 1", new User(1, "John Doe"), 1));
        when(jsonHelper.loadMessages()).thenReturn(messages);

        ArrayList<Message> loadedMessages = storageManager.loadMessages();
        assertEquals(1, loadedMessages.size());
        assertEquals("Message 1", loadedMessages.get(0).getInhoud());
    }

    @Test
    public void testSaveMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Message 1", new User(1, "John Doe"), 1));

        storageManager.saveMessages(messages);
        verify(jsonHelper).saveMessages(messages);
    }

    @Test
    public void testLoadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", "Task Content", 100));
        when(jsonHelper.loadTasks()).thenReturn(tasks);

        ArrayList<Task> loadedTasks = storageManager.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertEquals("Task 1", loadedTasks.get(0).getTitle());
    }

    @Test
    public void testSaveTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", "Task Content", 100));

        storageManager.saveTasks(tasks);
        verify(jsonHelper).saveTasks(tasks);
    }

    @Test
    public void testLoadUserStories() {
        ArrayList<UserStory> userStories = new ArrayList<>();
        userStories.add(new UserStory("User Story 1", 1, new ArrayList<>()));
        when(jsonHelper.loadUserStories()).thenReturn(userStories);

        ArrayList<UserStory> loadedUserStories = storageManager.loadUserStories();
        assertEquals(1, loadedUserStories.size());
        assertEquals("User Story 1", loadedUserStories.get(0).getTitel());
    }

    @Test
    public void testSaveUserStories() {
        ArrayList<UserStory> userStories = new ArrayList<>();
        userStories.add(new UserStory("User Story 1", 1, new ArrayList<>()));

        storageManager.saveUserStories(userStories);
        verify(jsonHelper).saveUserStories(userStories);
    }
}

