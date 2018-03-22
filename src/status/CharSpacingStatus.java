package status;

public class CharSpacingStatus extends Status{

  private String defaultOption = "letter-spacing: 0px;";

  public String getStringToAdd(int type) {
    if (type == small) {
      defaultOption = "letter-spacing: 0px;";
    }
    if (type == medium) {
      defaultOption = "letter-spacing: 1px;";
    }
    if (type == big) {
      defaultOption = "letter-spacing: 2px;";
    }
    return defaultOption;
  }

  public String getStringToRemove() {
    return "letter-spacing";
  }
}
