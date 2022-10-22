
package boardEx;
public class map{
	String mapName;
	int eventfee;
	private User ownUser = null;
	private int price;
	
	
	public User getOwnUser() {
		return ownUser;
	}

	public void setOwnUser(User ownUser) {
		this.ownUser = ownUser;
	}

	public map(){
		
	}
	
	public map(String mapName) {
		super();
		this.mapName = mapName;
	}

	public map(String mapName, int eventfee) {
		super();
		this.mapName = mapName;
		this.eventfee = eventfee;
		
	}

	
	public map(String mapName, int eventfee, int price){
		super();
		this.mapName = mapName;
		this.eventfee = eventfee;
		this.price = price;
				
	}
	
	@Override
	public String toString() {
		return mapName + "\n이벤트 금액: " + eventfee + ", 통행료: " + price;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public int getEventfee() {
		return eventfee;
	}

	public void setEventfee(int eventfee) {
		this.eventfee = eventfee;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}

