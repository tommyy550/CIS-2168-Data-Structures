package Lab7;

import lab6.LL;
import lab6.LLException;

import lab6.StackException;

public class Stack<T>
{
  private LL<T> theStack;

  public Stack()
  {
    theStack = new LL<T>();
  }

  public boolean isEmpty()
  {
    return theStack.isEmpty();
  }

  public boolean isFull()
  {
    return false;
  }

  public void push(T value)
  {
    theStack.insertHead(value);
  }

  public T pop() throws StackException
  {
    T retval = null;
    try
    {
      retval = theStack.deleteHead();
    }
    catch (LLException e)
    {
      throw new StackException("Stack underflow.");
    } 
    return retval;
  }

  public static void main(String args[]) throws StackException
  {
    Stack<Integer> st = new Stack<Integer>();
    
    for (int i = 0; i < 20; i++)
    {
      st.push(i);
    }

    while (!st.isEmpty())
      System.out.println(st.pop());
  }

}

