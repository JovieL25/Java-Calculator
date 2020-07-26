package project;

import java.util.ArrayList;

public class CalculatorFunctions {

	private static int maxIterations = 2000; // Increase for higher precision outputs, for ln()
	private static final double accuracy = 0.00001; // The accuracy for ln()
	private static final int precise = 20; // maxIterations for sin(x)
	protected static double exSum = 1; // exponential sum

	/*
	 * Function 1 Zhen's branch
	 */

	/**
	 * 
	 * Use Taylor series to perform an approximation of sin(x) in degrees
	 * 
	 * @param x
	 * @return result
	 */
	public static double sin(double x) {

		double result = 0;

		double fac;
		double input;

		if ((x * BuiltInFunctionImplementation.getPi() / 180) % BuiltInFunctionImplementation.getPi() == 0)
			return 0;
		else
			input = (x * BuiltInFunctionImplementation.getPi() / 180) % (2 * BuiltInFunctionImplementation.getPi());
		
		//What is this for???
		/*
		 *
		if ((30 * BuiltInFunctionImplementation.getPi() / 180) % input == 0 || (150 * BuiltInFunctionImplementation.getPi() / 180) % input == 0) // avoid some small inaccuracy
			return result = 0.5;

		if ((210 * BuiltInFunctionImplementation.getPi() / 180) % input == 0 || (330 * BuiltInFunctionImplementation.getPi() / 180) % input == 0)
			return result = -0.5;
		 */
		for (int i = 0; i <= precise; i++) {
			fac = 1;
			for (int j = 2; j <= 2 * i + 1; j++) {
				fac *= j;
			}

			result += BuiltInFunctionImplementation.posPow(-1.0, i)
					* BuiltInFunctionImplementation.posPow(input, 2 * i + 1) / fac;

		}
		return result;
	}

	/**
	 * Use Taylor series to perform an approximation of sin(x) in radians
	 * 
	 * @param x
	 * @return result
	 */
	public static double sinforR(double x) {

		double result = 0;
		double input;
		double fac;

		if (x % BuiltInFunctionImplementation.getPi() == 0)
			input = 0;
		else
			input = x % (2 * BuiltInFunctionImplementation.getPi());

		for (int i = 0; i <= precise; i++) {
			fac = factorial(2 * i + 1);
			result += BuiltInFunctionImplementation.posPow(-1.0, i)
					* BuiltInFunctionImplementation.posPow(input, 2 * i + 1) / fac;
		}
		return result;
	}

	//////////////////////////////////////////////////////////

	/*
	 * Function 2: 10^x by Jingyi Lin This class contains all methods required to
	 * compute the TF 10^x The methods include XtoN ln isRational taylor_expand
	 * factorial abs
	 */

	/**
	 * method isRational determine
	 * 
	 * @param d can be an integer or rational number
	 * @return true if d is rational
	 */
	public static boolean isRational(double d) {
		return !(d % 1 == 0);
	}

	/**
	 * method taylor_expand compute and return the taylor expansion of a^x
	 * 
	 * @param x the base number
	 * @param n the exponent number
	 * @return taylor expansion of the inputs
	 */
	public static double taylor_expand(double a, double x) {
		double result = 1;
		for (int i = 1; i < precise; i++) {
			result += XtoN(x * ln(a), i) / factorial(i);
		}
		return result;
	}

	/**
	 * method factorial compute and return the factorial(x)
	 * 
	 * @param x integer
	 * @return x!
	 */
	public static double factorial(int x) {
		if (x == 0 || x == 1)
			return 1;
		else
			return x * factorial(x - 1);
	}

	/**
	 * method XtoN compute and return the x^n
	 * 
	 * @param x double
	 * @param n double could be integer or rational number
	 * @return x^n
	 */
	public static double XtoN(double x, double n) {
		/*
		 * if n is an integer, we can iterate to get the result if n is a rational
		 * number, we have to use the taylor expansion
		 */
		if (isRational(n)) {
			return taylor_expand(x, n);
		} else if (n < 0)
			return XtoN(1 / x, -n);
		else if (n == 0)
			return 1;
		else if (n == 1)
			return x;
		else if (n % 2 == 0)
			return XtoN(x * x, n / 2);
		else
			return x * XtoN(x * x, (n - 1) / 2);

	}

	//////////////////////////////////////////////////////////

