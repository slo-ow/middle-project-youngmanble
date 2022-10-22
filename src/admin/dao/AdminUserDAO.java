package admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.MemberVO;

public class AdminUserDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public List<MemberVO> selectBoardList(String boardId,String boardName) throws SQLException{
		HashMap<String,String> paramMap = new HashMap<>();
		paramMap.put("mem_id",boardId);
		paramMap.put("mem_name",boardName);
		List<MemberVO> list = (List<MemberVO>)sqlMap.queryForList("adminuser.selectBoardList",paramMap);
		
		return list;
	}
	
	public MemberVO selectBoard(String boardId) throws SQLException{
		MemberVO vo = (MemberVO) sqlMap.queryForObject("adminuser.selectBoard",boardId);
		return vo;
	}
	
	public void deleteBoard(String boardId) throws SQLException{
		sqlMap.delete("adminuser.deleteBoard",boardId);
		System.out.println("삭제완료");
	}
	
	
}
