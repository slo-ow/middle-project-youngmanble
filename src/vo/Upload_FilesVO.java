package vo;

public class Upload_FilesVO {
	private String upf_seq_pk;
	private String upf_ntc_num;
	private String upf_frb_num;
	private String upf_fq_num;
	public String getUpf_seq_pk() {
		return upf_seq_pk;
	}
	public void setUpf_seq_pk(String upf_seq_pk) {
		this.upf_seq_pk = upf_seq_pk;
	}
	public String getUpf_ntc_num() {
		return upf_ntc_num;
	}
	public void setUpf_ntc_num(String upf_ntc_num) {
		this.upf_ntc_num = upf_ntc_num;
	}
	public String getUpf_frb_num() {
		return upf_frb_num;
	}
	public void setUpf_frb_num(String upf_frb_num) {
		this.upf_frb_num = upf_frb_num;
	}
	public String getUpf_fq_num() {
		return upf_fq_num;
	}
	public void setUpf_fq_num(String upf_fq_num) {
		this.upf_fq_num = upf_fq_num;
	}
	@Override
	public String toString() {
		return "Upload_FilesVO [upf_seq_pk=" + upf_seq_pk + ", upf_ntc_num=" + upf_ntc_num + ", upf_frb_num="
				+ upf_frb_num + ", upf_fq_num=" + upf_fq_num + "]";
	}
	
	
}
