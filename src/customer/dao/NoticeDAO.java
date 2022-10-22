package customer.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.NoticeVO;

public class NoticeDAO {

	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	
	public List<NoticeVO> selectBoardList(String boardTitle, String boardWriter) throws SQLException {
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("ntcSubject", boardTitle);
		paramMap.put("ntcMemid", boardWriter);
		
		List<NoticeVO> list = (List<NoticeVO>)sqlMap.queryForList("notice.selectBoardList", paramMap);
		
		return list;
	}
	
	
	public NoticeVO selectBoard(String boardId) throws SQLException {
		NoticeVO vo = (NoticeVO)sqlMap.queryForObject("notice.selectBoard", boardId);
		return vo;
	}
	
	
	
	
	public void plusView(String boardNum) throws SQLException {
		sqlMap.update("notice.plusView", boardNum);
	}
	
	
	
	
	public NoticeVO nextContents(String boardId) throws SQLException {
		NoticeVO vo = (NoticeVO)sqlMap.queryForObject("notice.nextContent", boardId);
		return vo;
	}
	
	
	
	public NoticeVO previousContents(String boardId) throws SQLException {
		NoticeVO vo = (NoticeVO)sqlMap.queryForObject("notice.previousContent", boardId);
		return vo;
	}
	
	
	
	
	
	
	
	
}
