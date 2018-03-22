package status;

public class AlignStatus extends Status{
  
  private final String add = "text-align: left;";

  @Override
  public String getStringToAdd(int type) {
    return add;
  }
  
  @Override
  public String getStringToRemove() {
    return null;
  }
}