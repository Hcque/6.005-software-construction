import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * These tests are provided for testing client connection and disconnection, 
 * and nickname changes. You can and should use these tests as a model for 
 * your own testing, but write your tests in ServerModelTest.java.
 *
 * Note that "assert" commands used for testing can have a String as 
 * their last argument, denoting what the "assert" command is testing.
 * 
 * Remember to check:
 * https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html 
 * for assert method documention!
 */
public class ConnectionNicknamesTest2 {
    private ServerModel model;

    /**
     * Before each test, we initialize model to be 
     * a new ServerModel (with all new, empty state)
     */
    @Before
    public void setUp() {
        model = new ServerModel();
    }
    
    @Test
    public void testEmptyOnInit() {
        assertTrue(model.getRegisteredUsers().isEmpty());
    }

    // The questions to ask when writing a test for ServerModel:
    //     1. does the method return what is expected?
    //     2. was the state updated as expected?
    @Test
    public void testRegisterSingleUser() {
        // Recall: registerUser returns a 'connected' Broadcast with the nickname of the registered
        // user! We expect the nickname to be "User0"
        Broadcast expected = Broadcast.connected("User0");

        // test: does model.registerUser(0) return what we expect?
        assertEquals(expected, model.registerUser(0));

        // Recall: getRegisteredUsers returns a Collection of the nicknames of all registered users!
        // We expect this collection to have one element, which is "User0"
        Collection<String> registeredUsers = model.getRegisteredUsers();

        // test: does the collection contain only one element?
        assertEquals(1, registeredUsers.size());

        // test: does the collection contain the nickname "User0"
        assertTrue(registeredUsers.contains("User0"));
    }

    @Test
    public void testRegisterMultipleUsers() {
        Broadcast expected0 = Broadcast.connected("User0");
        assertEquals(expected0, model.registerUser(0));
        Broadcast expected1 = Broadcast.connected("User1");
        assertEquals(expected1, model.registerUser(1));
        Broadcast expected2 = Broadcast.connected("User2");
        assertEquals(expected2, model.registerUser(2));

        Collection<String> registeredUsers = model.getRegisteredUsers();
        assertEquals(3, registeredUsers.size());
        assertTrue(registeredUsers.contains("User0"));
        assertTrue(registeredUsers.contains("User1"));
        assertTrue(registeredUsers.contains("User2"));
    }

    @Test
    public void testDeregisterSingleUser() {
        model.registerUser(0);
        model.deregisterUser(0);
        assertTrue(model.getRegisteredUsers().isEmpty());
    }

    @Test
    public void testDeregisterOneOfManyUsers() {
        model.registerUser(0);
        model.registerUser(1);
        model.deregisterUser(0);
        assertFalse(model.getRegisteredUsers().isEmpty());
        assertFalse(model.getRegisteredUsers().contains("User0"));
        assertTrue(model.getRegisteredUsers().contains("User1"));
    }

    @Test
    public void testNickNotInChannels() {
        model.registerUser(0);
        Command command = new NicknameCommand(0, "User0", "cis120");
        Set<String> recipients = Collections.singleton("cis120");
        Broadcast expected = Broadcast.okay(command, recipients);
        assertEquals(expected, command.updateServerModel(model));
        Collection<String> users = model.getRegisteredUsers();
        assertFalse(users.contains("User0"));
        assertTrue(users.contains("cis120"));
    }

    @Test
    public void testNickCollision() {
        model.registerUser(0);
        model.registerUser(1);
        Command command = new NicknameCommand(0, "User0", "User1");
        Broadcast expected = Broadcast.error(command, ServerResponse.NAME_ALREADY_IN_USE);
        assertEquals(expected, command.updateServerModel(model));
        Collection<String> users = model.getRegisteredUsers();
        assertTrue(users.contains("User0"));
        assertTrue(users.contains("User1"));
    }

    @Test
    public void testNickCollisionOnConnect() {
        model.registerUser(0);
        Command command = new NicknameCommand(0, "User0", "User1");
        command.updateServerModel(model);
        Broadcast expected = Broadcast.connected("User0");
        assertEquals(expected, model.registerUser(1));
        Collection<String> users = model.getRegisteredUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains("User0"));
        assertTrue(users.contains("User1"));
        assertEquals(0, model.getUserId("User1"));
        assertEquals(1, model.getUserId("User0"));
    }

}

