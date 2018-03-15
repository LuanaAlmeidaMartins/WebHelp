package util;

public class LetterColorStatus {
	String defaultOption = "color: #666;";
private boolean color = false;
	
	public void setColor(){
		this.color = !color;
	}
	
	public boolean isColor() {
		return this.color;
	}
	
	public void setColorName(String color) {
		defaultOption = "color: #"+color+";";
	}
	
	public String getColor() {
		return defaultOption;
	}
}
