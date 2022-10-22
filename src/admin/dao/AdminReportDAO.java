package admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.ReportVO;

public class AdminReportDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public List<ReportVO> selectBoardList(String repoId,String reportTitle) throws SQLException{
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("rpt_trgter_id",repoId);
		paramMap.put("rpt_title", reportTitle);
		
		List<ReportVO> list = (List<ReportVO>)sqlMap.queryForList("adminreport.selectBoardList");
		
		return list;
	}
	
	public ReportVO selectBoard(String repoNum) throws SQLException{
		
		ReportVO vo = (ReportVO)sqlMap.queryForObject("adminreport.selectBoard",repoNum);
		return vo;
	
	}
	
	public void deleteBoard(String repoNum) throws SQLException{
		sqlMap.delete("adminreport.deleteBoard",repoNum);
	}
	
	public void updateBoard(String repoNum) throws SQLException {
		sqlMap.update("adminreport.updateBoard",repoNum);
	}

	public void insertBlack(String repoId) throws SQLException{
		sqlMap.insert("adminreport.insertBlack",repoId);
	}

}
