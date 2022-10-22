package home.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.MemberVO;
import vo.blacklistVO;

public interface HDao {

	/*int loginCheck(Map<String, String> params);

	MemberVO getMemberInfo(String mem_id) throws SQLException;*/

	List<MemberVO> getMemberList(MemberVO mv) throws SQLException;
	
	void insertMem(MemberVO vo) throws SQLException;
	
	int updateMem(MemberVO up) throws SQLException;
	
	int deleteMem(MemberVO dt) throws SQLException;
	
	int checkId(MemberVO id) throws SQLException; //아이디 중복체크
	
	int loginCheck(Map<String, String> params); //로그인체크
	
	MemberVO getMemberInfo(String mem_id) throws SQLException; //멤버리스트

	MemberVO getSession(String session); //회원의세션
	
	MemberVO getName(String name); //회원가입여부 
}
