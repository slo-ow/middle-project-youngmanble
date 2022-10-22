package admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.FreeBoardVO;

public class AdminFreeDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public List<FreeBoardVO> selectBoardList(String boardTitle, String boardWriter) throws SQLException{
		HashMap<String,String> paramMap = new HashMap<>();
		paramMap.put("fbSubject",boardTitle);
		paramMap.put("fbMemid", boardWriter);
		
		
		List<FreeBoardVO> list = (List<FreeBoardVO>)sqlMap.queryForList("freeboard.selectBoardList",paramMap);
		
		return list;
	}
	
	public FreeBoardVO selectBoard(String boardId) throws SQLException {
		FreeBoardVO vo = (FreeBoardVO)sqlMap.queryForObject("freeboard.selectBoard", boardId);
		return vo;
	}
	
	
	
	public void insertBoard(FreeBoardVO vo) throws SQLException {
		HashMap paramMap = new HashMap<>();
		paramMap.put("fbNum", vo.getFbNum());
		paramMap.put("fbSubject", vo.getFbSubject());
		paramMap.put("fbMemid", vo.getFbMemid());
		paramMap.put("fbContent", vo.getFbContent());
		paramMap.put("fbView", vo.getFbView());
		sqlMap.insert("freeboard.insertBoard", paramMap);
	}
	
	
	
	/*
	관리자는 굳이 update할 필요 없다
	
	public void updateBoard(FreeBoardVO vo) throws SQLException {
		HashMap paramMap = new HashMap<>();
		paramMap.put("fbNum", vo.getFbNum());
		paramMap.put("fbSubject", vo.getFbSubject());
		paramMap.put("fbMemid", vo.getFbMemid());
		paramMap.put("fbContent", vo.getFbContent());
		paramMap.put("fbView", vo.getFbView());
		sqlMap.update("freeboard.updateBoard", paramMap);
	}
	*/
	
	
	public void deleteBoard(String boardId) throws SQLException {
		sqlMap.delete("freeboard.deleteBoard", boardId);
	}
	
	public FreeBoardVO nextContents(String boardNum) throws SQLException{
		FreeBoardVO vo = (FreeBoardVO) sqlMap.queryForObject("freeboard.nextContent",boardNum);
		return vo;
	}
	
	public FreeBoardVO previousContents(String boardNum) throws SQLException{
		FreeBoardVO vo = (FreeBoardVO) sqlMap.queryForObject("freeboard.previousContent",boardNum);
		return vo;
	}
	
	
	
}
	
	
	
	
	
	

