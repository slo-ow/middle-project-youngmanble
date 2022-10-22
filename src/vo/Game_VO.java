package vo;

public class Game_VO {
	private String gam_code;
	private String gam_gam_name;
	private String gam_score;
	private String gam_win_rcd;
	private String gam_date;
	private String gam_mem_id;
	private String gam_lose_rcd;
	public String getGam_code() {
		return gam_code;
	}
	public void setGam_code(String gam_code) {
		this.gam_code = gam_code;
	}
	public String getGam_gam_name() {
		return gam_gam_name;
	}
	public void setGam_gam_name(String gam_gam_name) {
		this.gam_gam_name = gam_gam_name;
	}
	public String getGam_score() {
		return gam_score;
	}
	public void setGam_score(String gam_score) {
		this.gam_score = gam_score;
	}
	public String getGam_win_rcd() {
		return gam_win_rcd;
	}
	public void setGam_win_rcd(String gam_win_rcd) {
		this.gam_win_rcd = gam_win_rcd;
	}
	public String getGam_date() {
		return gam_date;
	}
	public void setGam_date(String gam_date) {
		this.gam_date = gam_date;
	}
	public String getGam_mem_id() {
		return gam_mem_id;
	}
	public void setGam_mem_id(String gam_mem_id) {
		this.gam_mem_id = gam_mem_id;
	}
	public String getGam_lose_rcd() {
		return gam_lose_rcd;
	}
	public void setGam_lose_rcd(String gam_lose_rcd) {
		this.gam_lose_rcd = gam_lose_rcd;
	}
	@Override
	public String toString() {
		return "Game_VO [gam_code=" + gam_code + ", gam_gam_name=" + gam_gam_name + ", gam_score=" + gam_score
				+ ", gam_win_rcd=" + gam_win_rcd + ", gam_date=" + gam_date + ", gam_mem_id=" + gam_mem_id
				+ ", gam_lose_rcd=" + gam_lose_rcd + "]";
	}
	
	
}
