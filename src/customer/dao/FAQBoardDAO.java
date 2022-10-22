package customer.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.FaqVO;

public class FAQBoardDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public List<FaqVO> selectBoardList(String boardTitle, String boardWriter) throws SQLException {
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("faqSubject", boardTitle);
		paramMap.put("faqMemid", boardWriter);
		
		List<FaqVO> list = (List<FaqVO>)sqlMap.queryForList("FAQboard.selectBoardList", paramMap);
		
		return list;
	}
	
	
	
	public FaqVO selectBoard(String boardId) throws SQLException {
		FaqVO vo = (FaqVO)sqlMap.queryForObject("FAQboard.selectBoard", boardId);
		return vo;
	}
	
	
	
	public void plusView(String boardNum) throws SQLException {
		sqlMap.update("FAQboard.plusView", boardNum);
	}
	
	
	
	
	public FaqVO nextContents(String boardId) throws SQLException {
		FaqVO vo = (FaqVO)sqlMap.queryForObject("FAQboard.nextContent", boardId);
		return vo;
	}
	
	
	public FaqVO previousContents(String boardId) throws SQLException {
		FaqVO vo = (FaqVO)sqlMap.queryForObject("FAQboard.previousContent", boardId);
		return vo;
	}
	
	
	
	
	

}
