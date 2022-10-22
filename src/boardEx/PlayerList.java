package boardEx;

public class PlayerList {
	private User[] playerList;
	
	public PlayerList() {
		super();
		playerList = new User[2];
		
		playerList[0] = new User ("user1",0);
		playerList[1] = new User ("user2",1);
	}
	
	public User getplayerList(int k) {
		return playerList[k];
		
	}

}
