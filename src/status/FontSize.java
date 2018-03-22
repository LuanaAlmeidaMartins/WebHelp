package status;

public class FontSize {
  String tagName, size;

  public FontSize(String tagName, String size) {
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
