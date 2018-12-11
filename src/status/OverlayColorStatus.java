package status;

import javafx.scene.paint.Color;

public class OverlayColorStatus {

  private boolean status = false;
  double blue, red, green;

  public OverlayColorStatus() {
    this.blue = 0.3019607961177826;
    this.green=1.0;
    this.red=1.0; 
  }

  public void setColor(Color value) {
    this.blue = value.getBlue();
    this.green=value.getGreen();
    this.red=value.getRed();   
  }

  public double getBlue() {
    return blue;
  }

  public double getRed() {
    return red;
  }

  public double getGreen() {
    return green;
  }
  
  public boolean isActive() {
    return status;
  }

  public void setStatusMenu() {
    this.status = !status;
  }

  public void setStatusSubMenu() {
    if (!this.status) {
      this.status = !status;
    }
  }

}
