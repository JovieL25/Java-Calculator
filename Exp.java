package project;

public class Exp {
	
	protected static double exSum = 1; //exponential sum
	/*
	 * Function 4: e^x by Yilu Liang 
	 * 
	 * when using this function maximum x = 113, result = 1.188812691963352E49
	 * 
	 * @param x the user input
	 * @param n always 1
	 * @return exponential results
	 */
	public static double EXP(double x, int n) {
		if (x > 709)
			return Double.POSITIVE_INFINITY;
		if (n > 150) // n=150, xMAX = 113
			return exSum;
		exSum += BuiltInFunctionImplementation.XtoN(x, n) / BuiltInFunctionImplementation.factorial(n);
		return EXP(x, n + 1); // n factorial
	}
}
