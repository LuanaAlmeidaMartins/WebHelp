package util;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import persistence.Tags;

public class ApplyButtonStatus {

	private ArrayList<String> ArrayOfGeneralStyle = new ArrayList<String>();
	private ArrayList<String> tags = new ArrayList<String>();
	private Document doc;
	boolean fonteStatus = false;
	boolean backgroundStatus = false;
	ArrayList<FontSize> sizeFont = new ArrayList<>();
	Element element, body;
	private String backgroundColor;

	public ApplyButtonStatus(Document doc) {
		Tags tags = new Tags();
		this.tags = tags.getTagsName();
		this.doc = doc;
	}

	public void setFontStyle(String applyStyle, Boolean status) {
		if(status) {
			this.ArrayOfGeneralStyle.add(applyStyle);
			System.out.println("if "+this.ArrayOfGeneralStyle+" "+status);
		} 
		else {
			removeFontStyle(applyStyle);
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
		String styleHeader = null;
		
		body = (Element) doc.getElementsByTagName("body").item(0);
		body.setAttribute("style", backgroundColor);
		
		// passa em todas as tags 
		for (int g = 0; g < tags.size(); g++) {
			for (int i = 0; i < doc.getElementsByTagName(tags.get(g)).getLength(); i++) {
				element = (Element) doc.getElementsByTagName(tags.get(g)).item(i);
				element.setAttribute("style", getFontStyle());
			}
		}

		// somente cabeçalho
		for (int j = 0; j < sizeFont.size(); j++) {
			for (int i = 0; i < doc.getElementsByTagName(sizeFont.get(j).getTagName()).getLength(); i++) {
				if(fonteStatus) {
					styleHeader = getFontStyle()+" font-size: " + sizeFont.get(j).getSize();
					body.setAttribute("style", backgroundColor);
				} 
			 else {
					styleHeader = getFontStyle();
				}
				element = (Element) doc.getElementsByTagName(sizeFont.get(j).getTagName()).item(i);
				for (int k = 0; k < element.getChildNodes().getLength(); k++) {
					element.setAttribute("style", styleHeader);
					if (!element.getChildNodes().item(k).getNodeName().contains("#")) {
						Element subElement = (Element) element.getChildNodes().item(k);
						subElement.setAttribute("style", styleHeader);
					}
				}
			}
		}
	}

	public void setFontStyle(ArrayList<FontSize> sizeFont, boolean status) {
		fonteStatus = status;
		this.sizeFont = sizeFont;
		
		applyStyle();
	}

	public void removeFontStyle(String removeString) {
		for(int i = 0; i < ArrayOfGeneralStyle.size();i++) {
			if((ArrayOfGeneralStyle.get(i).contains(removeString))||
					(ArrayOfGeneralStyle.get(i).equals(removeString))) {
				ArrayOfGeneralStyle.remove(i);
				System.out.println("else "+this.ArrayOfGeneralStyle);
			}
		}
	}

	public void setBackgroundStyle(String color, boolean status) {
		this.backgroundStatus = status;
		if(status) {
			this.backgroundColor = color;
		} else {
			this.backgroundColor="";
		}
		applyStyle();
	}
}
