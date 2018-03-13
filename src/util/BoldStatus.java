package util;

public class BoldStatus {

	private boolean bold = false;
	
	public void setBold(){
		this.bold = !bold;
	}
	
	public boolean isBold() {
		return this.bold;
	}
	
	public String getBold() {
		return "font-weight: normal;";
	}
}