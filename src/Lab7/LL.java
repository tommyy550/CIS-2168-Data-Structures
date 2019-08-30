package Lab7;



public class LL<T> 
{
  private ListElement<T> head;
  private ListElement<T> tail;

  public LL()
  {
    this.head = new ListElement<T>();
    this.tail = this.head;
  }

  public void insertHead(T val)
  {
    this.head.link = new ListElement<T>(val, this.head.link);
  }

  public void insertTail(T val)
  {
    this.tail.link = new ListElement<T>(val);
    this.tail = this.tail.link;
  }

  public boolean isEmpty()
  {
    return this.head.link == null;
  }

  public T deleteHead() throws LLException
  {
    T retval = null;
    if (this.isEmpty() )
      throw new LLException("Attempt to delete from an empty list.");
    else
    {
      retval = (T)this.head.link.value; 
      this.head.link = this.head.link.link;
      if (this.isEmpty())
        this.tail = this.head;
    }
    return retval;
  }
    
  public String toString()
  {
    String retval = "";
    ListElement where = this.head;

    while (where.link != null)
    {
      retval += where.link.value;
      where = where.link;
    }
    return retval;
  }

  public static void main(String args[]) throws LLException
  {
    LL<Integer> myLL = new LL<Integer>();
  }
}
      
class ListElement<R>
{
  R value;
  ListElement<R> link;

  public ListElement(R v, ListElement<R> le)
  {
    this.value = v;
    this.link = le;
  }

  public ListElement(R v)
  {
    this(v, null);
  }

  public ListElement()
  {
    this(null, null);
  }
}
