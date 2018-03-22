package status;

public class LineSpacingStatus extends Status{

  private String defaultOption = "line-height: 1.5em;";

  @Override
  public String getStringToAdd(int type) {
    if (type == simpleSpacing) {
      defaultOption = "line-height: 1em;";
    }
    if (type == spacing115) {
      defaultOption = "line-height: 1.15em;";
    }
    if (type == spacing15) {
      defaultOption = "line-height: 1.5em;";
    }
    if (type == doubleSpacing) {
      defaultOption = "line-height: 2em;";
    }
    return defaultOption;
  }

  @Override
  public String getStringToRemove() {
    return "line-height";
  }
}
