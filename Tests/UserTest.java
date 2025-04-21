import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamflow.cli.*;
import org.teamflow.controllers.*;
import org.teamflow.models.*;
import org.teamflow.storage.*;
import org.teamflow.utils.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1, "John Doe");
    }

    @Test
    public void testGetId() {
        assertEquals(1, user.getId());
    }

    @Test
    public void testSetId() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testSetName() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    public void testGetIsScrumMaster() {
        user.setIsScrumMaster(true);
        assertTrue(user.getIsScrumMaster());
    }

    @Test
    public void testSetIsScrumMaster() {
        user.setIsScrumMaster(false);
        assertFalse(user.getIsScrumMaster());
    }

    @Test
    public void testDefaultConstructor() {
        User defaultUser = new User();
        assertNull(defaultUser.getName());
        assertNull(defaultUser.getIsScrumMaster());
    }
}

