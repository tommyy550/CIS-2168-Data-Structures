package trees;

import java.util.Random;
import java.util.Scanner;
public class BinTree<T>
{
  private Node<T> root;
  private Random r;

  public BinTree()
  {
    this.root = null;
    this.r = new Random();
  }

  public void makeRandomTree(int n)
  {
    for (int i = 0; i < n; i++)
    {
      ranInsert(i);
    }
  }

  public void ranInsert(int name)
  {
    Node<T> where = root;
    Node<T> next = null;
    boolean done = (where == null);
    if (root == null)
    {
      root = new Node<T>(name);
      return;
    }
    while (!done)
    {
      if (r.nextBoolean())
      {
        next = where.getLeft();
        if (next == null)
        {
          where.setLeft(new Node<T>(name));
          done = true;
        }
      }
      else
      {
        next = where.getRight();
        if (next == null)
        {
          where.setRight(new Node<T>(name));        
          done = true; 
        }
      }
      where = next;
    }
  }

    
  

  public class Node<R>
  {
    private R value;
    private Node<R> left;
    private Node<R> right;
    private int name;

    public Node(int n, R val, Node<R> lft, Node<R> r)
    {
      this.name = n;
      this.value = val;
      this.left = lft;
      this.right = r;
    }

    public Node (int n, R val)
    {
      this(n, val, null, null);
    }

    public Node (int n)
    {
      this(n, null, null, null);
    }

    public Node ()
    {
      this(0, null, null, null);
    }

    public R getValue()
    {
      return this.value;
    }

    public int getName()
    {
      return this.name;
    }

    public Node<R> getLeft()
    {
      return this.left;
    }
  
    public Node<R> getRight()
    {
      return this.right;
    }

    public void setLeft(Node<R> lft)
    {
      this.left = lft;
    }

    public void setRight(Node<R> r)
    {
      this.right = r;
    }
  }
  
  private String inorder(Node<T> r,String soFar, int depth){
	   String retval = soFar;
	   if (r == null)
	       retval += "Null\n";
	   else{
	       retval = inorder(r.getLeft(), retval, depth + 1);
	       retval += r.getName() + "\n";
	       retval = inorder(r.getRight(), retval, depth + 1);
	   }
	   return retval;
	}

	private String preorder(Node<T> r,String soFar, int depth){
	   String retval = soFar;
	   if (r == null)
	       retval += "Null\n";
	   else{
	       retval += r.getName() + "\n";
	       retval = preorder(r.getLeft(), retval, depth + 1);
	       retval = preorder(r.getRight(), retval, depth + 1);
	   }
	   return retval;
	}

	private String postorder(Node<T> r,String soFar, int depth){
	   String retval = soFar;
	   if (r == null)
	       retval += "Null\n";
	   else{
	       retval = postorder(r.getLeft(), retval, depth + 1);
	       retval = postorder(r.getRight(), retval, depth + 1);
	       retval += r.getName() + "\n";
	   }
	   return retval;
	}

	private static int depth(BinTree<Integer>.Node<Integer> root2){
	   if (root2 == null)
	       return 0;
	   int left = depth(root2.getLeft());
	   int right = depth(root2.getRight());
	   return Math.max(left,right) +1;
	}
	
	

	public static void main(String args[]){
	   Scanner sc = new Scanner(System.in);
	   System.out.print("Enter n:");
	   int n = sc.nextInt();
	   System.out.print("Enter number of reps");
	   int r = sc.nextInt();
	   int min = Integer.MAX_VALUE;
	   int max=-0;
	   double totalDepth=0.0;
	   for (int i = 0; i < r; i++){
	       BinTree<Integer> test = new BinTree<Integer>();
	       test.makeRandomTree(n);
	       int d = depth(test.root);
	       min = Math.min(min,d);
	       max = Math.max(max,d);
	       totalDepth+=d;
		   
	   }
	   System.out.println("The shallowest tree has depth " + (min-1));
	   System.out.println("The deepest tree has depth " + (max-1));
	   System.out.println("The average depth is " +(totalDepth/r-1));
	}
	
	
}
        
    


