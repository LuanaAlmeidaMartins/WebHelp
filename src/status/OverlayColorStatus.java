package status;

import javafx.scene.paint.Color;

public class OverlayColorStatus{

  private String defaultOption = "#ffff00;";
  double blue, red, green;


  public String getColor() {
    return defaultOption;
  }

  public void setColor(Color value) {
    this.blue = value.getBlue();
    this.green=value.getGreen();
    this.red=value.getRed();   
  }

  public String getDefaultOption() {
    return defaultOption;
  }

  public double getBlue() {
    System.out.println(blue);
    return blue;
  }

  public double getRed() {
    System.out.println(red);
    return red;
  }

  public double getGreen() {
    System.out.println(green);
    return green;
  }
}
