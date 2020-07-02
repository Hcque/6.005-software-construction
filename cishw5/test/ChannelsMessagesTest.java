import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * These tests are provided for testing the server's handling of 
 * channels and messages. You can and should use these tests as a model 
 * for your own testing, but write your tests in ServerModelTest.java.
 *
 * Note that "assert" commands used for testing can have a String as 
 * their last argument, denoting what the "assert" command is testing.
 * 
 * Remember to check:
 * https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html 
 * for assert method documention!
 */
public class ChannelsMessagesTest {
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
    public void testCreateNewChannel() {
        model.registerUser(0);
        Command create = new CreateCommand(0, "User0", "java", false);
        Broadcast expected = 
            Broadcast.okay(create, Collections.singleton("User0"));
        assertEquals(expected, create.updateServerModel(model));

        assertTrue(model.getChannels().contains("java"));
        assertTrue(
            model.getUsersInChannel("java").contains("User0")
        );
        assertEquals("User0", model.getOwner("java"));
    }

    @Test
    public void testJoinChannelExistsNotMember() {
        model.registerUser(0);
        model.registerUser(1);
        Command create = new CreateCommand(0, "User0", "java", false);
        create.updateServerModel(model);

        Command join = new JoinCommand(1, "User1", "java");
        Set<String> recipients = new TreeSet<>();
        recipients.add("User1");
        recipients.add("User0");
        Broadcast expected = Broadcast.names(join, recipients, "User0");
        //System.out.println(expected);
        //System.out.println(join.updateServerModel(model));
        assertEquals(expected, join.updateServerModel(model));

        assertTrue(
            model.getUsersInChannel("java").contains("User0")
        );
        assertTrue(
            model.getUsersInChannel("java").contains("User1")
        );
        assertEquals(
            2, model.getUsersInChannel("java").size()
        );
    }

    @Test
    public void testNickBroadcastsToChannelWhereMember() {
        model.registerUser(0);
        model.registerUser(1);
        Command create = new CreateCommand(0, "User0", "java", false);
        create.updateServerModel(model);
        Command join = new JoinCommand(1, "User1", "java");
        join.updateServerModel(model);

        Command nick = new NicknameCommand(0, "User0", "Duke");
        Set<String> recipients = new TreeSet<>();
        recipients.add("User1");
        recipients.add("Duke");
        Broadcast expected = Broadcast.okay(nick, recipients);
        //System.out.println(nick.updateServerModel(model));
        assertEquals(expected, nick.updateServerModel(model));
        
        //System.out.println(model.getUsersInChannel("java"));
        assertFalse(
            model.getUsersInChannel("java").contains("User0")
        );
        assertTrue(
            model.getUsersInChannel("java").contains("Duke")
        );
        assertTrue(
            model.getUsersInChannel("java").contains("User1")
        );
    }

    @Test
    public void testLeaveChannelExistsMember() {
        model.registerUser(0);
        model.registerUser(1);
        Command create = new CreateCommand(0, "User0", "java", false);
        create.updateServerModel(model);
        Command join = new JoinCommand(1, "User1", "java");
        join.updateServerModel(model);

        Command leave = new LeaveCommand(1, "User1", "java");
        Set<String> recipients = new TreeSet<>();
        recipients.add("User1");
        recipients.add("User0");
        Broadcast expected = Broadcast.okay(leave, recipients);
        //System.out.println(expected);
        //System.out.println(leave.updateServerModel(model));
        assertEquals(expected, leave.updateServerModel(model));
        
        assertTrue(
            model.getUsersInChannel("java").contains("User0")
        );
        assertFalse(
            model.getUsersInChannel("java").contains("User1")
        );
        assertEquals(
            1, model.getUsersInChannel("java").size()
        );
    }

    @Test
    public void testDeregisterSendsDisconnectedWhereMember() {
        model.registerUser(0);
        model.registerUser(1);
        Command create = new CreateCommand(0, "User0", "java", false);
        create.updateServerModel(model);
        Command join = new JoinCommand(1, "User1", "java");
        join.updateServerModel(model);

        Broadcast expected = 
            Broadcast.disconnected("User1", Collections.singleton("User0"));
        //System.out.println(expected);
        //System.out.println(model.deregisterUser(1));
        assertEquals(expected, model.deregisterUser(1));

        assertTrue(
            model.getChannels().contains("java")
        );
        assertEquals(
            1, model.getUsersInChannel("java").size()
        );
        assertTrue(
            model.getUsersInChannel("java").contains("User0")
        );
    }

    @Test
    public void testMesgChannelExistsMember() {
        model.registerUser(0);
        model.registerUser(1);
        Command create = new CreateCommand(0, "User0", "java", false);
        create.updateServerModel(model);
        Command join = new JoinCommand(1, "User1", "java");
        join.updateServerModel(model);

        Command mesg = 
            new MessageCommand(0, "User0", "java", "hey whats up hello");
        Set<String> recipients = new TreeSet<>();
        recipients.add("User1");
        recipients.add("User0");
        Broadcast expected = Broadcast.okay(mesg, recipients);
        //System.out.println(expected);
        //System.out.println(mesg.updateServerModel(model));
        assertEquals(expected, mesg.updateServerModel(model));
    }

}

