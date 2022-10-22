package home.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.MemberVO;
import vo.blacklistVO;

public class HDaoImpl implements HDao {
	
	private static HDaoImpl dao = null;
	private SqlMapClient client = null;
	
	private HDaoImpl() {
		client = BuildedSqlMapClient.getSqlMapClient();
	}
	
	
	public static HDaoImpl getInstance() {
		if(dao == null) {
			dao = new HDaoImpl();
		}
		return dao;
	}


	@Override
	public int loginCheck(Map<String, String> params) {
		int loginCount = 0;
		try {
			loginCount = (int) client.queryForObject("member.loginCount", params); // 객체하나받아올때 queryforobject
		} catch (SQLException e) {													//객체여러개받아올때 queryforlist
			e.printStackTrace();
		}		
		return loginCount;
	}


	@Override
	public MemberVO getMemberInfo(String mem_id) throws SQLException {
		return (MemberVO) client.queryForObject("member.getMemberInfo", mem_id);
	}

	@Override
	public List<MemberVO> getMemberList(MemberVO mv) throws SQLException {
		
		return client.queryForList("member.getMemberList", mv);
	}

	// queryForObject는 select 할때만 사용한다.
	@Override
	public void insertMem(MemberVO vo) throws SQLException {
		client.insert("member.memberInsert",vo);
		
	}


	@Override
	public int updateMem(MemberVO up) throws SQLException {
		
		return client.update("member.memberUpdate",up);
	}


	@Override
	public int deleteMem(MemberVO dt) throws SQLException {
		
		return client.delete("member.memberDelete",dt);
	}


	@Override
	public int checkId(MemberVO id) throws SQLException {
		int loginCheck = 0;
		try {
			loginCheck = (int)client.queryForObject("member.checkId",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginCheck;
	}


	@Override
	public MemberVO getSession(String session) { //회원세션
		MemberVO returnSession = null;
		try {
			returnSession = (MemberVO)client.queryForObject("member.getSession",session);
			System.out.println(returnSession + "\n||queryForObject 반환값 확인||");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnSession;
	}


	@Override
	public MemberVO getName(String name) {
		MemberVO returnName = null;
		try{
			returnName = (MemberVO)client.queryForObject("member.getName",name);
			System.out.println(returnName + "\n||queryForObject 반환값 확인||");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return returnName;
	}
	
}
