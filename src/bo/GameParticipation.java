package bo;

public class GameParticipation {
	private int id;
	private int userId;
	private int gameId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	@Override
	public String toString() {
		return "GameParticipation#" + this.id;
	}
}
