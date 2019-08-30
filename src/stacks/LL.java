package stacks;

public class LL<Person> {
	private ListElement<Person> head;
	  private ListElement<Person> tail;

	  public LL()
	  {
	    head = null;
	    tail = null;
	  }

	  public boolean isEmpty()
	  {
	    return head == null;
	  }

	  public void insertAtHead(Person value)
	  {
	    if(head == null)
	    {
	      head = new ListElement<Person>(value, null);
	      tail = head;
	    }
	    else
	    {
	      head = new ListElement<Person>(value, head);
	    }
	  }

	  public void insertAtTail(Person value)
	  {
	    if(tail == null) //empty list
	    {
	      head = new ListElement<Person>(value, null);
	      tail = head;
	    }
	    else
	    {
	      tail.setLink(new ListElement<Person>(value, null));
	      tail = tail.getLink();
	    }
	  }
	    

	  public String deleteFromHead() throws LLException
	  {
	    String retval = null;
	    if(this.isEmpty())
	    {
	      throw new LLException("Attempt to remove from empty list");
	    }
	    else
	    {
	      retval = head.getValue();
	      head = head.getLink();
	      if(head == null)
	        tail = head;
	    }
	    return retval;
	  }

	  public void printList()
	  {
	    ListElement<Person> where = head;
	    while(where != null)
	    {
	      System.out.println(where.getValue());
	      where = where.getLink();
	    }
	  }

	  public void printListR()
	  {
	    printListR(head);
	  }

	  public void printListR(ListElement<Person> start)
	  {
	    if (start == null)
	      return;
	    else
	    {
	      System.out.println(start.getValue());
	      printListR(start.getLink());
	    }
	  }

	  public static void main(String args[]) throws LLException
	  {
	    LL<Double> myll = new LL<Double>();
	    
	    for (int i = 0; i < 10; i++)
	    {
	      myll.insertAtHead((double)i);
	    }

	    myll.printListR();
	  }

	public void insertAtHead(String tokens) {
		// TODO Auto-generated method stub
		
	}  



	}


	class ListElement <T>
	{
	  private String value;
	  private ListElement link;

	  public ListElement(String value, ListElement link)
	  {
	    this.value = value;
	    this.link = link;
	  }

	  public String getValue()
	  {
	    return this.value;
	  }

	  public ListElement getLink()
	  {
	    return this.link;
	  }

	  public void setLink(ListElement link)
	  {
	    this.link = link;
	  }
}
