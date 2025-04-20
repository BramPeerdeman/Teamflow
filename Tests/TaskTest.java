import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamflow.cli.*;
import org.teamflow.controllers.*;
import org.teamflow.models.*;
import org.teamflow.storage.*;
import org.teamflow.utils.*;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task(1, "Task Title", "Task Content", 100);
    }

    @Test
    public void testGetId() {
        assertEquals(1, task.getId());
    }

    @Test
    public void testSetId() {
        task.setId(2);
        assertEquals(2, task.getId());
    }

    @Test
    public void testGetTitle() {
        assertEquals("Task Title", task.getTitle());
    }

    @Test
    public void testSetTitle() {
        task.setTitle("New Task Title");
        assertEquals("New Task Title", task.getTitle());
    }

    @Test
    public void testGetContent() {
        assertEquals("Task Content", task.getContent());
    }

    @Test
    public void testSetContent() {
        task.setContent("New Task Content");
        assertEquals("New Task Content", task.getContent());
    }

    @Test
    public void testIsPinned() {
        assertFalse(task.isPinned());
    }

    @Test
    public void testSetPinned() {
        task.setPinned(true);
        assertTrue(task.isPinned());
    }

    @Test
    public void testPinTask() {
        task.pinTask();
        assertTrue(task.isPinned());
    }

    @Test
    public void testUnpinTask() {
        task.pinTask();
        task.unpinTask();
        assertFalse(task.isPinned());
    }

    @Test
    public void testGetAssignedUserId() {
        assertEquals(100, task.getAssignedUserId());
    }

    @Test
    public void testSetAssignedUserId() {
        task.setAssignedUserId(200);
        assertEquals(200, task.getAssignedUserId());
    }

    @Test
    public void testDefaultConstructor() {
        Task defaultTask = new Task();
        assertNull(defaultTask.getTitle());
        assertNull(defaultTask.getContent());
        assertEquals(0, defaultTask.getAssignedUserId());
    }
}
