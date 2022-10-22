package customer.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import conf.BuildedSqlMapClient;
import vo.ReportVO;

public class AccuseDAO {
	private SqlMapClient sqlMap = BuildedSqlMapClient.getSqlMapClient();
	
	public void insertMember(ReportVO vo) throws SQLException {
		HashMap paramMap = new HashMap<>();
		paramMap.put("rpt_mem_id", vo.getRpt_mem_id());
		paramMap.put("rpt_title", vo.getRpt_title());
		paramMap.put("rpt_cn", vo.getRpt_cn());
		paramMap.put("rpt_trgter_id", vo.getRpt_trgter_id());
		
		sqlMap.insert("accuse.insertBoard", paramMap);
	}
	
	
}
