package apply;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ApplyStyle extends GeneralStyle {

  public ApplyStyle(Document doc) {
    super(doc);
  }

  public void applyStyle() {
    body();
    everyTag();
    header();
  }

  private void header() {
    String styleHeader = null;
    for (int j = 0; j < sizeFont.size(); j++) {
      for (int i = 0; i < doc.getElementsByTagName(sizeFont.get(j).getTagName()).getLength(); i++) {
        if (fonteStatus) {
          styleHeader = getStyle() + " font-size: " + sizeFont.get(j).getSize();
          System.out.println("if " + getStyle() + " font-size: " + sizeFont.get(j).getSize());
        } else {
          styleHeader = getStyle();
          System.out.println("else" + getStyle());
        }
        body.setAttribute("style", backgroundColor);
        element = (Element) doc.getElementsByTagName(sizeFont.get(j).getTagName()).item(i);
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

  private void everyTag() {
    for (int g = 0; g < tags.size(); g++) {
      for (int i = 0; i < doc.getElementsByTagName(tags.get(g)).getLength(); i++) {
        element = (Element) doc.getElementsByTagName(tags.get(g)).item(i);
        element.setAttribute("style", getStyle());
      }
    }
  }

  private void body() {
    body = (Element) doc.getElementsByTagName("body").item(0);
    body.setAttribute("style", backgroundColor);
  }
}
