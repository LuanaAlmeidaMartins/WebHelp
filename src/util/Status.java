package util;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import persistence.Tags;

public class Status {
	
	private ArrayList<String> ArrayOfGeneralStyle = new ArrayList<String>();
	private ArrayList<String> tags = new ArrayList<String>();
	private Document doc;
	private ArrayList<String> headers = new ArrayList<String>();
	private ArrayList<String> paragraph = new ArrayList<String>();
	
	public Status(Document doc) {
		Tags tags = new Tags("tags");
		this.tags = tags.getTagsName();
		Tags headers = new Tags("headers");
		this.headers  = headers.getTagsName();
		Tags paragraph = new Tags("paragraph");
		this.paragraph  = paragraph.getTagsName();
		this.doc = doc;
	}
	
	public void setFontStyle(String applyStyle, Boolean status) {
		if(status) {
			this.ArrayOfGeneralStyle.add(applyStyle);
			System.out.println("if "+this.ArrayOfGeneralStyle+" "+status);
		} 
		else {
			for(int i = 0; i < ArrayOfGeneralStyle.size();i++) {
				if(ArrayOfGeneralStyle.get(i).equals(applyStyle)) {
					ArrayOfGeneralStyle.remove(i);
					System.out.println("else "+this.ArrayOfGeneralStyle+" "+status);
				}
			}
		}	
		applyStyle();
	}
	
	
	public String getFontStyle() {
		String style = "";
		for(int i = 0; i < ArrayOfGeneralStyle.size();i++) {
			style+=ArrayOfGeneralStyle.get(i);
		}
		return style;
	}
	
	public void applyStyle() {
		Element element;
		for (int g = 0; g < tags.size(); g++) {
			for (int i = 0; i < doc.getElementsByTagName(tags.get(g)).getLength(); i++) {
				element = (Element) doc.getElementsByTagName(tags.get(g)).item(i);
				element.setAttribute("style", getFontStyle());
			}
		}
	}
}
