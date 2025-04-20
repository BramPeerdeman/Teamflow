import static org.junit.jupiter.api.Assertions.*;
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MessageTest {

    private Message message;

    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(user.getName()).thenReturn("John Doe");
        message = new Message("Hello, World!", user, 1);
    }

    @Test
    public void testGetInhoud() {
        assertEquals("Hello, World!", message.getInhoud());
    }

    @Test
    public void testSetInhoud() {
        message.setInhoud("New Content");
        assertEquals("New Content", message.getInhoud());
    }

    @Test
    public void testGetAfzender() {
        assertEquals(user, message.getAfzender());
    }

    @Test
    public void testSetAfzender() {
        User newUser = new User(2, "Jane Doe");
        message.setAfzender(newUser);
        assertEquals(newUser, message.getAfzender());
    }

    @Test
    public void testGetId() {
        assertEquals(1, message.getId());
    }

    @Test
    public void testSetId() {
        message.setId(2);
        assertEquals(2, message.getId());
    }

    @Test
    public void testToonBericht() {
        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        message.toonBericht();

        assertEquals("Hello, World!\n -John Doe", outContent.toString().trim());

        // Reset the standard output
        System.setOut(System.out);
    }
}
