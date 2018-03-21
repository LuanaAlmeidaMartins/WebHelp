package util;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import persistence.Tags;

public class ApplyButtonStatus {
  private ArrayList<String> arrayOfGeneralStyle = new ArrayList<String>();
  private ArrayList<String> tags = new ArrayList<String>();
  private Document doc;
  boolean fonteStatus = false;
  boolean backgroundStatus = false;
  ArrayList<FontSize> sizeFont = new ArrayList<>();
  Element element, body;
  private String backgroundColor;
  
  /**
   * @param doc the style is applied on document doc
   * */
  public ApplyButtonStatus(Document doc) {
    Tags tags = new Tags();
    this.tags = tags.getTagsName();
    this.doc = doc;
  }
  
  public String getFontStyle() {
    String style = "";
    for (int i = 0; i < arrayOfGeneralStyle.size(); i++) {
      style += arrayOfGeneralStyle.get(i);
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
      for (int i = 0; i < doc.getElementsByTagName(sizeFont
          .get(j).getTagName()).getLength(); i++) {
        if (fonteStatus) {
          styleHeader = getFontStyle() + " font-size: " 
                          + sizeFont.get(j).getSize();
          System.out.println("if "+ getFontStyle() + " font-size: " 
                          + sizeFont.get(j).getSize());
          body.setAttribute("style", backgroundColor);
        } else {
          styleHeader = getFontStyle();
System.out.println("else" + getFontStyle());
        }
        element = (Element) doc.getElementsByTagName(
            sizeFont.get(j).getTagName()).item(i);
        for (int k = 0; k < element.getChildNodes().getLength(); k++) {
          element.setAttribute("style", styleHeader);
          if (!element.getChildNodes().item(k).getNodeName().contains("#")) {
            Element subElement;
            subElement = (Element) element.getChildNodes().item(k);
            subElement.setAttribute("style", styleHeader);
          }
        }
      }
    }
  }
  
  public void setFontStyle(String applyStyle, Boolean status) {
    if (status) {
      this.arrayOfGeneralStyle.add(applyStyle);
      System.out.println("if " + this.arrayOfGeneralStyle + " " + status);
    } else {
      removeFontStyle(applyStyle);
    }
    applyStyle();
  }
  
  public void setFontStyle(ArrayList<FontSize> sizeFont, boolean status) {
    fonteStatus = status;
    this.sizeFont = sizeFont;
    applyStyle();
  }
  
  public void removeFontStyle(String removeString) {
    for (int i = 0; i < arrayOfGeneralStyle.size();i++) {
      if ((arrayOfGeneralStyle.get(i).contains(removeString)) 
          || (arrayOfGeneralStyle.get(i).equals(removeString))) {
        arrayOfGeneralStyle.remove(i);
        System.out.println("else " + this.arrayOfGeneralStyle);
      }
    }
  }
  
  public void setBackgroundStyle(String color, boolean status) {
    this.backgroundStatus = status;
    if (status) {
      this.backgroundColor = color;
    } else {
      this.backgroundColor = "";
    }
    applyStyle();
  }
}
