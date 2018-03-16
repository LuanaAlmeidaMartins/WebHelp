package util;

public class LetterColorStatus {
	private String defaultOption = "color: #666;";
	private final String removeOption = "color";
	private boolean status = false;
	
	public void setColor(){
		this.status = !status;
	}
	
	public boolean isColor() {
		return this.status;
	}
	
	public void setColorName(String color) {
		defaultOption = "color: #"+color+";";
	}
	
	public void setOptionColorSpacing() {
		if (this.status == false) {
			this.status = !status;
			System.out.println("entrou if " + this.status);
		}
	}

	public String getRemoveOption() {
		return removeOption;
	}
	
	public String getColor() {
		return defaultOption;
	}
}
