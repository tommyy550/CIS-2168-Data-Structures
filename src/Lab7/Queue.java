package Lab7;

import lab6.LL;
import lab6.LLException;

import lab6.QueueException;

public class Queue<T>
{
  private LL<T> theQueue;

  public Queue()
  {
    theQueue = new LL<T>();
  }

  public boolean isEmpty()
  {
    return theQueue.isEmpty();
  }

  public boolean isFull()
  {
    return false;
  }

  public void enqueue(T value)
  {
    theQueue.insertTail(value);
  }

  public T dequeue() throws QueueException
  {
    T retval = null;
    try
    {
      retval = theQueue.deleteHead();
    }
    catch (LLException e)
    {
      throw new QueueException("Queue underflow.");
    } 
    return retval;
  }

  public static void main(String args[]) throws QueueException
  {
    Queue<Integer> q = new Queue<Integer>();
    
    for (int i = 0; i < 20; i++)
    {
      q.enqueue(i);
    }

    while (!q.isEmpty())
      System.out.println(q.dequeue());
  }

}

