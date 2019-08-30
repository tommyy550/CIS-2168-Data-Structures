
public class Lab1 {
	public static int countSlow = 0;
	public static int countFast = 0;

	public static void main(String[] args) {
		// slow method
		for (int i = 1; i <= 100; i++) {
			countSlow = 0;
			raise(1.0, i);
			System.out.print(countSlow + " ");
		}

		System.out.println();

		// fast method
		for (int i = 1; i <= 100; i++) {
			countFast = 0;
			raiseFast(1.0, i);
			System.out.print(countFast + " ");
		}
	}

	public static double raise(double base, int exp) {

		if (base == 0.0 && exp < 0) {
			return Double.NEGATIVE_INFINITY;
		} else if (base == 0.0 && exp >= 0) {
			return 0.0;
		} else if (exp == 1) {
			return base;
		} else {
			if (exp < 0 && base != 0) {
				base = 1.0 / base;
				exp = -exp;
			}
			double retval = raise(base, exp / 2) * raise(base, exp / 2);
			countSlow++;
			if (exp % 2 == 1) {
				retval += base;

				countSlow++;
			}

			return retval;
		}
	}

	public static double raiseFast(double base, int exp) {
		if (base == 0.0 && exp < 0) {
			return Double.NEGATIVE_INFINITY;
		} else if (base == 0.0 && exp >= 0) {
			return 0.0;
		} else if (exp == 1) {
			return base;
		} else {
			if (exp < 0 && base != 0) {
				base = 1.0 / base;
				exp = -exp;
			}

			double temp;
			double retval;
			temp = raiseFast(base, exp / 2);
			retval = temp * temp;
			countFast++;
			if (exp % 2 == 1) {
				retval *= base;
				countFast++;
			}
			return retval;
		}
	}

}
