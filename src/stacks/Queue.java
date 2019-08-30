package stacks;

public class Queue {
	private LL<Person> theQueue;

	  public Queue()
	  {
	    theQueue = new LL<Person>();
	  }

	  public boolean isEmpty()
	  {
	    return theQueue.isEmpty();
	  }

	  public boolean isFull()
	  {
	    return false;
	  }

	  public void enqueue(String tokens)
	  {
	    theQueue.insertAtHead(tokens);
	  }

	  public String dequeue() throws QueueException
	  {
		  String retval = null;
	    try
	    {
	      retval = theQueue.deleteFromHead();
	    }
	    catch (LLException e)
	    {
	      throw new QueueException("Queue underflow.");
	    } 
	    return retval;
	  }

	 

}
