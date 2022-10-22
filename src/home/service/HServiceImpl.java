package home.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import home.dao.HDao;
import home.dao.HDaoImpl;
import vo.MemberVO;
import vo.blacklistVO;

public class HServiceImpl implements HService {

	private static HService service = null;
	private HDao dao = null;

	private HServiceImpl() {
		dao = HDaoImpl.getInstance();
	}


	public static HService getInstance() {
		if(service==null) {
			service = new HServiceImpl();
		}
		return service;
	}

	@Override
	public int loginCheck(Map<String, String> params) {
		int loginCount = dao.loginCheck(params);
		return loginCount;
	}


	@Override
	public MemberVO getMemberInfo(String mem_id) {
		MemberVO memberInfo = new MemberVO();
		try {
			 memberInfo = dao.getMemberInfo(mem_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberInfo;
	}

	@Override
	public List<MemberVO> getMemberList(MemberVO mv) {
		List<MemberVO> memberList = null;
		try {
			memberList = dao.getMemberList(mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberList;
	}


	@Override
	public void insertMem(MemberVO vo) {
		try {
			dao.insertMem(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int updateMem(MemberVO up) {
		Integer update = null;
		try {
			update = dao.updateMem(up);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}


	@Override
	public int deleteMem(MemberVO dt) {
		Integer delete = null;
			try {
				delete = dao.deleteMem(dt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return delete;
	}


	@Override
	public int checkId(MemberVO id) { //아이디 중복체크
		int checkId = 0;
		try {
			checkId = dao.checkId(id); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return checkId;
	}


	@Override
	public MemberVO getSession(String session) { //회원세션
		MemberVO returnSession = null;
		returnSession = (MemberVO)dao.getSession(session);
		return returnSession;
	}
	
	@Override
	public MemberVO getName(String name) { //회원세션
		MemberVO returnName = null;
		returnName = (MemberVO)dao.getName(name);
		return returnName;
	}
	
}
