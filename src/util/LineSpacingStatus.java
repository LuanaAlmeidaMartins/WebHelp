package util;

public class LineSpacingStatus {

	private boolean status = false;
	private String defaultOption = "line-height: 1.5em;";

	public enum LineSpacingEnum {

		lineSimples(4), line15(3), line5(2), linhaDupla(1), none(0);

		private final int valueOfFont;
		
		LineSpacingEnum(int value) {
			valueOfFont = value;
		}

		public int getLineSpacing() {
			return valueOfFont;
		}
	}

	public boolean isLineSpacing() {
		return status;
	}

	public void setLineSpacing() {
		this.status = !status;
	}

	public String setOptionLineSpacing() {
		if (this.status == false) {
			this.status = !status;
			System.out.println("entrou if " + this.status);
		}
		return defaultOption;
	}

	public String getLineSpacing(int type) {
		if (type == LineSpacingEnum.lineSimples.getLineSpacing()) {
			defaultOption = "line-height: 1em;";
		}
		 if(type == LineSpacingEnum.line15.getLineSpacing()) {
		 defaultOption = "line-height: 1.15em;";
		 }
		 if(type == LineSpacingEnum.line5.getLineSpacing()) {
		 defaultOption = "line-height: 1.5em;";
		 }
		 if(type == LineSpacingEnum.linhaDupla.getLineSpacing()) {
		 defaultOption = "line-height: 2em;";
		 }
		return defaultOption;
	}

}
