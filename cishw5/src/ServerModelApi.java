import java.util.Collection;

/**
 * Defines a collection of methods that should be available in the {@link ServerModel} class. These
 * methods will be used by the {@link ServerBackend}, and should therefore conform to this
 * interface.
 *
 * You do not need to modify this file.
 */
public interface ServerModelApi {

    /**
     * Informs the model that a client has connected to the server with the given user ID. The model
     * should update its state so that it can identify this user during later interactions. The
     * newly connected user will not yet have had the chance to set a nickname, and so the model
     * should provide a default nickname for the user.
     * Any user who is registered with the server (without being later deregistered) should appear
     * in the output of {@link #getRegisteredUsers()}.
     *
     * @param userId The unique ID created by the backend to represent this user
     * @return A {@link Broadcast} to the user with their new nickname
     */
    Broadcast registerUser(int userId);

    /**
     * Informs the model that the client with the given user ID has disconnected from the server.
     * After a user ID is deregistered, the server backend is free to reassign this user ID to an
     * entirely different client; as such, the model should remove all state of the user associated
     * with the deregistered user ID. The behavior of this method if the given user ID is not
     * registered with the model is undefined.
     * Any user who is deregistered (without later being registered) should not appear in the output
     * of {@link #getRegisteredUsers()}.
     *
     * @param userId The unique ID of the user to deregister
     * @return A {@link Broadcast} instructing clients to remove the user from all channels
     */
    Broadcast deregisterUser(int userId);

    /**
     * Gets the user ID currently associated with the given nickname. The returned ID is -1 if the
     * nickname is not currently in use.
     *
     * @param nickname The nickname for which to get the associated user ID
     * @return The user ID of the user with the argued nickname if such a user exists, otherwise -1
     */
    int getUserId(String nickname);

    /**
     * Gets the nickname currently associated with the given user ID. The returned nickname is
     * null if the user ID is not currently in use.
     *
     * @param userId The user ID for which to get the associated nickname
     * @return The nickname of the user with the argued user ID if such a user exists, otherwise
     *          null
     */
    String getNickname(int userId);

    /**
     * Gets a collection of the nicknames of all users who are registered with the server. Changes
     * to the returned collection should not affect the server state.
     * 
     * This method is provided for testing.
     *
     * @return The collection of registered user nicknames
     */
    Collection<String> getRegisteredUsers();

    /**
     * Gets a collection of the names of all the channels that are present on the server. Changes to
     * the returned collection should not affect the server state.
     * 
     * This method is provided for testing.
     *
     * @return The collection of channel names
     */
    Collection<String> getChannels();

    /**
     * Gets a collection of the nicknames of all the users in a given channel. The collection is
     * empty if no channel with the given name exists. Modifications to the returned collection
     * should not affect the server state.
     *
     * This method is provided for testing.
     *
     * @param channelName The channel for which to get member nicknames
     * @return The collection of user nicknames in the argued channel
     */
    Collection<String> getUsersInChannel(String channelName);

    /**
     * Gets the nickname of the owner of the given channel. The result is {@code null} if no
     * channel with the given name exists.
     *
     * This method is provided for testing.
     *
     * @param channelName The channel for which to get the owner nickname
     * @return The nickname of the channel owner if such a channel exists, othewrise null
     */
    String getOwner(String channelName);
}
