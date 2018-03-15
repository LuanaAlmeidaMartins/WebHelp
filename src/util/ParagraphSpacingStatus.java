package util;

public class ParagraphSpacingStatus {

	private boolean status = false;
	private String defaultOption = "margin: 0 0 1.5em;";

	public enum ParagraphSpacingEnum {

		simpleParagraph(4), paragraph15(3), paragraph5(2), doubleParagraph(1), none(0);

		private final int valueOfFont;
		
		ParagraphSpacingEnum(int value) {
			valueOfFont = value;
		}

		public int getParagraphSpacing() {
			return valueOfFont;
		}
	}

	public boolean isParagraphSpacing() {
		return status;
	}

	public void setParagraphSpacing() {
		this.status = !status;
	}

	public String setOptionParagraphSpacing() {
		if (this.status == false) {
			this.status = !status;
			System.out.println("entrou if " + this.status);
		}
		return defaultOption;
	}

	public String getParagraphSpacing(int type) {
		if (type == ParagraphSpacingEnum.simpleParagraph.getParagraphSpacing()) {
			defaultOption = "margin: 0 0 1em;";
		}
		 if(type == ParagraphSpacingEnum.paragraph15.getParagraphSpacing()) {
		 defaultOption = "margin: 0 0 1.15em;";
		 }
		 if(type == ParagraphSpacingEnum.paragraph5.getParagraphSpacing()) {
		 defaultOption = "margin: 0 0 1.5em;";
		 }
		 if(type == ParagraphSpacingEnum.doubleParagraph.getParagraphSpacing()) {
		 defaultOption = "margin: 0 0 2em;";
		 }
		return defaultOption;
	}

}
