package util;

import java.util.ArrayList;

import persistence.Options;

public class FontSizeStatus {

  private boolean status = false;
  private final String removeOption = "font-size";

  public enum FontSizeEnum {

    enorme(4), grande(3), medio(2), pequeno(1), none(0);

    private final int valueOfFont;

    FontSizeEnum(int value) {
      valueOfFont = value;
    }

    public int getFont() {
      return valueOfFont;
    }
  }

  public boolean isFontSize() {
    return status;
  }

  public void setFontSize() {
    this.status = !status;
  }

  public void setOptionSize() {
    if (this.status == false) {
      this.status = !status;
    }
  }

  public ArrayList<FontSize> getSizeFont(int type) {
    Options options = new Options();
    ArrayList<FontSize> fontSizes = new ArrayList<FontSize>();

    if (type == FontSizeEnum.enorme.getFont()) {
      fontSizes = options.fillOptions(FontSizeEnum.enorme.getFont());
    }
    if (type == FontSizeEnum.grande.getFont()) {
      fontSizes = options.fillOptions(FontSizeEnum.grande.getFont());
    }
    if (type == FontSizeEnum.pequeno.getFont()) {
      fontSizes = options.fillOptions(FontSizeEnum.pequeno.getFont());
    }
    if ((type == FontSizeEnum.medio.getFont())
        || (type == FontSizeEnum.none.getFont())) {
      fontSizes = options.fillOptions(FontSizeEnum.medio.getFont());
    }
    return fontSizes;
  }

  public String getRemoveOption() {
    return removeOption;
  }
}
