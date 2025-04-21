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
public class EpicMenuTest {

    private EpicMenu epicMenu;

    @Mock
    private CLIHandler cliHandler;
    @Mock
    private EpicController epicController;
    @Mock
    private MainMenu mainMenu;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(cliHandler.getEpicController()).thenReturn(epicController);
        when(cliHandler.getMainMenu()).thenReturn(mainMenu);
        when(cliHandler.isRunning()).thenReturn(true).thenReturn(false); // Simulate running state

        epicMenu = new EpicMenu(cliHandler);
    }

    @Test
    public void testDisplayMenu_AddEpic() {
        simulateUserInput("1\nEpicName\n\n");

        epicMenu.displayMenu();

        verify(epicController).createEpic("EpicName");
        verify(mainMenu).displayMenu();
    }

    @Test
    public void testDisplayMenu_ListEpics() {
        List<Epic> epics = new ArrayList<>();
        epics.add(new Epic(1, "Epic1", new ArrayList<>()));
        epics.add(new Epic(2, "Epic2", new ArrayList<>()));
        when(epicController.getEpics()).thenReturn((ArrayList<Epic>) epics);
        simulateUserInput("2\n");

        epicMenu.displayMenu();

        // Verify the output (you might need to capture the console output)
    }

    @Test
    public void testDisplayMenu_DeleteEpic() {
        List<Epic> epics = new ArrayList<>();
        epics.add(new Epic(1, "Epic1", new ArrayList<>()));
        when(epicController.getEpics()).thenReturn((ArrayList<Epic>) epics);
        simulateUserInput("3\n1\n");

        epicMenu.displayMenu();

        verify(epicController).deleteEpic(epics.get(0));
    }

    @Test
    public void testDisplayMenu_BackToMainMenu() {
        simulateUserInput("4\n");

        epicMenu.displayMenu();

        verify(mainMenu).displayMenu();
    }

    private void simulateUserInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }
}


