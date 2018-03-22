package status;

public class FontFamilyStatus extends Status {

  private String defaultOption = "font-family: opendyslexic;";

  @Override
  public String getStringToAdd(int type) {
    if (type == openDyslexic) {
      defaultOption = "font-family: opendyslexic;";
    }
    if (type == comicSans) {
      defaultOption = "font-family: \"Comic Sans MS\";";
    }
    if (type == openSans) {
      defaultOption = "font-family: opensans;";
    }
    if (type == georgia) {
      defaultOption = "font-family: Georgia;";
    }
    return defaultOption;
  }

  @Override
  public String getStringToRemove() {
    return "font-family";
  }
}