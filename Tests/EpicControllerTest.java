import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

public class EpicControllerTest {

    private EpicController epicController;

    @Mock
    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        epicController = EpicController.getInstance();
        when(storageManager.loadEpics()).thenReturn(new ArrayList<>());
    }

    @Test
    public void testSingletonInstance() {
        EpicController instance1 = EpicController.getInstance();
        EpicController instance2 = EpicController.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testCreateEpic() {
        boolean created = epicController.createEpic("Epic1");
        assertTrue(created);
        assertEquals(1, epicController.getEpics().size());
        assertEquals("Epic1", epicController.getEpics().get(0).getTitel());
    }

    @Test
    public void testCreateEpic_DuplicateName() {
        epicController.createEpic("Epic1");
        boolean created = epicController.createEpic("Epic1");
        assertFalse(created);
        assertEquals(1, epicController.getEpics().size());
    }

    @Test
    public void testDeleteEpic() {
        epicController.createEpic("Epic1");
        Epic epic = epicController.getEpics().get(0);
        epicController.deleteEpic(epic);
        assertEquals(0, epicController.getEpics().size());
    }

    @Test
    public void testGetCurrentEpic() {
        epicController.createEpic("Epic1");
        Epic epic = epicController.getEpics().get(0);
        epicController.setCurrentEpic(epic);
        assertEquals(epic, epicController.getCurrentEpic());
    }

    @Test
    public void testGetCurrentEpicName() {
        epicController.createEpic("Epic1");
        Epic epic = epicController.getEpics().get(0);
        epicController.setCurrentEpic(epic);
        assertEquals("Epic1", epicController.getCurrentEpicName());
    }

    @Test
    public void testSaveEpics() {
        epicController.createEpic("Epic1");
        epicController.saveEpics();
        verify(storageManager).saveEpics(epicController.getEpics());
    }
}

