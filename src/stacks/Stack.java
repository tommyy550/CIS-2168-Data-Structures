package stacks;

public class Stack <Person>{
	private LL<Person> theStack;

	  public Stack()
	  {
	    theStack = new LL<Person>();
	  }

	  public boolean isEmpty()
	  {
	    return theStack.isEmpty();
	  }

	  public boolean isFull()
	  {
	    return false;
	  }

	  public void push(Person value)
	  {
	    theStack.insertAtHead(value);
	  }

	  public Person pop() throws StackException
	  {
		  Person retval = null;
	    try
	    {
	      retval = theStack.deleteFromHead();
	    }
	    catch (LLException e)
	    {
	      throw new StackException("Stack underflow.");
	    } 
	    return retval;
	  }

	  
	 
}
