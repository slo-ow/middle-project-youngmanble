/*package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import home.dao.HDao;
import home.service.HService;
import home.service.HServiceImpl;
import vo.MemberVO;
import vo.blacklistVO;

public class Main implements HDao {
	
	public static void main(String[] args) {
		HService service = HServiceImpl.getInstance();
		//Map
//		String mem_id = "a001";
//		String mem_pass="asdfasdf";
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("mem_id", mem_id);
//		params.put("mem_pass", mem_pass);
//		
//		int loginCount = service.loginCheck(params);
//		
//		System.out.println(loginCount);
		
		//String
//		String mem_id = "c001";
//		MemberVO memberInfo = service.getMemberInfo(mem_id);
//		System.out.println("끝");
		
		//select 완료
		//VO
//		MemberVO mv = new MemberVO();
//		String mem_id = "a001";
//		String mem_pass="asdfasdf";
//		
//		mv.setMem_id(mem_id);
//		mv.setMem_pass(mem_pass);
//		
//		List<MemberVO> memberList = service.getMemberList(mv); 
//		System.out.println("끝났다!");
		
		// insert완료 / 회원가입
		//vo
		//#mem_id#,#mem_se#,#mem_name#,#mem_sex#,#mem_ihidnum#,#mem_pass#,#mem_mbp#,#mem_email#,#mem_day#
		MemberVO vo = new MemberVO();
		String mem_id = "test2";
		String mem_se = "2";
		String mem_name = "문성철";
		String mem_sexdstn = "남자";
		String mem_ihidnum = "930205";
		String mem_pass = "9670";
		String mem_mbp = "010-9670-2460";
		String mem_email = "icet25@naver.com";
		//String mem_resist_day = "17-01-14";
		
		vo.setMem_id(mem_id);
		vo.setMem_se(mem_se);
		vo.setMem_name(mem_name);
		vo.setMem_sexdstn(mem_sexdstn);
		vo.setMem_ihidnum(mem_ihidnum);
		vo.setMem_pass(mem_pass);
		vo.setMem_mbp(mem_mbp);
		vo.setMem_email(mem_email);
		//vo.setMem_resist_day(mem_resist_day);
		
		service.insertMem(vo);
		System.out.println(vo +"\n 값 저장확인");
		
		//update 완료
		MemberVO vo = new MemberVO();
		String mem_id = "test1";
		String mem_se = "1";
		String mem_name = "문승츠리";
		String mem_sexdstn = "여자";
		
		vo.setMem_id(mem_id);
		vo.setMem_se(mem_se);
		vo.setMem_name(mem_name);
		vo.setMem_sexdstn(mem_sexdstn);
		
		service.updateMem(vo);
		System.out.println(service.updateMem(vo) + "\t 결과성공시 1 실패시 0");
		

		//delete 
		MemberVO vo = new MemberVO();
		String mem_id = "test2";		
		vo.setMem_id(mem_id);		
		service.deleteMem(vo);
		System.out.println(service.deleteMem(vo)+"\t 작동확인");
	}

	@Override
	public int loginCheck(Map<String, String> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO getMemberInfo(String mem_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public List<MemberVO> getMemberList(MemberVO mv) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMem(MemberVO vo) throws SQLException {
		
	}

	@Override
	public int updateMem(MemberVO up) throws SQLException {

		return (Integer) null;
	}

	@Override
	public int deleteMem(MemberVO dt) throws SQLException {
		
		return (Integer) null;
	}

	@Override
	public MemberVO checkId(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
*/