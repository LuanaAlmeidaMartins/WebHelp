package apply;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import persistence.Tags;
import status.FontSize;

public class GeneralStyle {

  protected static ArrayList<String> arrayOfGeneralStyle = new ArrayList<String>();
  protected ArrayList<String> tags = new ArrayList<String>();
  protected Document doc;
  static boolean fonteStatus = false;
  boolean backgroundStatus = false;
  ArrayList<FontSize> sizeFont = new ArrayList<>();
  Element element, body;
  protected static String backgroundColor;

  /**
   * @param doc
   *          the style is applied on document doc
   */
  public GeneralStyle(Document doc) {
    Tags tags = new Tags();
    this.tags = tags.getTagsName();
    this.doc = doc;
  }

  public String getStyle() {
    String style = "";
    for (int i = 0; i < arrayOfGeneralStyle.size(); i++) {
      style += arrayOfGeneralStyle.get(i);
    }
    return style;
  }

  public void removeFontStyle(String removeString) {
    for (int i = 0; i < arrayOfGeneralStyle.size(); i++) {
      if ((arrayOfGeneralStyle.get(i).contains(removeString))
          || (arrayOfGeneralStyle.get(i).equals(removeString))) {
        arrayOfGeneralStyle.remove(i);
        System.out.println("else " + arrayOfGeneralStyle);
      }
    }
  }

  public void addFontStyle(String applyStyle, Boolean status) {
    if (status) {
      arrayOfGeneralStyle.add(applyStyle);
      System.out.println("if " + arrayOfGeneralStyle + " " + status);
    } else {
      removeFontStyle(applyStyle);
    }
  }

  public void addFontStyle(ArrayList<FontSize> sizeFont, boolean status) {
    fonteStatus = status;
    this.sizeFont = sizeFont;
  }

  public void setBackgroundStyle(String color, boolean status) {
    backgroundStatus = status;
    if (status) {
      backgroundColor = color;
    } else {
      backgroundColor = "";
    }
  }

}
