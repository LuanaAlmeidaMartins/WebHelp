package status;

public class RulerStatus {

  private double defaultOption = 80;
  private boolean status = false;

  public double getStringToAdd(int type) {
    if (type == 1) {
      defaultOption = 50.00;
    }
    if (type == 2) {
      defaultOption = 100.00;
    }
    if (type == 3) {
      defaultOption = 150.00;
    }
    if (type == 4) {
      defaultOption = 300.00;
    }
    return defaultOption;
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
