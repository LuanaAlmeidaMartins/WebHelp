package util;

public class HighLightColorStatus {

  private String defaultOption = "#ffff00;";

  public void setColorName(String color) {
    defaultOption = "#" + color + ";";
  }

  public String getColor() {
    return defaultOption;
  }
}
