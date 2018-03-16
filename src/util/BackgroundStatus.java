package util;

public class BackgroundStatus {
	private String defaultOption = "background-color: #fce5cd;";

	private boolean status = false;
	
	public void setColor(){
		this.status = !status;
		System.out.println("status "+status);
	}
	
	public boolean isColor() {
		return this.status;
	}
	
	public void setOptionBackgroundSpacing() {
		if (this.status == false) {
			this.status = !status;
			System.out.println("true or false? " + this.status);
		}
	}

	
	public String getColor() {
		return defaultOption;
	}

	public void setBackgroundColorName(String color) {
		System.out.println("no back? "+status+"  "+color );
		defaultOption = "background-color: #"+color+";";
	}
}
