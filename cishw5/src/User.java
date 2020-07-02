import java.util.Set;
import java.util.TreeSet;

public class User implements Comparable<User>{
	private int id;
	private String nickname;
	private Set<Channel> channels;
	
	public User(int id, String nickname) {
		this.id = id;
		this.nickname = nickname;
		channels = new TreeSet<>();
	}
	public int getId() { return id; }
	public String getNickName() { return nickname; }
	public Set<Channel> getChannels() { return channels; }
	public Set<String> getChannelNames() {
		Set<String> chNames = new TreeSet<>();
		for (Channel ch: channels) {
			chNames.add(ch.getName());
		}
		return chNames;
	}
	
	public void changeNickname(String newName) {
		nickname = newName;
	}
	public void addChannel(Channel ch) {
		channels.add(ch);
	}
	public void kickOneChannel(Channel ch) {
		channels.remove(ch);
	}
	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.getId() - o.getId();
	}

}
