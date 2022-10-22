package vo;

public class ChatVO {

	private String ct_code;
	private String ct_cn;
	private String ct_date;
	private String ct_ip_add;
	private String ct_mem1_id;
	private String ct_mem2_id;
	public String getCt_code() {
		return ct_code;
	}
	public void setCt_code(String ct_code) {
		this.ct_code = ct_code;
	}
	public String getCt_cn() {
		return ct_cn;
	}
	public void setCt_cn(String ct_cn) {
		this.ct_cn = ct_cn;
	}
	public String getCt_date() {
		return ct_date;
	}
	public void setCt_date(String ct_date) {
		this.ct_date = ct_date;
	}
	public String getCt_ip_add() {
		return ct_ip_add;
	}
	public void setCt_ip_add(String ct_ip_add) {
		this.ct_ip_add = ct_ip_add;
	}
	public String getCt_mem1_id() {
		return ct_mem1_id;
	}
	public void setCt_mem1_id(String ct_mem1_id) {
		this.ct_mem1_id = ct_mem1_id;
	}
	public String getCt_mem2_id() {
		return ct_mem2_id;
	}
	public void setCt_mem2_id(String ct_mem2_id) {
		this.ct_mem2_id = ct_mem2_id;
	}
	@Override
	public String toString() {
		return "ChatVO [ct_code=" + ct_code + ", ct_cn=" + ct_cn + ", ct_date=" + ct_date + ", ct_ip_add=" + ct_ip_add
				+ ", ct_mem1_id=" + ct_mem1_id + ", ct_mem2_id=" + ct_mem2_id + "]";
	}
	
	
}
