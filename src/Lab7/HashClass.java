package Lab7;

public class HashClass
{
  private String key;
  private double value;
 
  public HashClass(String k, double v)
  {
    this.key = k;
    this.value = v;
  }

  public String toString()
  {
    return this.key + ":" + this.value;
  }

  public String getKey()
  {
    return this.key;
  }

  public double getValue()
  {
    return this.value;
  }

  public void setValue(double v)
  {
    this.value = v;
  }

}
