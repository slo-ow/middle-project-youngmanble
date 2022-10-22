package admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.FaqVO;

public class FaqDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public List<FaqVO> selectBoardList(String faqTitle,String faqWriter) throws SQLException{
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("faqSubject", faqTitle);
		paramMap.put("faqMemid", faqWriter);
		
		List<FaqVO> list = (List<FaqVO>)sqlMap.queryForList("FAQboard.selectBoardList",paramMap);
		
		return list;
	}
	
	public FaqVO selectBoard(String boardId) throws SQLException{
		
		FaqVO vo = (FaqVO) sqlMap.queryForObject("FAQboard.selectBoard",boardId);
		return vo;
		
	}
	
	public void insertBoard(FaqVO vo) throws SQLException{
		HashMap paramMap = new HashMap<>();
		
		paramMap.put("faqNum", vo.getFaqNum());
		paramMap.put("faqSubject", vo.getFaqSubject());
		paramMap.put("faqMemid", vo.getFaqMemid());
		paramMap.put("faqContent", vo.getFaqContent());
		paramMap.put("faqView", vo.getFaqView());
		
		sqlMap.insert("FAQboard.insertBoard",paramMap);
		
	}
	
	public void updateBoard(FaqVO vo) throws SQLException{
		HashMap paramMap = new HashMap<>();
		
		paramMap.put("faqNum", vo.getFaqNum());
		paramMap.put("faqSubject", vo.getFaqSubject());
		paramMap.put("faqMemIid", vo.getFaqMemid());
		paramMap.put("faqContent", vo.getFaqContent());
		paramMap.put("faqView", vo.getFaqView());
		
		sqlMap.update("FAQboard.updateBoard", paramMap);
		
	}
	
	public void deleteBoard(String boardId) throws SQLException{
		sqlMap.delete("FAQboard.deleteBoard",boardId);
	}
	
	public FaqVO nextContents(String boardNum) throws SQLException{
		FaqVO vo = (FaqVO) sqlMap.queryForObject("FAQboard.nextContent",boardNum);
		return vo;
		
	}
	
	public FaqVO previousContents(String boardNum) throws SQLException{
		FaqVO vo = (FaqVO) sqlMap.queryForObject("FAQboard.previousContent",boardNum);
		return vo;
		
	}
	
	
	
	
}
