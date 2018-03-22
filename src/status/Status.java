package status;

import buttons.Types;

public abstract class Status implements Types{

  private boolean status = false;

  public boolean isActive() {
    return status;
  }

  public void setStatusMenu() {
    this.status = !status;
  }

  public void setStatusSubMenu() {
    if (!this.status) {
      this.status = !status;
      System.out.println("entrou if " + this.status);
    }
  }

  public abstract String getStringToAdd(int type);

  public abstract String getStringToRemove();

}
