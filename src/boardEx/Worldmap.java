package boardEx;

public class Worldmap {
	private map[] mapArr;
	
	public Worldmap() {
		super();
		mapArr = new map[28];
		
		
		// City(int cityName, int cityPrice, int cityfee)
		// map(String mapName, int eventfee)
		// map(String mapName, int eventfee, int price)
		mapArr [0] = new map ("출발점",200_000);
		mapArr [1] = new City("방콕", 100_000,50_000);
		mapArr [2] = new map("사회복지처",0,150_000);
		mapArr [3] = new City("베이징",170_000 , 85_000);
		mapArr [4] = new City("제주도",300_000 ,150_000);
		mapArr [5] = new City("타이페이",120_000 ,60_000);
		mapArr [6] = new City("두바이",150_000 ,75_000);
		mapArr [7] = new map("무인도");
		mapArr [8] = new City("도쿄",300_000 ,150_000);
		mapArr [9] = new City("시드니",250_000 ,125_000);
		mapArr [10] = new map("보너스",100_000);
		mapArr [11] = new City("퀘벡",180_000 ,90_000);
		mapArr [12] = new City("하와이",250_000 ,125_000);
		mapArr [13] = new City("상파울로",190_000 ,95_000);
		mapArr [14] = new map("사회복지기금",0,500_000);
		mapArr [15] = new City("프라하",150_000 ,75_000);
		mapArr [16] = new City("푸켓",150_000 ,75_000);
		mapArr [17] = new City("베를린",200_000 ,100_000);
		mapArr [18] = new City("콩코드여객기",200_000,200_000);
		mapArr [19] = new City("모스크바",250_000,125_000);
		mapArr [20] = new City("로마",300_000 ,150_000);
		mapArr [21] = new map("세계여행",0,50_000);
		mapArr [22] = new City("타히티",150_000 ,750_000);
		mapArr [23] = new City("런던",450_000 ,225_000);
		mapArr [24] = new City("파리",500_000 ,250000);
		mapArr [25] = new map("국세청",0,200_000);
		mapArr [26] = new City("뉴욕",600_000 ,300_000);
		mapArr [27] = new City("서울",800_000 ,400_000);
		
	}
	public map[] getMapArr() {
		return mapArr;
		
	}
	
}
