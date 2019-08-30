package trees;

public class BST<T extends Comparable<T>>
{
  private Node<T> root;
 
  public BST()
  {
    return;
  }

  public T find(T target)
  {
    T retval = find(target, root);
    return retval;
  }

  private T find(T target, Node<T> root)
  {
    T retval = null;
    if(root == null)
    {
      return null;
    }
    else
    {
      if (root.getData().compareTo(target)  > 0)
        retval  = find(target, root.getLeft());
      else if (root.getData().compareTo(target)  < 0)
        retval = find(target, root.getRight());
      else
        retval = root.getData();
      return retval;
    }
  }

  public void insert(T target) throws BSTException
  {
    this.root = insert(target, root);
  }
  
  private Node<T> insert(T target, Node<T> root) throws BSTException
  {
    if (root == null)
      return new Node<T>(target);
    else if (root.getData().compareTo(target) > 0)
      root.setLeft(insert(target, root.getLeft()));
    else if (root.getData().compareTo(target) < 0)
      root.setRight(insert(target, root.getRight()));
    else
      throw (new BSTException("Duplicate key"));
    return root;
  }
    
  public void printPreOrder()
  {
    printPreOrder(root);
  }

  public int depth()
  {
    return depth(root);
  }

  private int depth(Node<T> root)
  {
    if (root == null)
    {
      return -1;
    }
    else
    {
      int dl = depth(root.getLeft());
      int dr = depth(root.getRight());
      if (dl > dr)
        return dl + 1;
      else
        return dr + 1;
    }
  }

  private void printPreOrder(Node<T> root)
  {
    if(root == null)
      return;
    else
    {
      System.out.println(root.getData());
      printPreOrder(root.getLeft());
      printPreOrder(root.getRight());
    }
  }
}
