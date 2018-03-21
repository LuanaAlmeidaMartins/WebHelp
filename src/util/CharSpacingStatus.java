package util;

public class CharSpacingStatus {

  private boolean status = false;
  private String defaultOption = "letter-spacing: 0px;";
  private final String removeOption = "letter-spacing";

  public enum CharSpacingEnum {

    small(3), medium(2), big(1), none(0);

    private final int valueOfFont;

    CharSpacingEnum(int value) {
      valueOfFont = value;
    }

    public int getCharSpacing() {
      return valueOfFont;
    }
  }

  public boolean isCharSpacing() {
    return status;
  }

  public void setCharSpacing() {
    this.status = !status;
  }

  public String setOptionCharSpacing() {
    if (this.status == false) {
      this.status = !status;
      System.out.println("entrou if " + this.status);
    }
    return defaultOption;
  }

  public String getCharSpacing(int type) {
    if (type == CharSpacingEnum.small.getCharSpacing()) {
      defaultOption = "letter-spacing: 0px;";
    }
    if (type == CharSpacingEnum.medium.getCharSpacing()) {
      defaultOption = "letter-spacing: 1px;";
    }
    if (type == CharSpacingEnum.big.getCharSpacing()) {
      defaultOption = "letter-spacing: 2px;";
    }
    return defaultOption;
  }

  public String getRemoveOption() {
    return removeOption;
  }
}
