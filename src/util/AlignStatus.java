package util;

public class AlignStatus {
  private boolean align = false;
  
  public void setAlign() {
    this.align = !align;
  }
  
  public boolean isAlign() {
    return align;
  }
  
  public String getAlign() {
    return "text-align: left;";
  }
}