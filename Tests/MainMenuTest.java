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

public class MainMenuTest {

    private MainMenu mainMenu;

    @Mock
    private CLIHandler cliHandler;
    @Mock
    private UserController userController;
    @Mock
    private User user;
    @Mock
    private EpicMenu epicMenu;
    @Mock
    private UserStoryMenu userStoryMenu;
    @Mock
    private TaskMenu taskMenu;
    @Mock
    private MessageMenu messageMenu;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(cliHandler.getUserController()).thenReturn(userController);
        when(userController.getCurrentUser()).thenReturn(user);
        when(cliHandler.getEpicMenu()).thenReturn(epicMenu);
        when(cliHandler.getUserStoryMenu()).thenReturn(userStoryMenu);
        when(cliHandler.getTaskMenu()).thenReturn(taskMenu);
        when(cliHandler.getMessageMenu()).thenReturn(messageMenu);
        when(cliHandler.isRunning()).thenReturn(true).thenReturn(false); // Simulate running state

        mainMenu = new MainMenu(cliHandler);
    }

    @Test
    public void testDisplayMenu_ScrumMaster() {
        when(user.getIsScrumMaster()).thenReturn(true);
        simulateUserInput("10\n");

        mainMenu.displayMenu();

        verify(cliHandler).setRunning(false);
    }

    @Test
    public void testDisplayMenu_NonScrumMaster() {
        when(user.getIsScrumMaster()).thenReturn(false);
        simulateUserInput("10\n");

        mainMenu.displayMenu();

        verify(cliHandler).setRunning(false);
    }

    @Test
    public void testDisplayMenu_EpicMenu() {
        simulateUserInput("1\n10\n");

        mainMenu.displayMenu();

        verify(epicMenu).displayMenu();
        verify(cliHandler).setRunning(false);
    }

    @Test
    public void testDisplayMenu_UserStoryMenu() {
        simulateUserInput("2\n10\n");

        mainMenu.displayMenu();

        verify(userStoryMenu).displayMenu();
        verify(cliHandler).setRunning(false);
    }

    @Test
    public void testDisplayMenu_TaskMenu() {
        simulateUserInput("3\n10\n");

        mainMenu.displayMenu();

        verify(taskMenu).displayMenu();
        verify(cliHandler).setRunning(false);
    }

    @Test
    public void testDisplayMenu_MessageMenu() {
        simulateUserInput("4\n10\n");

        mainMenu.displayMenu();

        verify(messageMenu).displayMenu();
        verify(cliHandler).setRunning(false);
    }

    private void simulateUserInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }
}

