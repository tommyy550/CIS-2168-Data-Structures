package trees;

import java.util.Arrays;
import java.util.Scanner;

public class Lab4 {
	static int[] depthcount;
	static int j = -1;

	public static void permute(int vals[], int start, int size) throws BSTException {
		for (int i = start; i < size; i++) {
			swap(vals, i, start);
			permute(vals, start + 1, size);
			swap(vals, start, i);
		}
		if (start == size - 1) {
			BST<Integer> bst = new BST<Integer>();
			j++;
			for (int i = 0; i < size; i++) {
				bst.insert(vals[i]);
			}
			int depth = bst.depth();
			depthcount[j] = depth;
		}
	}

	public static void swap(int v[], int i, int k) {
		int temp = v[i];
		v[i] = v[k];
		v[k] = temp;
	}

	public static int factorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		} else {
			return factorial(n - 1) * n;
		}

	}

	public static double average(int[] arr) {
		double sum = 0;
		for (double i = 0; i < arr.length; i++) {
			sum += arr[(int) i];
		}
		return sum / arr.length;
	}

	public static int median(int[] arr) {
		Arrays.sort(arr);
		return arr[arr.length / 2];

	}
	
	public static void main(String args[]) throws BSTException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter n: ");
		int n = sc.nextInt();
		depthcount = new int[factorial(n)];
		int vals[] = new int[n];
		for (int i = 0; i < n; i++) {
			vals[i] = i;
		}
		permute(vals, 0, n);
		for (int i = 0; i < factorial(n); i++) {
			System.out.println(depthcount[i]);
		}
		System.out.println("The average depth is:" + average(depthcount));
		System.out.println("The median depth is:" + median(depthcount));
	}

	

}
