package util;

public class UnderlineStatus {

  private boolean underline = false;

  public void setUnderline() {
    this.underline = !underline;
  }

  public boolean isUnderline() {
    return underline;
  }

  public String getUnderline() {
    return "text-decoration: none;";
  }
}