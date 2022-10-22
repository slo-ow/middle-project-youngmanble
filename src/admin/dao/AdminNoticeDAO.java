package admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.NoticeVO;

public class AdminNoticeDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public List<NoticeVO> selectBoardList(String boardTitle, String boardWriter) throws SQLException{
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("ntcSubject", boardTitle);
		paramMap.put("ntcMemid", boardWriter);
		
		List<NoticeVO> list = sqlMap.queryForList("notice.selectBoardList",paramMap);
		
		return list;
	}
	
	public NoticeVO selectBoard(String boardId) throws SQLException{
		NoticeVO vo = (NoticeVO) sqlMap.queryForObject("notice.selectBoard",boardId);
		return vo;
	}
	
	public void insertBoard(NoticeVO vo) throws SQLException{
		HashMap paramMap = new HashMap<>();
		
		paramMap.put("ntcNum", vo.getNtcNum());
		paramMap.put("ntcSubject", vo.getNtcSubject());
		paramMap.put("ntcMemid", vo.getNtcMemid());
		paramMap.put("ntcContent", vo.getNtcContent());
		paramMap.put("ntcView", vo.getNtcView());
		
		sqlMap.insert("notice.insertBoard",paramMap);
		
		
	}
	
	public void updateBoard(NoticeVO vo) throws SQLException{
		HashMap paramMap = new HashMap<>();
		
		paramMap.put("ntcNum", vo.getNtcNum());
		paramMap.put("ntcSubject", vo.getNtcSubject());
		paramMap.put("ntcMemid", vo.getNtcMemid());
		paramMap.put("ntcContent", vo.getNtcContent());
		paramMap.put("ntcView", vo.getNtcView());
		
		sqlMap.update("notice.updateBoard",paramMap);
		
	}
	
	public void deleteBoard(String boardId) throws SQLException{
		sqlMap.delete("notice.deleteBoard",boardId);
	}
	
	public NoticeVO previousContents(String boardNum) throws SQLException{
		NoticeVO vo = (NoticeVO) sqlMap.queryForObject("notice.previousContent",boardNum);
		return vo;
	}
	
	public NoticeVO nextContents(String boardNum) throws SQLException{
		NoticeVO vo = (NoticeVO) sqlMap.queryForObject("notice.nextContent",boardNum);
		return vo;
	}
	
	
	
	
	
}
