package boardEx;

public class User {
	
	private String Id;
	private int index;
	private int money;
	private int Plocation;
	private int citynum=0;
	private int mooinum = 0;
	
	public int getCitynum() {
		return citynum;
	}

	public void setCitynum(int citynum) {
		this.citynum = citynum;
	}

	public User(){
		super();
	}
	
	public User(String Id, int index){
		this.Id = Id;
		this.index = index;
		this.money =2_000_000;
		this.Plocation = 0;
		this.mooinum = 0;
	}
	
	public int rollDice(){
		return (int)((Math.random()*6)+1);	
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getPlocation() {
		return Plocation;
	}

	public void setPlocation(int plocation) {
		if(Plocation+plocation>27){
			Plocation = (Plocation+plocation)-28;
			money += 200_000;
		}else{
			Plocation += plocation;		//다이스로 받아온 값을 추가해준다.
		}
	}
	
	public void setPlocation1(int plocation) {
		this.Plocation = plocation;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", index=" + index + ", money=" + money + ", Plocation=" + Plocation + "]";
	}

	public int getMooinum() {
		return mooinum;
	}

	public void setMooinum(int mooinum) {
		this.mooinum = mooinum;
	}

	
	

}
