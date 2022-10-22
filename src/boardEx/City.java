package boardEx;


public class City extends map{
	private String cityName;	//시티명
	private int cityPrice;		//시티 가격
	private int cityfee;	//통행료		
	private User ownUser = null;
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(String cityName, int cityPrice, int cityfee) {
		super("City");
		this.cityName = cityName;
		this.cityPrice = cityPrice;
		this.cityfee = cityfee;
	
	}

	public User getOwnUser() {
		return ownUser;
	}

	public void setOwnUser(User ownUser) {
		this.ownUser = ownUser;
	}

	public void setBuyCity(int pmoney){
		
		
	}
	public boolean buyCity1(User user){
		if(user.getMoney() >= getCityPrice() ){
//			System.out.println(player.getPmoney());
			user.setMoney(user.getMoney()-getCityPrice());
			this.ownUser = user; //소유권 change
			
			return true;
		}
		return false; 	//돈이 부족할때
	}
	
	
	//통행료 지불하자
	public boolean passMoney1(User user){
		if((this.ownUser != user) && (this.ownUser != null) ){	//내가 주인이 아니고 빈 자리이면
			user.setMoney(user.getMoney() - (this.cityfee)); //통행료 지불
			System.out.println("\t"+this.ownUser.getId()+"님에게" +this.cityfee +"(을)를 지불합니다");
			System.out.println("\t 나의 정보 : ("+user+")");
			
			return true;
		}
		return false;
	}
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityPrice() {
		return cityPrice;
	}

	public void setCityPrice(int cityPrice) {
		this.cityPrice = cityPrice;
	}

	public int getCityfee() {
		return cityfee;
	}

	public void setCityfee(int cityfee) {
		this.cityfee = cityfee;
	}

	
	@Override
	public String toString() {
		return cityName + "\n도시 가격: "+cityPrice+"원, 통행료: "+cityfee+"원";
	}

	
}
