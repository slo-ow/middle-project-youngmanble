package customer.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.MemberVO;

public class CustomerHomeDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public MemberVO getInfo(String memberId) throws SQLException {
		MemberVO vo = (MemberVO)sqlMap.queryForObject("member.getMemberInfo", memberId);
		return vo;
	}
	
	
	
	public void updateInfo(MemberVO vo) throws SQLException {
		HashMap paramMap = new HashMap<>();
		paramMap.put("mem_id", vo.getMem_id());
		paramMap.put("mem_pass", vo.getMem_pass());
		paramMap.put("mem_name", vo.getMem_name());
		paramMap.put("mem_ihidnum", vo.getMem_ihidnum());
		paramMap.put("mem_mbp", vo.getMem_mbp());
		paramMap.put("mem_email", vo.getMem_email());
		paramMap.put("mem_propic", vo.getMem_propic());
		sqlMap.update("member.infoUpdate", paramMap);
	}
	
	
	
	public void deleteAccount(String accountId) throws SQLException {
		
		sqlMap.delete("member.memberDelete", accountId);
	}
	
	

}
