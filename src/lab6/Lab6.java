package lab6;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Lab6 {

	public static void main(String[] args) {
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

	public static double infixCalculate(String[] tokens) {
		Queue a = new Queue();
		Stack b = new Stack();
		// use shunting yard
		for (int i = 0; i < tokens.length; i++) {
			if (isDouble(tokens[i])==true) {
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

	public static double postfix(Queue q) {
		Stack stack = new Stack();
		Stack a = new Stack();
		Stack b = new Stack();
		while (!q.isEmpty()) {
			try {
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
			} catch (QueueException e) {

				e.printStackTrace();
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
	private static boolean isDouble(String s) // see if a string represents
	{ // an integer.
		boolean retval = false;
		try {
			Double.parseDouble(s);
			retval = true;
		} catch (NumberFormatException e) {
			retval = false;
		}
		return retval;
	}
}
