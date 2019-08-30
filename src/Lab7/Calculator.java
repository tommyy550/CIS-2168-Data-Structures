package Lab7;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


import lab6.QueueException;

public class Calculator {

	public static void main(String[] args) throws QueueException {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter expression");
		String userInput = sc.nextLine();

	
		StringTokenizer tok = new StringTokenizer(userInput, "+-x/()", true);
		String[] faketokens = new String[100];
		int where = 0;
		while (tok.hasMoreTokens()) {
			faketokens[where] = tok.nextToken();
			where++;
		}
		String[] tokens = new String[where];
		for (int i = 0; i < where; i++) {
			tokens[i] = faketokens[i];
		}	
		double value = infixCalculate(tokens);
		System.out.println(value);
		
	}

	public static double infixCalculate(String[] tokens) throws QueueException {
		Queue a = new Queue();
		Stack b = new Stack();
		// use shunting yard
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].charAt(0) == '0' || tokens[i].charAt(0) == '1' || tokens[i].charAt(0) == '2'
					|| tokens[i].charAt(0) == '3' || tokens[i].charAt(0) == '4' || tokens[i].charAt(0) == '5'
					|| tokens[i].charAt(0) == '6' || tokens[i].charAt(0) == '7' || tokens[i].charAt(0) == '8'
					|| tokens[i].charAt(0) == '9') {
				a.enqueue(tokens[i]);
			} else if (tokens[i].equals("(")) {
				b.push(tokens[i]);
			} else if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("x")
					|| tokens[i].equals("/")) {
				if (b.isEmpty() || priority(tokens[i], (String) b.peek()) == true) {
					b.push(tokens[i]);
				} else {
					while (!b.empty() && !b.peek().equals("(")) {
						a.enqueue((String) b.pop());
					}
					b.push(tokens[i]);
				}
			}
			if (tokens[i].equals(")")) {
				while (!b.empty() && !b.peek().equals("(")) {
					a.enqueue((String) b.pop());
				}
				b.pop();
			}
		}
		while (!b.empty()) {
			a.enqueue((String) b.pop());
		}
		return postfix(a);
	}

	public static double postfix(Queue q) throws QueueException {
		Stack stack = new Stack();
		while (!q.isEmpty()) {
			String token = (String) q.dequeue();
			if (token.equals("+") || token.equals("-") || token.equals("x") || token.equals("/")) {
				if (token.equals("+")) {
					stack.push((double) stack.pop() + (double) stack.pop());
				}
				if (token.equals("-")) {
					stack.push((double) stack.pop() - (double) stack.pop());
				}
				if (token.equals("x")) {
					stack.push((double) stack.pop() * (double) stack.pop());
				}
				if (token.equals("/")) {
					stack.push((double) stack.pop() / (double) stack.pop());
				}
			}
			if (token.charAt(0) == '0' || token.charAt(0) == '1' || token.charAt(0) == '2' || token.charAt(0) == '3'
					|| token.charAt(0) == '4' || token.charAt(0) == '5' || token.charAt(0) == '6'
					|| token.charAt(0) == '7' || token.charAt(0) == '8' || token.charAt(0) == '9') {
				stack.push(Double.parseDouble(token));
			}

		}
		return (double) stack.pop();
	}

	
	private static boolean priority(String token, String stack) {
		int tok = 0;
		if (token.equals("(")) {
			tok = 3;
		}
		if (token.equals(")")) {
			tok = 3;
		}
		if (token.equals("x")) {
			tok = 2;
		}
		if (token.equals("/")) {
			tok = 2;
		}
		if (token.equals("+")) {
			tok = 1;
		}
		if (token.equals("-")) {
			tok = 1;
		}
		int stk = 0;
		if (stack.equals("(")) {
			stk = 3;
		}
		if (stack.equals(")")) {
			stk = 3;
		}
		if (stack.equals("x")) {
			stk = 2;
		}
		if (stack.equals("/")) {
			stk = 2;
		}
		if (stack.equals("+")) {
			stk = 1;
		}
		if (stack.equals("-")) {
			stk = 1;
		}
		if (tok > stk) {
			return true;
		} else {
			return false;
		}
	}
	
}















