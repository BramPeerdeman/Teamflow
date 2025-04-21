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

public class TaskControllerTest {

    private TaskController taskController;

    @Mock
    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(storageManager.loadTasks()).thenReturn(new ArrayList<>());
        taskController = new TaskController();
    }

    @Test
    public void testCreateTask() {
        Task task = taskController.createTask("Task Title", "Task Content", 100);
        assertNotNull(task);
        assertEquals("Task Title", task.getTitle());
        assertEquals("Task Content", task.getContent());
        assertEquals(100, task.getAssignedUserId());
        verify(storageManager).saveTasks((ArrayList<Task>) anyList());
    }

    @Test
    public void testGetTasksForUser() {
        Task task1 = new Task(1, "Task 1", "Content 1", 100);
        Task task2 = new Task(2, "Task 2", "Content 2", 200);
        taskController.createTask(task1.getTitle(), task1.getContent(), task1.getAssignedUserId());
        taskController.createTask(task2.getTitle(), task2.getContent(), task2.getAssignedUserId());

        ArrayList<Task> userTasks = taskController.getTasksForUser(100);
        assertEquals(1, userTasks.size());
        assertEquals("Task 1", userTasks.get(0).getTitle());
    }

    @Test
    public void testPinTask() {
        Task task = taskController.createTask("Task Title", "Task Content", 100);
        boolean result = taskController.pinTask(task.getId(), 100);
        assertTrue(result);
        assertTrue(task.isPinned());
        verify(storageManager).saveTasks((ArrayList<Task>) anyList());
    }

    @Test
    public void testUnpinTask() {
        Task task = taskController.createTask("Task Title", "Task Content", 100);
        task.pinTask();
        boolean result = taskController.unpinTask(task.getId(), 100);
        assertTrue(result);
        assertFalse(task.isPinned());
        verify(storageManager).saveTasks((ArrayList<Task>) anyList());
    }
}