	/**
	 * Function 3: ln(x) by Derek Liu
	 * 
	 * method ln uses Talyor series approximations to find the value of ln(x)
	 * 
	 * @param x
	 * @return output
	 */
	public static double ln(double x) {

		double output = 0;

		double curValue = x - 1;
		if (x < 0) {
			// Could potentially implement error catching
			System.out.println("MATH ERROR");
			output = Integer.MIN_VALUE;
			return output;
		} else if (x < 1) {
			// Using Taylor series approximation for values < 1 as trapezoidal sums diverge
			// at ln(0)

			for (int i = 1; i < maxIterations; i++) {
				output += BuiltInFunctionImplementation.posPow(-1, (i + 1) % 2) * curValue / i;
				curValue *= x - 1;
			}
		} else if (x == 1) {
			output = 0;
		} else if (x > 1) {
			// Return 1 if x is very close to e
			if (BuiltInFunctionImplementation.abs(x - BuiltInFunctionImplementation.getE()) <= accuracy) {
				return 1;
			}
			// Converge the Taylor series until we hit our desired accuracy or we hit a max
			// number of iterations
			double base = (x - 1) / x;
			for (int i = 1; i < maxIterations; i++) {
				output += BuiltInFunctionImplementation.posPow(base, i) / i;
			}
		}
		return output;
	}

	//////////////////////////////////////////////////////////

	/*
	 * Function 4: e^x by Yilu Liang
	 * 
	 * when using this function maximum x = 113, result = 1.188812691963352E49
	 * 
	 * @param x the user input
	 * 
	 * @param n always 1
	 * 
	 * @return exponential results
	 */
	public static double EXP(double x, int n) {
		if (x > 709)
			return Double.POSITIVE_INFINITY;
		if (n > 150) // n=150, xMAX = 113
			return exSum;
		exSum += XtoN(x, n) / BuiltInFunctionImplementation.factorial(n);
		return EXP(x, n + 1); // n factorial
	}

	//////////////////////////////////////////////////////////

	/*
	 * Function 5: MAD by Xuan
	 *
	 * the string is first divided into several strings then an arrayList is used to
	 * save the input of the user, will write my own structure if this lib is not
	 * allowed. the counter is used to get how many numbers the user typed. what to
	 * do next: add exceptions(input error), allow user change their input before
	 * calculating save the equation for further check(to be decide)
	 * 
	 * @param str the user input
	 * 
	 * @return the MAD result
	 */

	public static double MAD(String str) {
		ArrayList<Double> list = new ArrayList<>();
		int counter = 0;
		double total = 0;
		double difInTotal = 0;

		if (str.equals(""))
			return 0;
		double result = 0;
		String[] s = str.split(",");
		for (int i = 0; i < s.length; i++) {
			double temp = Double.valueOf(s[i]);
			list.add(temp);
			total += temp;
			counter++;
		}

		if (counter == 0)
			result = 0;
		else {
			double avg = total / counter;
			for (int i = 0; i < list.size(); i++) {
				difInTotal += BuiltInFunctionImplementation.abs(list.get(i) - avg);
			}
			result = difInTotal / counter;
		}
		return result;

	}

	//////////////////////////////////////////////////////////

	/**
	 * Function 7: sinh by Ziqian
	 * 
	 * @param num
	 * @param isNumDegree
	 * @return sum
	 */
	public static double sinh(double num, boolean isNumDegree) {

		/* Degree to Radian */
		if (isNumDegree) {
			num = num * BuiltInFunctionImplementation.getPi() / 180;
		}

		double sum = num;
		double step = num;

		/* Compute until the value of step is smaller than 9 decimal places */
		int k = 2;
		while (Double.compare(step >= 0 ? step : step * (-1), accuracy) > 0) {
			step = step * num * num / (k * (k + 1));
			sum += step;

			if (sum == Double.POSITIVE_INFINITY) {
				throw new ArithmeticException("Positive Infinity");
			} else if (sum == Double.NEGATIVE_INFINITY) {
				throw new ArithmeticException("Negative Infinity");
			}
			k += 2;
		}

		return sum;
	}

	//////////////////////////////////////////////////////////

	/**
	 * Function 8: x^y by Shiyu Lin
	 * 
	 * @param x
	 * @param y
	 * @return result
	 */
	public static double xPowY(double x, double y) {
		// x and y are both real numbers
		// 1. special case when x = 0
		int test = (int) y;
		if (y == test && y != 0 && x != 0) {
			double result = 1.0;
			if (test < 0) {
				x = 1 / x;
			}
			for (int i = 0; i < BuiltInFunctionImplementation.abs(y); i++) {
				result = result * x;
			}
			return result;
		} else {
			if (x == 0) {
				// 1. if y <= 0
				if (y == 0 || y < 0) {
					System.out.println("Math Error!");
				}
				// 2. if y > 0
				if (y > 0) {
					return 0.0;
				}
			}
			// 2. if x is a negative real number or positive real number
			else if (x > 0 || x < 0) {
				if (y == 0)// exponential is zero always return 1.0
				{
					return 1.0;
				} else {
					double result = 0.0;
					// calculate y * ln(x)
					double temp = y * CalculatorFunctions.ln(x);
					result = CalculatorFunctions.EXP(temp, 1);
					return result;
				}
			}
		}
		return (0.0);

	}

}
