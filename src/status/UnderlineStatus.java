package status;

public class UnderlineStatus extends Status{

  private final String add = "text-decoration: none;";

  @Override
  public String getStringToAdd(int type) {
    return add;
  }

  @Override
  public String getStringToRemove() {
    return null;
  }
}