package util;

public class FontFamilyStatus {
	private boolean status = false;
	private String defaultOption = "font-family: opendyslexic;";
	
	public enum FontFamilyEnum {

		openDyslexic(4), openSans(3), comicSans(2), georgia(1), none(0);

		private final int valueOfFont;

		FontFamilyEnum(int value) {
			valueOfFont = value;
		}

		public int getFont() {
			return valueOfFont;
		}
	}
	
	public boolean isFontFamily() {
		return status;
	}
	
	public void setFontFamily() {
		this.status = !status;
	}
	
	public void setOptionFamily() {
		if(this.status==false) {
			this.status = !status;
			System.out.println("entrou if "+this.status);
		}
	}
	
	public String getFamilyFont(int type) {
		if(type==FontFamilyEnum.openDyslexic.getFont()) {
			defaultOption = "font-family: opendyslexic;";
		} 
		if(type == FontFamilyEnum.comicSans.getFont()) {
			defaultOption = "font-family: \"Comic Sans MS\";";
		}
		if(type == FontFamilyEnum.openSans.getFont()) {
			defaultOption = "font-family: opensans;";
		}
		if(type == FontFamilyEnum.georgia.getFont()) {
			defaultOption = "font-family: Georgia;";
		}
		return defaultOption;
	}
}
