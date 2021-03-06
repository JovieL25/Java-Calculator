/*
 * BuiltInFunctionImplementation
 * Version clean_code branch
 * This class implements all the built-in functions we need in the Math library
 */

package Model;

public class BuiltInFunctions {

	public static int maxIterations_small = 400;
	public static int maxIterations_middle = 1000;
	public static int maxIterations_large = 5000;
	public static final double e = getE();
	public static final double PI = getPi();
	public static final double accuracy = 0.00001; // The accuracy for ln()
	public static final int precise = 100; // maxIterations for sin(x)

	// Newton's method for square root
	public static double sqrt(double x) {
		double x_iteration = 10;

		for (int i = 0; i < 1000; i++) {
			x_iteration = x_iteration
					- (x_iteration * x_iteration - x) / (2 * x_iteration);
		}
		return x_iteration;

	}

	// nilakantha series for pi
	public static double getPi() {
		double pi = 3;
		double denominator = 2;
		for (int i = 0; i < 500; i++) {

			if (i % 2 == 0) {
				pi += 4 / (denominator * ++denominator * ++denominator);
			}

			if (i % 2 == 1) {
				pi -= 4 / (denominator * ++denominator * ++denominator);
			}
		}
		return pi;
	}

	// euler's number to get e
	public static double getE() {
		double e = 1 + 1.0 / 1000000;
		try {
			return BuiltInFunctions.XtoN(e, 1000000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Cannot get E");
		}
		return 0;
	}

	/*
	 * absolute function
	 * 
	 * @param arg the input number
	 * 
	 * @return an absolute value
	 */
	public static double abs(double arg) {
		double output = arg;
		if (arg < 0) {
			return -output;
		}
		return output;
	}

	/*
	 * factorial function x! (by definition of factorial x can only be positive
	 * integer or 0)
	 * 
	 * @param the input number
	 * 
	 * @return the factorial result
	 */
	public static double factorial(int x) throws Exception {

		if (x > 0) {
			double result = 1;
			for (int i = 1; i <= x; i++) {
				result = result * i;
			}
			return result;
		}

		if (x == 0) {
			return 1.0;
		}

		throw new Exception("Factorial input out of range");

	}

	/*
	 * positive integer power function
	 * 
	 * @param x the base number
	 * 
	 * @param p the index number
	 * 
	 * @result a power result
	 */
	public static double posPow(double x, double p) {

		if (p == 0) {
			return 1.0;
		}

		if (p < 0) {
			return 0.9999999999;// for now the number represents N/A
		}

		double result = 1.0;
		for (int i = 0; i < p; i++) {
			result = result * x;
		}
		return result;
	}

	/**
	 * method isRational determine if a number is rational number or not
	 * 
	 * @param d
	 *            can be an integer or rational number
	 * @return true if d is rational
	 */
	public static boolean isRational(double d) {
		return (d%1 != 0);
	}

	/**
	 * method taylor_expand compute and return the taylor expansion of a^x
	 * 
	 * @param a
	 *            the base number
	 * @param x
	 *            the exponent number
	 * @return taylor expansion of the inputs
	 * @throws Exception
	 */
	public static double taylor_expand(double a, double x) throws Exception {
		double result = 1;
		for (int i = 1; i < maxIterations_small; i++) {
			result += Model.XtoN.Xton(x * Ln.ln2(a), i) / factorial(i);
		}
		return result;
	}

	/**
	 * Function 3: ln(x) by Derek Liu
	 * 
	 * method ln uses Talyor series approximations to find the value of ln(x)
	 * 
	 * @param x
	 * @return output
	 * @throws Exception
	 */
	public static double ln(double x) throws Exception {

		double output = 0;
		double curValue = x - 1;

		if (x < 0) {
			// Could potentially implement error catching
			System.out.println("MATH ERROR");
			output = Integer.MIN_VALUE;
			return output;
		} else if (x < 1) {
			// Using Taylor series approximation for values < 1 as trapezoidal
			// sums diverge
			// at ln(0)

			for (int i = 1; i < maxIterations_small; i++) {
				output += BuiltInFunctions.XtoN(-1, (i + 1) % 2)*curValue/i;
				curValue *= x - 1;
			}

		} else if (x == 1) {
			output = 0;
		} else if (x > 1) {
			// Return 1 if x is very close to e
			if (BuiltInFunctions.abs(x - e) <= accuracy) {
				return 1;
			}
			// Converge the Taylor series until we hit our desired accuracy or
			// we hit a max
			// number of iterations
			double base = (x - 1) / x;
			for (int i = 1; i < maxIterations_small; i++) {
				output += BuiltInFunctions.XtoN(base, i) / i;
			}
		}
		return output;
	}

	/**
	 * This function implements the transcendental function x^n
	 * 
	 * @version clean_code branch
	 * @author Jingyi Lin
	 * @throws Exception
	 */
	public static double XtoN(double x, double n) throws Exception {
		/*
		 * if n is an integer, we can iterate to get the result if n is a
		 * rational number, we have to use the taylor expansion
		 */
		if (isRational(n)) {
			return taylor_expand(x, n);
		} else if (n < 0) {
			return XtoN(1 / x, -n);
		} else if (n == 0) {
			return 1;
		} else if (n == 1) {
			return x;
		} else if (n % 2 == 0) {
			return XtoN(x * x, n / 2);
		} else {
			return x * XtoN(x * x, (n - 1) / 2);
		}

	}

}
