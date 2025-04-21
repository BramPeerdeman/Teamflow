import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamflow.cli.*;
import org.teamflow.controllers.*;
import org.teamflow.models.*;
import org.teamflow.storage.*;
import org.teamflow.utils.*;

import java.util.ArrayList;

public class UserStoryTest {

    private UserStory userStory;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        task1 = new Task(1, "Task 1", "llll", 1);
        task2 = new Task(2, "Task 2", "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoo", 1);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        userStory = new UserStory("User Story Title", 1, tasks);
    }

    @Test
    public void testGetTitel() {
        assertEquals("User Story Title", userStory.getTitel());
    }

    @Test
    public void testSetTitel() {
        userStory.setTitel("New User Story Title");
        assertEquals("New User Story Title", userStory.getTitel());
    }

    @Test
    public void testGetTaskList() {
        assertEquals(1, userStory.gettaskList().size());
        assertEquals(task1, userStory.gettaskList().get(0));
    }

    @Test
    public void testSetTaskList() {
        ArrayList<Task> newTasks = new ArrayList<>();
        newTasks.add(task2);
        userStory.setTakenLijst(newTasks);
        assertEquals(1, userStory.gettaskList().size());
        assertEquals(task2, userStory.gettaskList().get(0));
    }

    @Test
    public void testAddTask() {
        userStory.addTask(task2);
        assertEquals(2, userStory.gettaskList().size());
        assertTrue(userStory.gettaskList().contains(task2));
    }

    @Test
    public void testVerwijderTask() {
        userStory.verwijderTask(task1);
        assertEquals(0, userStory.gettaskList().size());
        assertFalse(userStory.gettaskList().contains(task1));
    }

    @Test
    public void testGetId() {
        assertEquals(1, userStory.getId());
    }

    @Test
    public void testSetId() {
        userStory.setId(2);
        assertEquals(2, userStory.getId());
    }
}

