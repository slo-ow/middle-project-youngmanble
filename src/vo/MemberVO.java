package vo;

public class MemberVO {
	
	private String mem_id;
	private String mem_se;
	private String mem_name;
	private String mem_sexdstn;
	private String mem_ihidnum;
	private String mem_pass;
	private String mem_mbp;
	private String mem_email;
	private String mem_resist_day;
	private String mem_propic; //프로필사진 path()저장
	//count 추가 
	private String count;
	
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_se() {
		return mem_se;
	}
	public void setMem_se(String mem_se) {
		this.mem_se = mem_se;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_sexdstn() {
		return mem_sexdstn;
	}
	public void setMem_sexdstn(String mem_sexdstn) {
		this.mem_sexdstn = mem_sexdstn;
	}
	public String getMem_ihidnum() {
		return mem_ihidnum;
	}
	public void setMem_ihidnum(String mem_ihidnum) {
		this.mem_ihidnum = mem_ihidnum;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_mbp() {
		return mem_mbp;
	}
	public void setMem_mbp(String mem_mbp) {
		this.mem_mbp = mem_mbp;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_resist_day() {
		return mem_resist_day;
	}
	public void setMem_resist_day(String mem_resist_day) {
		this.mem_resist_day = mem_resist_day;
	}
	
	public String getMem_propic() {
		return mem_propic;
	}
	public void setMem_propic(String mem_propic) {
		this.mem_propic = mem_propic;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_se=" + mem_se + ", mem_name=" + mem_name + ", mem_sexdstn="
				+ mem_sexdstn + ", mem_ihidnum=" + mem_ihidnum + ", mem_pass=" + mem_pass + ", mem_mbp=" + mem_mbp
				+ ", mem_email=" + mem_email + ", mem_resist_day=" + mem_resist_day + ", mem_propic=" + mem_propic
				+ ", count=" + count + "]";
	}
	
}
