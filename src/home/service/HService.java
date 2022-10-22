package home.service;

import java.util.List;
import java.util.Map;

import vo.MemberVO;
import vo.blacklistVO;

public interface HService {

	int loginCheck(Map<String, String> params);

	MemberVO getMemberInfo(String mem_id);

	List<MemberVO> getMemberList(MemberVO mv);
	
	void insertMem(MemberVO vo); //회원가입
	
	int updateMem(MemberVO up);
	
	int deleteMem(MemberVO dt);
	
	int checkId(MemberVO id); //아이디 중복체크

	MemberVO getSession(String session); //회원의세션
	
	MemberVO getName(String name); //회원가입여부를확인함
}