/*public class Editor
{
  private LLComp<String> theText;
  private String prompt;
  private enum Keywords  {READ, SAVE, LIST, RESEQUENCE, QUIT, EXIT, UNDEFINED;};
  private Scanner console;
  public Dictionary dict;


  public Editor()
  {
    this.theText = new LLComp<String>();
    this.prompt = ">";
    this.console = new Scanner(System.in);
  }

  public void setPrompt(String p)
  {
  }

  private static boolean isInt(String s) // see if a string represents
  {                                      // an integer.
    boolean retval = false;
    try
    {
      Integer.parseInt(s);
      retval = true; 
    }
    catch (NumberFormatException e)
    {
      retval = false;
    }
    return retval;
  }
 
  public void process() throws NumberFormatException, HashException, QueueException
  {
    boolean done = false;
    String line;
    while (!done)
    {
      System.out.print(this.prompt);
      line = console.nextLine().toUpperCase(); // Work only with upper case
      String splitString[] = line.split(" ", 4);

      if (splitString[0].equals("LET")){
    	  StringTokenizer tok = new StringTokenizer(splitString[4], "+-x/()", true);
    	  String[] tokens = new String[100];
  		int where = 0;
  		while (tok.hasMoreTokens()) {
  			tokens[where] = tok.nextToken();
  			where++;
  		}
  		String[] realTokens = new String[where];
  		
  		for (int i = 0; i < where; i++) {
  			if (tokens[i].charAt(0) == '0' || tokens[i].charAt(0) == '1' || tokens[i].charAt(0) == '2'
					|| tokens[i].charAt(0) == '3' || tokens[i].charAt(0) == '4' || tokens[i].charAt(0) == '5'
					|| tokens[i].charAt(0) == '6' || tokens[i].charAt(0) == '7' || tokens[i].charAt(0) == '8'
					|| tokens[i].charAt(0) == '9') {
  			realTokens[i] = tokens[i];
  			}
  			else if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("x")
					|| tokens[i].equals("/")) {
  				realTokens[i] = tokens[i];
  			}
  			else if(tokens[i].equals("(")||tokens[i].equals(")")) {
  				realTokens[i] = tokens[i];
  			}
  			else {
  				realTokens[i].equals(tokens[i].look)
  			}
  		}	
  		
    	  dict.insert(splitString[1],infixCalculate(tokens));
    	  
    	  
      }
      else if(splitString[0].equals("PRINT")){
    	  System.out.println(splitString[2].getValue());
      }
      else 
        done = this.doCommand(splitString[0]);
    }
  }
  public static double infixCalculate(String[] tokens) throws QueueException {
		Queue a = new Queue();
		Stack b = new Stack();
		// use shunting yard
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].charAt(0) == '0' || tokens[i].charAt(0) == '1' || tokens[i].charAt(0) == '2'
					|| tokens[i].charAt(0) == '3' || tokens[i].charAt(0) == '4' || tokens[i].charAt(0) == '5'
					|| tokens[i].charAt(0) == '6' || tokens[i].charAt(0) == '7' || tokens[i].charAt(0) == '8'
					|| tokens[i].charAt(0) == '9') {
				a.enqueue(tokens[i]);
			} else if (tokens[i].equals("(")) {
				b.push(tokens[i]);
			} else if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("x")
					|| tokens[i].equals("/")) {
				if (b.isEmpty() || priority(tokens[i], (String) b.peek()) == true) {
					b.push(tokens[i]);
				} else {
					while (!b.empty() && !b.peek().equals("(")) {
						a.enqueue((String) b.pop());
					}
					b.push(tokens[i]);
				}
			}
			if (tokens[i].equals(")")) {
				while (!b.empty() && !b.peek().equals("(")) {
					a.enqueue((String) b.pop());
				}
				b.pop();
			}
		}
		while (!b.empty()) {
			a.enqueue((String) b.pop());
		}
		return postfix(a);
	}

	public static double postfix(Queue q) throws QueueException {
		Stack stack = new Stack();
		while (!q.isEmpty()) {
			String token = (String) q.dequeue();
			if (token.equals("+") || token.equals("-") || token.equals("x") || token.equals("/")) {
				if (token.equals("+")) {
					stack.push((double) stack.pop() + (double) stack.pop());
				}
				if (token.equals("-")) {
					stack.push((double) stack.pop() - (double) stack.pop());
				}
				if (token.equals("x")) {
					stack.push((double) stack.pop() * (double) stack.pop());
				}
				if (token.equals("/")) {
					stack.push((double) stack.pop() / (double) stack.pop());
				}
			}
			if (token.charAt(0) == '0' || token.charAt(0) == '1' || token.charAt(0) == '2' || token.charAt(0) == '3'
					|| token.charAt(0) == '4' || token.charAt(0) == '5' || token.charAt(0) == '6'
					|| token.charAt(0) == '7' || token.charAt(0) == '8' || token.charAt(0) == '9') {
				stack.push(Double.parseDouble(token));
			}

		}
		return (double) stack.pop();
	}

	
	private static boolean priority(String token, String stack) {
		int tok = 0;
		if (token.equals("(")) {
			tok = 3;
		}
		if (token.equals(")")) {
			tok = 3;
		}
		if (token.equals("x")) {
			tok = 2;
		}
		if (token.equals("/")) {
			tok = 2;
		}
		if (token.equals("+")) {
			tok = 1;
		}
		if (token.equals("-")) {
			tok = 1;
		}
		int stk = 0;
		if (stack.equals("(")) {
			stk = 3;
		}
		if (stack.equals(")")) {
			stk = 3;
		}
		if (stack.equals("x")) {
			stk = 2;
		}
		if (stack.equals("/")) {
			stk = 2;
		}
		if (stack.equals("+")) {
			stk = 1;
		}
		if (stack.equals("-")) {
			stk = 1;
		}
		if (tok > stk) {
			return true;
		} else {
			return false;
		}
	}

  private boolean doCommand(String com)
  {
    boolean retval = false;
    Keywords command;

    try
    {
      command = Keywords.valueOf(com);// command is a Keywords and can
    }                                 // can be used as the target of a switch.
    catch (IllegalArgumentException e)
    {
      command = Keywords.UNDEFINED; //An undefined Keywords will cause
    }                               //an exception. 
    switch (command)
    {

case QUIT:
case EXIT: retval = true; 
           break;
case UNDEFINED: System.out.println("Undefined command:" + com);
    }
    return retval;
  }

  public static void main(String args[])
  {
    Editor e = new Editor();
    e.process();

  }
}*/
