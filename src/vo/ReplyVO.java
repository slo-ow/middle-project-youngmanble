package vo;

public class ReplyVO {
	private String rpy_frb_num_pk;
	private String rpy_mem1_id_fk;
	private String rpy_mem2_id_fk;
	private String rpy_cts_fk;
	public String getRpy_frb_num_pk() {
		return rpy_frb_num_pk;
	}
	public void setRpy_frb_num_pk(String rpy_frb_num_pk) {
		this.rpy_frb_num_pk = rpy_frb_num_pk;
	}
	public String getRpy_mem1_id_fk() {
		return rpy_mem1_id_fk;
	}
	public void setRpy_mem1_id_fk(String rpy_mem1_id_fk) {
		this.rpy_mem1_id_fk = rpy_mem1_id_fk;
	}
	public String getRpy_mem2_id_fk() {
		return rpy_mem2_id_fk;
	}
	public void setRpy_mem2_id_fk(String rpy_mem2_id_fk) {
		this.rpy_mem2_id_fk = rpy_mem2_id_fk;
	}
	public String getRpy_cts_fk() {
		return rpy_cts_fk;
	}
	public void setRpy_cts_fk(String rpy_cts_fk) {
		this.rpy_cts_fk = rpy_cts_fk;
	}
	@Override
	public String toString() {
		return "ReplyVO [rpy_frb_num_pk=" + rpy_frb_num_pk + ", rpy_mem1_id_fk=" + rpy_mem1_id_fk + ", rpy_mem2_id_fk="
				+ rpy_mem2_id_fk + ", rpy_cts_fk=" + rpy_cts_fk + "]";
	}
	
	
}
