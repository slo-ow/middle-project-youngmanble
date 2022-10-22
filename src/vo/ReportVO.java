package vo;

public class ReportVO {
	private String rpt_num;
	private String rpt_trgter_id;
	private String rpt_resist_day;
	private String rpt_cn;
	private String rpt_mem_id;
	private String rpt_prost;
	private String rpt_title;
	
	
	
	public String getRpt_title() {
		return rpt_title;
	}
	public void setRpt_title(String rpt_title) {
		this.rpt_title = rpt_title;
	}
	public String getRpt_num() {
		return rpt_num;
	}
	public void setRpt_num(String rpt_num) {
		this.rpt_num = rpt_num;
	}
	public String getRpt_trgter_id() {
		return rpt_trgter_id;
	}
	public void setRpt_trgter_id(String rpt_trgter_id) {
		this.rpt_trgter_id = rpt_trgter_id;
	}
	public String getRpt_resist_day() {
		return rpt_resist_day;
	}
	public void setRpt_resist_day(String rpt_resist_day) {
		this.rpt_resist_day = rpt_resist_day;
	}
	public String getRpt_cn() {
		return rpt_cn;
	}
	public void setRpt_cn(String rpt_cn) {
		this.rpt_cn = rpt_cn;
	}
	public String getRpt_mem_id() {
		return rpt_mem_id;
	}
	public void setRpt_mem_id(String rpt_mem_id) {
		this.rpt_mem_id = rpt_mem_id;
	}
	public String getRpt_prost() {
		return rpt_prost;
	}
	public void setRpt_prost(String rpt_prost) {
		this.rpt_prost = rpt_prost;
	}
	@Override
	public String toString() {
		return "ReportVO [rpt_num=" + rpt_num + ", rpt_trgter_id=" + rpt_trgter_id + ", rpt_resist_day="
				+ rpt_resist_day + ", rpt_cn=" + rpt_cn + ", rpt_mem_id=" + rpt_mem_id + ", rpt_prost=" + rpt_prost
				+ ", rpt_title=" + rpt_title + "]";
	}
	
	
	
	
}
