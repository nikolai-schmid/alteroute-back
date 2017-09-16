package alteroute;

public class User {
	private int id;
	private String username;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.username + "#" + this.id;
	}
}
