
package Model;

/*
 * This class implements the transcendental function, exponential function 
 * @version clean_code branch July 2020
 * @author Yilu Liang
 */
public class Exp {
	
	/**
	 * exSum stores the exponential result for each calculation
	 * need to be reset to 1 after each completion
	 */
	public static double exSum = 1;
	
	/*
	 * Function 4: e^x
	 * Return the Exponential function result
	 * maximum x = 113, result = 1.188812691963352E49
	 * 
	 * @param x the user input
	 * @param n is tracking the iteration 
	 * @return exponential results
	 */
	public static double EXP(double x, int n) throws Exception {
		if (x > 709)
			return Double.POSITIVE_INFINITY;
		if (n > 150) // n=150, xMAX = 113
			return exSum;
		exSum += BuiltInFunctionImplementation.XtoN(x, n) / BuiltInFunctionImplementation.factorial(n);
		return EXP(x, n + 1); // n factorial
	}
}
