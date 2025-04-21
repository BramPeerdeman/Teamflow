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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserStoryMenuTest {

    private UserStoryMenu userStoryMenu;

    @Mock
    private CLIHandler cliHandler;
    @Mock
    private EpicController epicController;
    @Mock
    private UserStoryController userStoryController;
    @Mock
    private MainMenu mainMenu;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(cliHandler.getEpicController()).thenReturn(epicController);
        when(cliHandler.getUserStoryController()).thenReturn(userStoryController);
        when(cliHandler.getMainMenu()).thenReturn(mainMenu);
        when(cliHandler.isRunning()).thenReturn(true).thenReturn(false); // Simulate running state

        userStoryMenu = new UserStoryMenu(cliHandler);
    }

    @Test
    public void testDisplayMenu_AddUserStory() {
        List<Epic> epics = new ArrayList<>();
        epics.add(new Epic(1, "Epic1", new ArrayList<>()));
        when(epicController.getEpics()).thenReturn((ArrayList<Epic>) epics);
        when(epicController.getCurrentEpic()).thenReturn(epics.get(0));
        when(userStoryController.createUserStory("UserStory1")).thenReturn(true);
        when(userStoryController.getCurrentUserStory()).thenReturn(new UserStory("UserStory1", 1, new ArrayList<>()));
        simulateUserInput("1\nUserStory1\n\n");

        userStoryMenu.displayMenu();

        verify(userStoryController).createUserStory("UserStory1");
        verify(epicController).saveEpics();
        verify(mainMenu).displayMenu();
    }

    @Test
    public void testDisplayMenu_ListUserStories() {
        Epic epic = new Epic(1, "Epic1", new ArrayList<>());
        epic.voegUserStoryToe(new UserStory("UserStory1", 1, new ArrayList<>()));
        when(epicController.getCurrentEpic()).thenReturn(epic);
        simulateUserInput("2\n");

        userStoryMenu.displayMenu();

        // Verify the output (you might need to capture the console output)
    }

    @Test
    public void testDisplayMenu_SelectEpic() {
        List<Epic> epics = new ArrayList<>();
        epics.add(new Epic(1, "Epic1", new ArrayList<>()));
        when(epicController.getEpics()).thenReturn((ArrayList<Epic>) epics);
        simulateUserInput("3\n0\n");

        userStoryMenu.displayMenu();

        verify(epicController).setCurrentEpic(epics.get(0));
    }

    @Test
    public void testDisplayMenu_DeleteUserStory() {
        Epic epic = new Epic(1, "Epic1", new ArrayList<>());
        UserStory userStory = new UserStory("UserStory1", 1, new ArrayList<>());
        epic.voegUserStoryToe(userStory);
        when(epicController.getCurrentEpic()).thenReturn(epic);
        simulateUserInput("4\n1\nj\n");

        userStoryMenu.displayMenu();

        verify(userStoryController).saveUserStories();
        verify(epicController).saveEpics();
    }

    @Test
    public void testDisplayMenu_BackToMainMenu() {
        simulateUserInput("5\n");

        userStoryMenu.displayMenu();

        verify(mainMenu).displayMenu();
    }

    private void simulateUserInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }
}
