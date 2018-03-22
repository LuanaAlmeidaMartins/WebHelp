package status;

public class BoldStatus extends Status {

  private final String add = "font-weight: normal;";

  @Override
  public String getStringToAdd(int type) {
    return add;
  }

  @Override
  public String getStringToRemove() {
    return null;
  }
}