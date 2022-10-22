package vo;

public class blacklistVO {
	
	private String blk_email;
	private String blk_code;
	private String blk_mem_id;
	private String blk_resist_day;
	private String blk_res_regi;
	private String blk_wrn_num;
	
	public String getBlk_email() {
		return blk_email;
	}
	public void setBlk_email(String blk_email) {
		this.blk_email = blk_email;
	}
	public String getBlk_code() {
		return blk_code;
	}
	public void setBlk_code(String blk_code) {
		this.blk_code = blk_code;
	}
	public String getBlk_mem_id() {
		return blk_mem_id;
	}
	public void setBlk_mem_id(String blk_mem_id) {
		this.blk_mem_id = blk_mem_id;
	}
	public String getBlk_resist_day() {
		return blk_resist_day;
	}
	public void setBlk_resist_day(String blk_resist_day) {
		this.blk_resist_day = blk_resist_day;
	}
	public String getBlk_res_regi() {
		return blk_res_regi;
	}
	public void setBlk_res_regi(String blk_res_regi) {
		this.blk_res_regi = blk_res_regi;
	}
	public String getBlk_wrn_num() {
		return blk_wrn_num;
	}
	public void setBlk_wrn_num(String blk_wrn_num) {
		this.blk_wrn_num = blk_wrn_num;
	}
	
	@Override
	public String toString() {
		return "blacklistVO [blk_email=" + blk_email + ", blk_code=" + blk_code + ", blk_mem_id=" + blk_mem_id
				+ ", blk_resist_day=" + blk_resist_day + ", blk_res_regi=" + blk_res_regi + ", blk_wrn_num="
				+ blk_wrn_num + "]";
	}
	
	
	
}
