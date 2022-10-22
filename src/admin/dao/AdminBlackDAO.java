package admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.blacklistVO;

public class AdminBlackDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public List<blacklistVO> selectBoardList(String code,String blackid) throws SQLException{
		HashMap<String,String> paramMap = new HashMap<>();
		paramMap.put("blk_code", code);
		paramMap.put("blk_mem_id",blackid);
		
		List<blacklistVO> list = (List<blacklistVO>)sqlMap.queryForList("blackboard.selectBoardList",paramMap);
		
		return list;
		
	}
	
	
	public blacklistVO selectBoard(String code) throws SQLException{
		blacklistVO vo = (blacklistVO) sqlMap.queryForObject("blackboard.selectBoard",code);
		return vo;
	}
	
	public void deleteBoard(String blackid) throws SQLException{
		sqlMap.delete("blackboard.deleteBoard",blackid);
		
	}
	
}
