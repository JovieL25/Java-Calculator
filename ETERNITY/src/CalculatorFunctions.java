package project.ETERNITY.src;

import java.util.ArrayList;

public class CalculatorFunctions {

	private static int maxIterations = 200; // Increase for higher precision outputs, for ln()
	private static final double accuracy = 0.00001; // The accuracy for ln()
	public static final double e = 2.718281828459;
	static double exSum = 1; // The result for exp function
	private static final double PI = 3.14159265359;

	/*
	 * Function 1 Zhen's branch
	 */
	private double input;
	private int precise = 10;
	private final double pi = 3.14159265359;
	double result;

	public void sin(double x) {

		input = x;
		sin(input, precise);

	}
	public void sin(double x, int precise) {

		input = x;
		this.precise = precise;
		sin(input, precise);

	}
	/*
	 * use Taylor series to implement sine, precise represents the nth term in the
	 * series, the default is 10 it also represents the accuracy of the function
	 * 
	 */
	public double sin_taylor(double x, int precise) {
		result = 0;

		int fac;

		input = x * pi / 180;

		for (int i = 0; i <= precise; i++) {
			fac = 1;
			for (int j = 2; j <= 2 * i + 1; j++) {
				fac *= j;
			}

			result += mypow(-1.0, i) * mypow(input, 2 * i + 1) / fac;

		}
		return result;
	}
	public double mypow(double x, int index) {

		double r = 1;

		for (int i = 0; i < index; i++) {

			r *= x;
		}

		return r;
	}

	//////////////////////////////////////////////////////////

	/*
	 * Function 2: 10^x by Jingyi Lin
	 * 
	 */
	public static boolean isRational(double d) {
		return !(d % 1 == 0);
	}
	public static double taylor_expand(double x, double n) {
		double result = 1;
		for (int i = 1; i < 30; i++) {
			result += XtoN(n * ln(x), i) / factorial(i);
		}
		return result;
	}
	public static int factorial(int x) {
		if (x == 0 || x == 1)
			return 1;
		else
			return x * factorial(x - 1);
	}
	public static double XtoN(double x, double n) {
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
	
	/*
	 * Function 3: ln(x) by Dereck Liu
	 * 
	 */
	public static double ln(double arg) {

		double output = 0;
		double realValue = Math.log(arg);

		// Using Taylor series approximation for values < 1 as trapezoidal sums diverge
		// at ln(0)

		double curValue = arg - 1;
		if (arg < 1) {
			int i = 1;
			// Converge the Taylor series until we hit our desired accuracy or we hit a max
			// number of iterations
			while (BuiltInFunctionImplementation.abs(BuiltInFunctionImplementation.abs(realValue)
					- BuiltInFunctionImplementation.abs(output)) >= accuracy || i == maxIterations) {
				// change Math.pow to our own pow function
				output += BuiltInFunctionImplementation.posPow(-1, (i + 1) % 2) * curValue / i;
				curValue *= arg - 1;
				i++;
			}
		} else if (arg == 1) {
			output = 0;
		} else if (arg > 1) {
			if (arg == e) {
				return 1;
			}
			double i = 1;
			double base = (arg - 1) / arg;
			// Taylor series expansion of ln(x) centered at x > 1/2
			while (BuiltInFunctionImplementation.abs(BuiltInFunctionImplementation.abs(realValue)
					- BuiltInFunctionImplementation.abs(output)) >= accuracy || i == maxIterations) {
				// change Math.pow to our own pow function
				output += BuiltInFunctionImplementation.posPow(base, i) / i;
				i++;
			}
		}
		return output;
	}
	
	//////////////////////////////////////////////////////////
	
	/*
	 * Function 4: e^x by Yilu Liang double x is the user input int n is always 1
	 * when using this function maximum x = 113, result = 1.188812691963352E49
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
	 * an arrayList is used to save the input of the user, will write my own
	 * structure if this lib is not allowed. the counter is used to get how many
	 * numbers the user typed. what to do next: add exceptions(input error), allow
	 * user change their input before calculate,allow double input
	 */
	public static double MAD(String str) {

		ArrayList<number> list = new ArrayList<>();
		int counter = 0;
		int total = 0;
		double defInTotal = 0;

		if (str.equals(""))
			return 0;
		double result = 0;
		String[] s = str.split(",");
		for (int i = 0; i < s.length; i++) {
			int temp = Integer.valueOf(s[i]);
			list.add(new number(temp));
			total += temp;
			counter++;
		}

		if (counter == 0)
			result = 0;
		else {
			double avg = (double) total / counter;
			for (int i = 0; i < list.size(); i++) {
				defInTotal += BuiltInFunctionImplementation.abs(list.get(i).n - avg);
			}
			result = defInTotal / counter;
		}
		// System.out.println(total+" "+counter+" "+defInTotal);
		return result;

	}

	static class number {
		int n;

		number(int n) {
			this.n = n;
		}
	}
	
	//////////////////////////////////////////////////////////
	
	/*
	 * Function 7: sinh by Ziqian
	 * 
	 */
	public static double sinh(double num, boolean isNumDegree) {

        /* Degree to Radian */
        if (isNumDegree) {
            num = num * PI / 180;
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
	
	/*
	 * Function 8: x^y by Shiyu Lin
	 * 
	 */
	public static double xPowY(double x, double y) {
		// x and y are both real numbers
		// 1. special case when x = 0
		if (x == 0) {
			// 1. if y <= 0
			if (y == 0 || y < 0) {
				System.out.println("Math Error!");
				return (-0.9999999999);// for now the value returned is just to indicate error
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
				// return Math.pow(x, y);
			}
		}

		return (-0.9999999999);

	}

	public static double ln_jingyi(double x) {
		/*
		 * this method takes the approximation method of finding ln The procedure is: 1.
		 * write x in A*10^(n-1) 1<=A<=10 2. compute y = (A-1)/(A+1) 3. calculate SP =
		 * 2*sigma_k=0 k->inf ((y^(2k+1))/(2k+1)) 4. ln(x) = n*log(10)+Sp
		 * 
		 * Notice ln(10) = 2.302585092994046 src:
		 * https://math.stackexchange.com/questions/977586/is-there-an-approximation-to-
		 * the-natural-log-function-at-large-values
		 */

		double SP = 0;
		String a = String.format("%1.30e", x); // format double to scientific notation
		a = a.split("e")[0];
		int n = String.valueOf(Double.parseDouble(a)).length() - 2;
		/* integer n being the number of decimal after turn into scientific notation */

		double y = Double.parseDouble(a);
		y = (y - 1) / (y + 1);

		for (int i = 0; i < 10; i++)
			SP += 2 * (XtoN(y, 2 * i + 1) / (2 * i + 1));

		return n * 2.302585092994046 + SP;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double result = CalculatorFunctions.xPowY(10, 1.0 / 7);
		System.out.println(sinh(1,false));
	}

}
