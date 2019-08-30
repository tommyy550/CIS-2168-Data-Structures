package trees;

public class Node<T>
{
  private T data;
  private Node<T> left;
  private Node<T> right;

  public Node(T data, Node<T> left, Node<T> right)
  {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  public Node(T data)
  {
    this(data, null, null);
  }

  public T getData()
  {
    return this.data;
  }

  public Node<T> getLeft()
  {
    return this.left;
  }

  public Node<T> getRight()
  {
    return this.right;
  }

  public void setData(T data)
  {
    this.data = data;
  }

  public void setLeft(Node<T> left)
  {
    this.left = left;
  }

  public void setRight(Node<T> right)
  {
    this.right = right;
  }
}