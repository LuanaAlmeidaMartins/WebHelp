package util;

public class ItalicStatus{

	private boolean italic = false;
	
	public void setItalic() {
		this.italic = !italic;
	}

	public boolean isItalic() {
		return italic;
	}
	
	public String getItalic() {
		return "font-style: normal;";
	}
}