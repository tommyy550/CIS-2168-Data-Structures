package Lab7;

public class Dictionary
{
  private HashClass theDict[];
  private int size;
  private int used;

  public Dictionary(int maxSize)
  {
    this.theDict = new HashClass[maxSize];
    this.size = maxSize;
    this.used = 0;
  }

  private int hash(String s)
  {
    int retval = 0;
    for(int i = 0; i < s.length(); i++)
    {
      retval += (int)s.charAt(i);
    }
    return retval % size;
  }

  public boolean isFull()
  {
    return used >= size-1;
  }
 
  private int findWhere(String s) // finds s or where it should be
  {
    int where = this.hash(s);
    while (theDict[where] != null && !theDict[where].getKey().equals(s))
    {
      System.out.println("Ouch");
      where = (where + 1) % size;
    }
    return where;
  }

  public void delete(String s) throws HashException
  {
    int where = findWhere(s);
    if(theDict[where] != null)
    {
      theDict[where] = null;
      used--;
    } 
    else
    {
      throw new HashException("Missing Key" + s);
    }
  }

  public double lookup(String s) throws HashException
  {
    int where = findWhere(s);
    if(theDict[where] != null)
      return theDict[where].getValue();
    else
      throw new HashException("Missing Key" + s);
  }

  public void insert(String s, double val) throws HashException
  {
    if(this.isFull())
    {
      throw new HashException("Hash table overflow");
    }
    int where = findWhere(s);
    if (theDict[where] == null)
    {
      theDict[where] = new HashClass(s, val);
      used++;
    }
    else
      theDict[where].setValue(val);
  }

  public String toString()
  {
    String retval = "";
    for(int i = 0; i < size; i++)
    {
      if (theDict[i] != null)
        retval += i + "  " + theDict[i] + "\n";
    }
    return retval;
  } 

  public static void main(String args[]) throws Exception
  {
    String words[] = {"cow", "aardvark", "axolotl", "hardware", "software", "tick", "waterbug", "tiger", "caribou", "dog", "clavicle", "patella", "appendix", "liver", "gizzard"};
    Dictionary dict = new Dictionary(10);
    for(int i = 0;  i < words.length; i++)
    {
      dict.insert(words[i], i*2.5);
    }
    System.out.println(dict);
  }
}
