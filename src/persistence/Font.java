package persistence;

public class Font {
	String tagName, size;
	
	public enum FontEnum {

		enorme(4), grande(3), medio(2), pequeno(1);

		private final int valueOfFont;

		FontEnum(int value) {
			valueOfFont = value;
		}

		public int getFont() {
			return valueOfFont;
		}
	}
	
	
	public Font(String tagName, String size) {
		this.tagName = tagName;
		this.size = size;
	}

	public String getSize() {
		return size;
	}

	public String getTagName() {
		return tagName;
	}
}
