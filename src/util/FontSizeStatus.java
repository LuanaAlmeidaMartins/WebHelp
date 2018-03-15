package util;

import java.util.ArrayList;

import persistence.Options;

public class FontSizeStatus {

	private boolean status = false;

	public enum FontSizeEnum {

		enorme(4), grande(3), medio(2), pequeno(1), none(0);

		private final int valueOfFont;

		FontSizeEnum(int value) {
			valueOfFont = value;
		}

		public int getFont() {
			return valueOfFont;
		}
	}

	public boolean isFontSize() {
		return status;
	}

	public void setFontSize() {
		this.status = !status;
	}

	public void setOptionSize() {
		if(this.status==false) {
			this.status = !status;
			System.out.println("entrou if "+this.status);
		}
	}

	public ArrayList<FontSize> getSizeFont(int type) {
		Options options = new Options();
		ArrayList<FontSize> fontSizes = new ArrayList<FontSize>();

		if(type==FontSizeEnum.enorme.getFont()) {
			fontSizes = options.FillOptions(FontSizeEnum.enorme.getFont());
		} 
		if(type == FontSizeEnum.grande.getFont()) {
			fontSizes = options.FillOptions(FontSizeEnum.grande.getFont());
		}
		if(type == FontSizeEnum.pequeno.getFont()) {
			fontSizes = options.FillOptions(FontSizeEnum.pequeno.getFont());
		}
		else {
			fontSizes = options.FillOptions(FontSizeEnum.medio.getFont());
		}
		return fontSizes;
	}
}
