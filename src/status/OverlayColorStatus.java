package status;

public class OverlayColorStatus {

  private String defaultOption = "#ffff00;";

  public void setColorName(String color) {
    defaultOption = "#" + color + ";";
  }

  public String getColor() {
    return defaultOption;
  }

}
