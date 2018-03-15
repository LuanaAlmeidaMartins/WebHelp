package util;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import persistence.Tags;

public class ApplyButtonStatus {

	private ArrayList<String> ArrayOfGeneralStyle = new ArrayList<String>();
	private ArrayList<String> tags = new ArrayList<String>();
	private Document doc;
	boolean fonte = false;
	ArrayList<FontSize> sizeFont = new ArrayList<>();


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
		String styleHeader = null;

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
				if(fonte) {
					styleHeader = getFontStyle()+" font-size: " + sizeFont.get(j).getSize();
				} else {
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
		fonte = status;
		this.sizeFont = sizeFont;
		applyStyle();
	}
}
