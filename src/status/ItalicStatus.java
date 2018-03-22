package status;

public class ItalicStatus extends Status{

  private final String add = "font-style: normal;";

  @Override
  public String getStringToAdd(int type) {
    return add;
  }

  @Override
  public String getStringToRemove() {
    return null;
  }
}