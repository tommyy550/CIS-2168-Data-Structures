package Lab7;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import lab6.QueueException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Editor {

	private static boolean isDouble(String s) // see if a string represents
	{ // a double
		boolean retval = false;
		try {
			Double.parseDouble(s);
			retval = true;
		} catch (NumberFormatException e) {
			retval = false;
		}
		return retval;
	}

	public static double infixCalculate(String[] tokens) throws QueueException {
		Queue a = new Queue();
		Stack b = new Stack();
		
		// use shunting yard
		for (int i = 0; i < tokens.length; i++) {
			if (isDouble(tokens[i])==true) {
				a.enqueue(tokens[i]);
			} else if (tokens[i].equals("(")) {
				b.push(tokens[i]);
			} else if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*")
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
		Stack a = new Stack();
		Stack b = new Stack();
		while (!q.isEmpty()) {
			String token = (String) q.dequeue();
			if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
				if (token.equals("+")) {
					stack.push((double) stack.pop() + (double) stack.pop());
				}
				if (token.equals("-")) {
					stack.push((double) stack.pop() - (double) stack.pop());
				}
				if (token.equals("*")) {
					stack.push((double) stack.pop() * (double) stack.pop());
				}
				if (token.equals("/")) {
					a.push(stack.pop());
					b.push(stack.pop());
					stack.push(a.pop());
					stack.push(b.pop());
					stack.push((double) stack.pop() / (double) stack.pop());
				}
			}
			if (isDouble(token)==true) {
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
		if (token.equals("*")) {
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
		if (stack.equals("*")) {
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

	public static void main(String args[]) throws NumberFormatException, HashException, QueueException
  {
		Dictionary d =new Dictionary(10);
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		String line;
		while (!done){
			System.out.println("enter command");
			line = sc.nextLine().toUpperCase(); // Work only with upper case
			String splitString[] = line.split(" ", 4);
			if (splitString[0].equals("LET")){ 
				StringTokenizer tok = new StringTokenizer(splitString[3], "+-*/()", true);
				String[] tokens = new String[100];
				int where = 0;
				while (tok.hasMoreTokens()) {
					tokens[where] = tok.nextToken();
					where++;
				}
				String[] realTokens = new String[where];		
				for (int i = 0; i < where; i++) {
					if (isDouble(tokens[i])==true) {
						realTokens[i] = tokens[i];
					}
					else if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*")
							|| tokens[i].equals("/")||tokens[i].equals("(")||tokens[i].equals(")")) {
						realTokens[i] = tokens[i];
					}
					else {//is a character so have to turn into its value
						realTokens[i]=Double.toString(d.lookup(tokens[i]));
					}
				}	
				d.insert(splitString[1], infixCalculate(realTokens));
			}
			else if(splitString[0].equals("PRINT")) {
				System.out.println(d.lookup(splitString[1]));
			}
			else if(splitString[0].equals("QUIT")) {
				done=true;
			}
			else {
				System.out.println("Invalid Command");
			}
					
		}
		
  }
}
