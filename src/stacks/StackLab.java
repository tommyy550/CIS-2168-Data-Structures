package stacks;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackLab {
	private static Queue<Person> app = new LinkedList<Person>();
	private static Stack<Person> workers = new Stack<Person>();
	private static Queue<Person> firedWorkers = new LinkedList<Person>();
	static Scanner sc = new Scanner(System.in);

	public static void acceptAPP() { 
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your name?");
		String name = sc.nextLine();
		System.out.println("What is your SSN?");
		String SSN = sc.nextLine();
		Person applicant = new Person(name, SSN);
		app.add(applicant);
	}

	public static void fire() { 
		if (!workers.isEmpty()) {
			Person temp = workers.peek();
			firedWorkers.add(workers.pop());
			System.out.println(temp.getName() + " " + temp.getSSN() + " fired");
		} else {
			System.out.println("There is nobody to fire.");
		}
	}

	public static void hire() {
		if (app.isEmpty() && firedWorkers.isEmpty()) {
			System.out.println("There is nobody to hire.");
		} else if (firedWorkers.isEmpty()) {
			Person temp = app.peek();
			workers.push(app.remove());
			System.out.println(temp.getName() + " " + temp.getSSN() + " hired");
		} else {
			Person temp = firedWorkers.peek();
			workers.push(firedWorkers.remove());
			System.out.println(temp.getName() + " " + temp.getSSN() + " hired.");
		}
	}

	public static void main(String[] args) {
		boolean done = false;
		while (!done) {
			System.out.println("Press 1 to accept, 2 to hire, 3 to fire, or 4 to quit");
			int action = sc.nextInt();
			if (action == 1) {
				acceptAPP();
			} else if (action == 2) {
				hire();
			} else if (action == 3) {
				fire();
			} else if (action == 4) {
				done = true;
			}
		}
	}
}
