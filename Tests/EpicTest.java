import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamflow.cli.*;
import org.teamflow.controllers.*;
import org.teamflow.models.*;
import org.teamflow.storage.*;
import org.teamflow.utils.*;

import java.util.ArrayList;

public class EpicTest {

    private Epic epic;
    private UserStory userStory1;
    private UserStory userStory2;

    @BeforeEach
    public void setUp() {
        userStory1 = new UserStory("User Story 1", 1,new ArrayList<>());
        userStory2 = new UserStory("User Story 2",2, new ArrayList<>());
        ArrayList<UserStory> userStories = new ArrayList<>();
        userStories.add(userStory1);
        epic = new Epic(1, "Epic Title", userStories);
    }

    @Test
    public void testGetId() {
        assertEquals(1, epic.getId());
    }

    @Test
    public void testSetId() {
        epic.setId(2);
        assertEquals(2, epic.getId());
    }

    @Test
    public void testGetTitel() {
        assertEquals("Epic Title", epic.getTitel());
    }

    @Test
    public void testSetTitel() {
        epic.setTitel("New Epic Title");
        assertEquals("New Epic Title", epic.getTitel());
    }

    @Test
    public void testGetUserStories() {
        assertEquals(1, epic.getUserStories().size());
        assertEquals(userStory1, epic.getUserStories().get(0));
    }

    @Test
    public void testVoegUserStoryToe() {
        epic.voegUserStoryToe(userStory2);
        assertEquals(2, epic.getUserStories().size());
        assertTrue(epic.getUserStories().contains(userStory2));
    }

    @Test
    public void testVerwijderUserStory() {
        epic.verwijderUserStory(userStory1);
        assertEquals(0, epic.getUserStories().size());
        assertFalse(epic.getUserStories().contains(userStory1));
    }
}
