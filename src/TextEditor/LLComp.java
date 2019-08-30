package TextEditor;

public class LLComp<T extends Comparable<? super T>> 
{
  private CListElement<T> head;
  private CListElement<T> tail;

  public LLComp()
  {
    this.head = new CListElement<T>();
    this.tail = this.head;
  }

  public void insertInOrder(T val, String s)
  {
    recInsInOrder(head, val, s);
  }

  private void recInsInOrder(CListElement<T> head, T val, String s)
  {
    if (head.link == null)
    {
      head.link = new CListElement<T>(val,s);
    }
    else
    {
      if (head.link.value.compareTo(val) > 0)
      {
        head.link = new CListElement<T>(val,s,head.link);
      }
      else
      {
        recInsInOrder(head.link, val,s);
      }
    }
  }
 
  public void insertHead(T val,String s)
  {
    this.head.link = new CListElement<T>(val,s, this.head.link);
  }

  public void insertTail(T val, String s)
  {
    this.tail.link = new CListElement<T>(val,s);
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
    CListElement where = this.head;

    while (where.link != null)
    {
      retval += where.link.value + where.link.text + "\n";
      where = where.link;
    }
    return retval;
  }
  
  public void resenquence() {
	  CListElement where = this.head;
	  int count=0;
	  while(where!=null) {
		  where.value= count;
		  where=where.link;
		  count+=10;
	  }
	  
  }
  
  public String read() {
	  String retval = "";
	    CListElement where = this.head;
	  while (where.link != null)
	    {
	      retval += where.link.text + " ";
	      where = where.link;
	    }
	    return retval;
  }
  public static void main(String args[])
  {
    LLComp<String> myLL = new LLComp<String>();

   /* myLL.insertInOrder("xyzzy");
    myLL.insertInOrder("lmnop");
    myLL.insertInOrder("nope");
    myLL.insertInOrder("zzzzz");*/


    System.out.println(myLL);
  }

}
      
class CListElement<R>
{
  R value;
  String text;
  CListElement<R> link;

  public CListElement(R v, String s, CListElement<R> le)
  {
    this.value = v;
    this.text=s;
    this.link = le;
  }

  public CListElement(R v, String s)
  {
    this(v,s,null);
  }

  public CListElement()
  {
    this(null,null, null);
  }
}

