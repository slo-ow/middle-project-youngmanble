/**
 * @author SeongCheol
 * @version 1.0
 * 6자리의 랜덤숫자 생성 테스트클래스
 * 
 */
package home.sms;
public class Test {

	public static void main(String[] args) {
		//		int n1 = (int)(Math.random()*9+1);
		//		int n2 = (int)(Math.random()*9+1);
		//		int n3 = (int)(Math.random()*9+1);
		//		int n4 = (int)(Math.random()*9+1);
		//		int n5 = (int)(Math.random()*9+1);
		//		int n6 = (int)(Math.random()*9+1);
		//		
		//인증번호요청을위한 랜덤인증번호 6자리 생성후 메세지 전송
		int n1 = (int)(Math.random()*9+1);
		int n2 = (int)(Math.random()*9+1);
		int n3 = (int)(Math.random()*9+1);
		int n4 = (int)(Math.random()*9+1);
		int n5 = (int)(Math.random()*9+1);
		int n6 = (int)(Math.random()*9+1);

		//6자리의 숫자이기때문에 자릿수 만큼을 곱해준다
		String result = String.valueOf(((n1*100000)+(n2*10000)+(n3*1000)+(n4*100)+(n5*10)+n6));
		System.out.println("누리마블 인증번호 "+result);
	}

}
