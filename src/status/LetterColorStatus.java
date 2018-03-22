package status;

public class LetterColorStatus extends Status {

  private String defaultOption = "color: #666;";

  public void setColorName(String color) {
    defaultOption = "color: #" + color + ";";
  }

  @Override
  public String getStringToAdd(int type) {
    return defaultOption;
  }

  @Override
  public String getStringToRemove() {
    return "color";
  }
}
