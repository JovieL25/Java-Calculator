/*
 * CalculatorFunctions
 * Version 1.1 (not updated)
 * This class implements calculator functions
 */

package project;

import java.util.ArrayList;

public class CalculatorFunctions {

	/*
	 * Function 2: 10^x by Jingyi Lin This class contains all methods required to
	 * compute the TF 10^x The methods include XtoN ln isRational taylor_expand
	 * factorial abs
	 */
	/**
	 * method XtoN compute and return the x^n
	 * @author Jingyi Lin
	 * @param x double
	 * @param n double could be integer or rational number
	 * @return x^n
	 */
	public static double XtoN(double x, double n) {
		/*
		 * if n is an integer, we can iterate to get the result if n is a rational
		 * number, we have to use the taylor expansion
		 */
		if (BuiltInFunctionImplementation.isRational(n)) {
			return BuiltInFunctionImplementation.taylor_expand(x, n);
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
		int test = (int)y;
		if(y == test && y != 0 && x != 0)
		{
			double result = 1.0;
			if(test < 0) {
				x = 1/x;
			}
			for(int i = 0; i < BuiltInFunctionImplementation.abs(y); i++) {
				result = result * x;
			}
			return result;
		}
		else {
			if(x == 0)
			{
				// 1. if y <= 0
				if(y == 0 || y < 0)
				{
					System.out.println("Math Error!");
				}
				// 2. if y > 0
				if(y > 0)
				{
					return 0.0;
				}
			}
			//2. if x is a negative real number or positive real number
			else if(x > 0 || x < 0)
			{
				if(y == 0)// exponential is zero always return 1.0
				{
					return 1.0;
				}
				else
				{
					double result = 0.0;
					//calculate y * ln(x)
					double temp = y * BuiltInFunctionImplementation.ln(x);
					try {
						result = Exp.EXP(temp,1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return result;
				}
			}
		}
		return(0.0);
		
	}
	
}
