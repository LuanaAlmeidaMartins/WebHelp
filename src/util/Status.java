package util;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import persistence.Tags;

public class Status {
	
	private ArrayList<String> ArrayOfStyle = new ArrayList<String>();
	private ArrayList<String> tags = new ArrayList<String>();
	private Document doc;
	
	public Status(Document doc) {
		Tags tags = new Tags();
		this.tags = tags.getTagsName();
		this.doc = doc;
	}
	
	public void setFontStyle(String applyStyle, Boolean status) {
		if(status) {
			this.ArrayOfStyle.add(applyStyle);
			System.out.println("if "+this.ArrayOfStyle+" "+status);
		} 
		else {
			for(int i = 0; i < ArrayOfStyle.size();i++) {
				if(ArrayOfStyle.get(i).equals(applyStyle)) {
					ArrayOfStyle.remove(i);
					System.out.println("else "+this.ArrayOfStyle+" "+status);
				}
			}
		}	
		applyStyle();
	}
	
	public String getFontStyle() {
		String style = "";
		for(int i = 0; i < ArrayOfStyle.size();i++) {
			style+=ArrayOfStyle.get(i);
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

	public void setFontStyle(String applyStyle, String removeStyle, boolean status) {
		if(!status) {
			ArrayOfStyle.add(applyStyle);
			System.out.println("if "+ArrayOfStyle+" "+status);
		} 
		else {
			for(int i = 0; i < ArrayOfStyle.size();i++) {
				if(ArrayOfStyle.get(i).equals(removeStyle)) {
					ArrayOfStyle.remove(i);
					ArrayOfStyle.add(applyStyle);
					System.out.println("else "+this.ArrayOfStyle+" "+status);
				}
			}
		}	
		applyStyle();
	}
}
