package status;

import java.util.ArrayList;

import persistence.Options;

public class FontSizeStatus extends Status {

  @Override
  public String getStringToAdd(int type) {
    return null;
  }

  public ArrayList<FontSize> getArrayStringToAdd(int type) {
    Options options = new Options();
    ArrayList<FontSize> fontSizes = new ArrayList<FontSize>();

    if (type == huge) {
      fontSizes = options.fillOptions(huge);
    }
    if (type == big) {
      fontSizes = options.fillOptions(big);
    }
    if (type == small) {
      fontSizes = options.fillOptions(small);
    }
    if ((type == medium) || (type == none)) {
      fontSizes = options.fillOptions(medium);
    }
    return fontSizes;
  }

  @Override
  public String getStringToRemove() {
    return "font-size";
  }
}
