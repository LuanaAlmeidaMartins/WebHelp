package status;

public class ParagraphSpacingStatus extends Status{

  private String defaultOption = "margin: 0 0 1.5em;";

  @Override
  public String getStringToAdd(int type) {
    if (type == simpleSpacing) {
      defaultOption = "margin: 0 0 1em;";
    }
    if (type == spacing115) {
      defaultOption = "margin: 0 0 1.15em;";
    }
    if (type == spacing15) {
      defaultOption = "margin: 0 0 1.5em;";
    }
    if (type == doubleSpacing) {
      defaultOption = "margin: 0 0 2em;";
    }
    return defaultOption;
  }

  @Override
  public String getStringToRemove() {
    return "margin";
  }

}
