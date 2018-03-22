package status;

public class BackgroundStatus extends Status{
  
  private String defaultOption = "background-color: #fce5cd;";

  public void setColorName(String color) {
    defaultOption = "background-color: #" + color + ";";
  }

  @Override
  public String getStringToAdd(int type) {
    return defaultOption;
  }

  @Override
  public String getStringToRemove() {
    return null;
  }
}
