package vo;

public class Friend_ManagementVO {
	private String frnd_resist;
	private String frnd_mem1_id;
	private String frnd_mem2_id;
	public String getFrnd_resist() {
		return frnd_resist;
	}
	public void setFrnd_resist(String frnd_resist) {
		this.frnd_resist = frnd_resist;
	}
	public String getFrnd_mem1_id() {
		return frnd_mem1_id;
	}
	public void setFrnd_mem1_id(String frnd_mem1_id) {
		this.frnd_mem1_id = frnd_mem1_id;
	}
	public String getFrnd_mem2_id() {
		return frnd_mem2_id;
	}
	public void setFrnd_mem2_id(String frnd_mem2_id) {
		this.frnd_mem2_id = frnd_mem2_id;
	}
	@Override
	public String toString() {
		return "Friend_ManagementVO [frnd_resist=" + frnd_resist + ", frnd_mem1_id=" + frnd_mem1_id + ", frnd_mem2_id="
				+ frnd_mem2_id + "]";
	}
	
	
}
