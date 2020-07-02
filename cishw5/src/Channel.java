import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Channel implements Comparable<Channel>{
	private String name;
	private User owner;
	private Set<User> users;
	private boolean inviteOnly;
	
	public Channel(String name, User owner, boolean inviteOnly) {
		this.name = name;
		this.owner = owner;
		this.users = new TreeSet<>();
		users.add(owner);
		owner.addChannel(this);
		this.inviteOnly = inviteOnly;
		
	}
	public String getName() { return name; }
	public User getOwner() { return owner; }
	public Set<User> getUsersInChannel() { 
		return users;
	}

	public Set<String> getUserNamesInChannel() { 
		Set<String> s = new TreeSet<>();
		for (User uu: users) {
			s.add(uu.getNickName());
		}
		return s;
	}
	public Set<Integer> getUserIdInChannel() { 
		Set<Integer> s = new TreeSet<>();
		for (User uu: users) {
			s.add(uu.getId());
		}
		return s;
	}

	public boolean isInvitedOnly() { return inviteOnly; }
	public void addUser(User u) {
		users.add(u);
	}
	public void kickOneUser(User u) {
		users.remove(u);
	}
	public void kickOneUser(int u) {
		User find = null;
		for (User uu: users) {
			if (uu.getId() == u) {
				find = uu;
				break;
			}
		}
		try {
			users.remove(find);
		} catch (NullPointerException e) {
		}
	}
	@Override
	public int compareTo(Channel o) {
		// TODO Auto-generated method stub
		return this.hashCode() - o.hashCode();
	}
	
	
}
