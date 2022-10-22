package boardEx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardController implements Initializable{
	
	private BooleanProperty btnOk = new SimpleBooleanProperty(false);
    private BooleanProperty btnRollDice = new SimpleBooleanProperty(true);
    private BooleanProperty RollDice = new SimpleBooleanProperty(true);
    private BooleanProperty Doublemsg = new SimpleBooleanProperty(true);
    
    //=====================================================
    @FXML	//트와이스 라벨
    private Label zzwi,jihyo,dahyun,junghyun,mina,sana,nayoun,chayoun,momo;
    //=====================================================
    
    @FXML
    private List<Label> member;
    
    @FXML
    private Label m;
   
    @FXML
    private Label user1,user2;
    
    @FXML
    private Label user1_money ,user2_money;
    
    @FXML
    private Button btn_rollDice;
    
    @FXML
    private Button btn_ok ,btn_cancel;

    @FXML
    private Label lbl_dice1;

    @FXML
    private Label lbl_dice2;

    @FXML
    private Circle user1_circle;
    
    @FXML
    private Circle user2_circle;
    
    @FXML
    private List<Label> cityList;
    
    @FXML
    private Label nowPlayer;
    
    @FXML
    private ImageView image;
    
    @FXML
    private ImageView image2;
    
    @FXML
    private Label doublemsg;
    
//    @FXML
//    private List<Label> cityList_bar;
    
    @FXML
    private ImageView img1;
    
    @FXML
    void rolldice(ActionEvent event) {
    	btnRollDice.set(true);
    }
    @FXML
    void ok(ActionEvent event) {
    	btnOk.set(false);
    }
    @FXML
    void cancel(ActionEvent event) {
    	btnOk.set(false);
    }
    @FXML
    private Label turnInfo;
    
    @FXML
    private Label cityInfo;
    
    
    PlayerList playerList = new PlayerList();
    MapLayoutToken mapLayoutToken = new MapLayoutToken();
    City city = new City();
    Worldmap worldmap = new Worldmap();
    
    @FXML	//세계 리스트
    private List<Label> worldList;
    
    @FXML //세계 리스트에 담을 것들
    private Label start, bako, socialoffice,beijing,jeju,taipei,dubai,unisland,tokyo,sydney,bonus,
    	quebec,hawai,sangpa,fund,praha,phuket,berlin,concord,mosc,rome,worldstr,tahiti,london,pari,revenue,newyork,seoul;
 
    @FXML
    private List<Label> cityList_bar2;
    
    @FXML
	private Label start_bar,bako_bar,socialoffice_bar,beijing_bar,jeju_bar,taipei_bar,dubai_bar,unisland_bar,
	tokyo_bar,sydney_bar,bonus_bar,quebec_bar,hawai_bar,sangpa_bar,fund_bar,praha_bar,phuket_bar,berlin_bar,
	concord_bar,mosc_bar,rome_bar,worldstr_bar,tahiti_bar,london_bar,pari_bar,revenue_bar,newyork_bar,seoul_bar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	//초기화 작업
		// TODO Auto-generated method stub
		info();
		dice();
		
//		memberinfo();
		btn_ok.visibleProperty().bind(btnOk);
		btn_cancel.visibleProperty().bind(btnOk);
		
		btn_rollDice.visibleProperty().bind(btnRollDice);
		image.visibleProperty().bind(RollDice);
		image2.visibleProperty().bind(RollDice);
		
		doublemsg.visibleProperty().bind(Doublemsg);
		
		//멤버 리스트에 라벨 넣기=======================================///
		
		this.member = new ArrayList<Label>();
		member.add(zzwi);
		member.add(chayoun);
		member.add(dahyun);
		member.add(jihyo); //지효
		member.add(junghyun);	//정연
		member.add(mina);
		member.add(momo);
		member.add(nayoun);
		member.add(sana);
		
		
		for(int i=0; i<member.size(); i++){
			m = member.get(i);
			String mem = member.get(i).getId();
			m.setOnMouseEntered(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					turnInfo.setText(mem);
				}
			});
		
		}
			m.setOnMouseExited(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					turnInfo.setText(null);
				}
			});
		
			this.cityList_bar2 = new ArrayList<Label>();
			cityList_bar2.add(start_bar);
			cityList_bar2.add(bako_bar);
			cityList_bar2.add(socialoffice_bar);
			cityList_bar2.add(beijing_bar);
			cityList_bar2.add(jeju_bar);
			cityList_bar2.add(taipei_bar);
			cityList_bar2.add(dubai_bar);
			cityList_bar2.add(unisland_bar);
			cityList_bar2.add(tokyo_bar);
			cityList_bar2.add(sydney_bar);
			cityList_bar2.add(bonus_bar);
			cityList_bar2.add(quebec_bar);
			cityList_bar2.add(hawai_bar);
			cityList_bar2.add(sangpa_bar);
			cityList_bar2.add(fund_bar);
			cityList_bar2.add(praha_bar);
			cityList_bar2.add(phuket_bar);
			cityList_bar2.add(berlin_bar);
			cityList_bar2.add(concord_bar);
			cityList_bar2.add(mosc_bar);
			cityList_bar2.add(rome_bar);
			cityList_bar2.add(worldstr_bar);
			cityList_bar2.add(tahiti_bar);
			cityList_bar2.add(london_bar);
			cityList_bar2.add(pari_bar);
			cityList_bar2.add(revenue_bar);
			cityList_bar2.add(newyork_bar);
			cityList_bar2.add(seoul_bar);
		//================================================================
	}
	
	//메뉴 아이템 처리
	public void handleNew(ActionEvent e){
		turnInfo.setText("New");
	}
	public void handleSave(ActionEvent e){
		turnInfo.setText("Save");
	}
	public void handleOpen(ActionEvent e){
		turnInfo.setText("Open");
	}
	public void handleExit(ActionEvent e){
		Platform.exit();
	}
	
	
	static int k = 0;	//턴을 바꿔줄 전역 변수
	User p = playerList.getplayerList(k);
	int k2 = (k+1) % 2;
	User pvp = playerList.getplayerList(k2);
	User p1 = playerList.getplayerList(0);
	User p2 = playerList.getplayerList(1);
	
	
	
	
	private void info() {		//정보보기
		
		this.worldList = new ArrayList<>();
    	worldList.add(start);
  		worldList.add(bako);
  		worldList.add(socialoffice);
  		worldList.add(beijing);
  		worldList.add(jeju);
  		worldList.add(taipei);
  		worldList.add(dubai);
  		worldList.add(unisland);
  		worldList.add(tokyo);
  		worldList.add(sydney);
  		worldList.add(bonus);
  		worldList.add(quebec);
  		worldList.add(hawai);
  		worldList.add(sangpa);
  		worldList.add(fund);
  		worldList.add(praha);
  		worldList.add(phuket);
  		worldList.add(berlin);
  		worldList.add(concord);
  		worldList.add(mosc);
  		worldList.add(rome);
  		worldList.add(worldstr);
  		worldList.add(tahiti);
  		worldList.add(london);
  		worldList.add(pari);
  		worldList.add(revenue);
  		worldList.add(newyork);
  		worldList.add(seoul);
  		
		for(int i=0;i<worldList.size();i++) {
			
			Label city = worldList.get(i);
			String info = worldmap.getMapArr()[i].toString();
			
			city.setOnMouseClicked(new EventHandler<Event>() {
				
				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					cityInfo.setText(info);
				}
				
			});
			
			city.setOnMouseEntered(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					cityInfo.setText(info);
				}
			});
			
			city.setOnMouseExited(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					cityInfo.setText(null);
				}
			});
			
		
			
		}		
		
		img1.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// 공유를 클릭하면
				
				int rn = (int)(Math.random()*4)+1;
				if(rn ==1) doublemsg.setText("트와이스 팬이에요~~^^");
				if(rn ==2) doublemsg.setText("Cheer~~Up~~ 좀더 힘을내~~^^");
				if(rn ==3) doublemsg.setText("퇴근까지 해.. 그것까지 하자....");
				if(rn ==4) doublemsg.setText("207호 화이팅!!");
				
			}
			
		});
	}
	

	
	int cnt = 0;
	
	private void dice(){		//다이스 굴리기
		btn_rollDice.setOnAction(new EventHandler<ActionEvent>() {
			//액션
			
			@Override
			public void handle(ActionEvent event) {
				
				
				if(cnt >0){
				k = ++k %2;		//턴이 바뀐다.		
				}
				
				cnt++;
				
				p = playerList.getplayerList(k);
				
				
//				Doublemsg.set(false);
				
				//무인도에 빠지면 두 턴동안 쉬게 한다.
				if(p.getMooinum()>0){
					k= ++k %2;
					p.setMooinum(p.getMooinum()-1);
				}
				p = playerList.getplayerList(k);
				
				if(p.getMooinum()>0){
					k= ++k %2;
					p.setMooinum(p.getMooinum()-1);
				}
				p = playerList.getplayerList(k);
				
//				turnInfo.setText(p.getId()+"의 턴");	
				
				
				int dice1 = (int)((Math.random()*6)+1);
				int dice2 = (int)((Math.random()*6)+1);
				
				
//				lbl_dice1.setText(Integer.toString(dice1));
//				lbl_dice2.setText(Integer.toString(dice2));
				
				if(dice1==1) {
					image.setImage(new Image("./주사위/1.png"));					
				} else if(dice1==2) {
					image.setImage(new Image("./주사위/2.png"));
				} else if(dice1==3) {
					image.setImage(new Image("./주사위/3.png"));
				} else if(dice1==4) {
					image.setImage(new Image("./주사위/4.png"));
				} else if(dice1==5) {
					image.setImage(new Image("./주사위/5.png"));
				} else {
					image.setImage(new Image("./주사위/6.png"));
				}
				
				if(dice2==1) {
					image2.setImage(new Image("./주사위/1.png"));					
				} else if(dice2==2) {
					image2.setImage(new Image("./주사위/2.png"));
				} else if(dice2==3) {
					image2.setImage(new Image("./주사위/3.png"));
				} else if(dice2==4) {
					image2.setImage(new Image("./주사위/4.png"));
				} else if(dice2==5) {
					image2.setImage(new Image("./주사위/5.png"));
				} else {
					image2.setImage(new Image("./주사위/6.png"));
				}
				
				RollDice.set(true);
				//다이스 나타내기
				
				
				
				int resultDice = dice1+dice2;
				
				
//				p.setPlocation(7);
				p.setPlocation(resultDice);
//				p.setPlocation(dice1);
				user1_money.setText(Integer.toString(p1.getMoney()));
				user2_money.setText(Integer.toString(p2.getMoney()));
				//화면창에 플레이어의 머니 보여주기
	
				int x = mapLayoutToken.getmapLayoutArr()[p.getPlocation()].layoutX;
				int y = mapLayoutToken.getmapLayoutArr()[p.getPlocation()].layoutY;
				//위치 셋팅하기
				
				if(k==0) {
					user1_circle.setLayoutX(x);
					user1_circle.setLayoutY(y);
				} else {
					user2_circle.setLayoutX(x+70);
					user2_circle.setLayoutY(y);
				}
				
				if(dice1==dice2) {
					doublemsg.setText("더블");
					cnt=0;
				}
				
				btnRollDice.set(false);	//다이스버튼 없애기
				
//				r_false();
				depart();	//도시를 구매할 것인가 물어본다.
			
				
			}
			
		});
		
	}
	

	////////////////////////////////////////////////////////////////////////////////
	
	
	private void depart(){	//땅에 도착했을때 진행되는 메서드
		
		
		map PdosiInfo = worldmap.getMapArr()[p.getPlocation()];
		
		if(PdosiInfo.getMapName().equals("City")){
			if(PdosiInfo.getOwnUser() == null) {
				buydosi();	//도시를 구매한다.
				
			} else if(PdosiInfo.getOwnUser() != null) {
				//도시가 누군가의 소유일 때
				if(PdosiInfo.getOwnUser().getId() == p.getId()) {
					//도시가 나의 소유일 때
					nowPlayer.setText("본인의 소유입니다. 통행료가 증가됩니다.");
					((City)PdosiInfo).setCityfee((int) (((City)PdosiInfo).getCityfee()*1.5));
					btnRollDice.set(true);
				} else {	//타인의 도시이면

					passPoint();		//통행료를 지급한다.
				}
			}
			
		}else{	// 도착한 곳이 시티가 아닐경우
//			System.out.println("map에 도착");
			if(p.getPlocation()==2) {
				nowPlayer.setText("사회복지기금을 지불합니다.");
				if(p.getMoney() < PdosiInfo.getPrice()) {
					worldmap.getMapArr()[14].setEventfee(worldmap.getMapArr()[14].getEventfee()+p.getMoney());
					p.setMoney(0);
				} else {
					p.setMoney(p.getMoney()-PdosiInfo.getPrice());
					worldmap.getMapArr()[14].setEventfee(worldmap.getMapArr()[14].getEventfee()+150_000);
					
				}
				btnRollDice.set(true);
				
			} else if(p.getPlocation()==14) {
				if(worldmap.getMapArr()[14].getEventfee()==0) {
					nowPlayer.setText("사회복지기금에 돈이 없습니다.");
				} else {
					p.setMoney(p.getMoney()+worldmap.getMapArr()[14].getEventfee());
					nowPlayer.setText(worldmap.getMapArr()[14].getEventfee()+"원의 사회복지기금을 받습니다.");
					worldmap.getMapArr()[14].setEventfee(0);
				}
				btnRollDice.set(true);
			} else if(p.getPlocation()==10) {
				nowPlayer.setText("보너스를 받습니다.");
				p.setMoney(p.getMoney()+100_000);
				btnRollDice.set(true);
				
				
			} else if(p.getPlocation()==21) {	//세계여행
				nowPlayer.setText("세계여행을 할 수 있습니다. 가고싶은 곳을 클릭하세요.");
				btnRollDice.set(false);
				RollDice.set(false);
				Doublemsg.set(false);
				for(int i=0;i<worldList.size();i++) {
					
					Label city = worldList.get(i);
					int a = i;
					
					city.setOnMouseClicked(new EventHandler<Event>() {
						
						@Override
						public void handle(Event event) {
							// TODO Auto-generated method stub
//							cityInfo.setText(info);
							p.setPlocation1(a);
							System.out.println("플레이어위치"+p.getPlocation());
							int x = mapLayoutToken.getmapLayoutArr()[p.getPlocation()].layoutX;
							int y = mapLayoutToken.getmapLayoutArr()[p.getPlocation()].layoutY;
							
							if(k==0) {
								user1_circle.setLayoutX(x);
								user1_circle.setLayoutY(y);
								
							} else {
								user2_circle.setLayoutX(x+70);
								user2_circle.setLayoutY(y);
								
							}
							depart();
							
							btnRollDice.set(true);
							info();	//정보 불러오기로 초기화 해야 한다
						}
						
					});
				}	
				
				
				
			} else if(p.getPlocation()==25) {
				nowPlayer.setText("국세청에 돈을 지불합니다.");
				if(p.getMoney()<PdosiInfo.getPrice()) {
					p.setMoney(0);
				} else {
					p.setMoney(p.getMoney()-(p.getMoney()/10));
				}
				btnRollDice.set(true);
			} else if(p.getPlocation()==0) {
				nowPlayer.setText("출발지에 도착했습니다.");
				btnRollDice.set(true);
				
				
				
				//===============================================================무인도
				//===============================================================무인도
				
			} else if(p.getPlocation()==7) {
				nowPlayer.setText("무인도에 도착했습니다.");
				nowPlayer.setText("다음턴에 한번 쉬세요");
				p.setMooinum(2);
				cnt++;
				doublemsg.setText(p.getMooinum()+"번 남았습니다.");
				btnRollDice.set(true);
				info();
			}
			//===============================================================무인도
			user1_money.setText(Integer.toString(p1.getMoney()));
			user2_money.setText(Integer.toString(p2.getMoney()));
			
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	

	private void buydosi(){	//도시를 구매하는 메서드
		
		
		
		map PdosiInfo = worldmap.getMapArr()[p.getPlocation()];

		//도시구입 OK? or no?
		
		nowPlayer.setText(PdosiInfo.toString());
		
		btn_ok.setText("구매");
		btnOk.set(true);
		
		btn_ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				btnOk.set(false);
				btnRollDice.set(true);
				
				boolean buyers = ((City)PdosiInfo).buyCity1(p);
				
				if(buyers) {
					nowPlayer.setText("구매되었습니다.");
					RollDice.set(false);
					p.setCitynum(p.getCitynum()+1);
					user1_money.setText(Integer.toString(p1.getMoney()));
					user2_money.setText(Integer.toString(p2.getMoney()));
					
					for(int i=0;i<cityList_bar2.size();i++) {

						if(i==p.getPlocation()) {
							Label city = cityList_bar2.get(i);
							if(k==0) {
								System.out.println(city.getText());
								city.setStyle("-fx-background-color: red;");
							} else {
								city.setStyle("-fx-background-color: blue;");
							}
						}
					}
					
					
					
				} else {
					nowPlayer.setText("잔액이 부족합니다.");
				}

				
			}
		});
		
		//도시를 구매하지 않을 시에 
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				btnOk.set(false);
				nowPlayer.setText(null);
				btnRollDice.set(true);
				
			}
		});
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//통행료 지급하기
	private void passPoint() {	

		
		map PdosiInfo = worldmap.getMapArr()[p.getPlocation()];
		
		if(p.getMoney()>=((City) PdosiInfo).getCityfee()) {
			p.setMoney(p.getMoney()-((City) PdosiInfo).getCityfee());
			PdosiInfo.getOwnUser().setMoney(PdosiInfo.getOwnUser().getMoney()+((City) PdosiInfo).getCityfee());
			user1_money.setText(Integer.toString(p1.getMoney()));
			user2_money.setText(Integer.toString(p2.getMoney()));
			nowPlayer.setText("통행료를 지불했습니다.");
			btnRollDice.set(true);
		} else {
			nowPlayer.setText("돈이 부족합니다. 파산하시겠습니까?");
			btnOk.set(true);
			btn_ok.setText("파산");
			
			//파산하기
			btn_ok.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					p.setMoney(0);
					user1_money.setText(Integer.toString(p1.getMoney()));
					user2_money.setText(Integer.toString(p2.getMoney()));
					btnOk.set(false);
					nowPlayer.setText(p.getId()+"패배!! 게임끝");
				}
			});
			
			//건물 팔기
			btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					//땅 되팔기
					btnOk.set(false);
					
					if(p.getCitynum()>0) {
						for(int i=0;i<worldmap.getMapArr().length;i++) {
							try {
								City cityInfo = (City) worldmap.getMapArr()[i];
								if(cityInfo.getOwnUser().getId() == p.getId()) {
									p.setCitynum(p.getCitynum()-1);
									p.setMoney(p.getMoney()+cityInfo.getCityPrice());
									cityInfo.setOwnUser(null);
									for(int j=0;j<cityList_bar2.size();j++) {
										if(i==j) {
											Label city = cityList_bar2.get(j);
											if(k==0) {
												city.setStyle(null);
											} else {
												city.setStyle(null);
											}
										}
									}
									nowPlayer.setText(cityInfo.getCityName()+"이(가) 팔렸습니다.");
									passPoint();
									break;
								}
							}catch(Exception e){
								nowPlayer.setText("팔 건물이 없습니다.\n"+p.getId()+"패배!! 게임끝");
								btnOk.set(false);
							}
						}
					} else {
						p.setMoney(0);
						user1_money.setText(Integer.toString(p1.getMoney()));
						user2_money.setText(Integer.toString(p2.getMoney()));
						nowPlayer.setText("팔 건물이 없습니다.\n"+p.getId()+"패배!! 게임끝");
						btnOk.set(false);
					}
				}
			});
			
		}
	
		
	}
	
	
	
}
