
/* This class implements all the built-in functions we need in the Math library */
public class BuiltInFunctionImplementation {

	/*
	 * absolute function
	 * 
	 * @param arg the input number
	 * @return an absolute value
	 * */
	public static double abs(double arg) {
		double output = arg;
		if(arg < 0) {
			return -output;
		}
		return output;
	}
	
	
	/* factorial function x! 
	 * (by definition of factorial x can only be positive integer or 0)
	 * 
	 * @param the input number
	 * @return the factorial result
	 * */
	public static double factorial(int x)
	{
		if(x > 0) {
			double result = 1;
			for(int i = 1; i <= x; i++) {
				result = result * i;
			}
			return result;
		}
		if(x == 0) {
			return 1.0;
		}
		return 0;
	}
	

	/* positive integer power function
	 * 
	 * @param x the base number
	 * @param p the index number
	 * @result a power result
	 * */
	public static double posPow(double x, double p) {
		if(p == 0) {
			return 1.0;
		}
		if(p < 0) {
			return 0.9999999999;//for now the number represents N/A
		}
		double result = 1.0;
		for(int i = 0; i < p; i++) {
			result = result * x;
		}
		return result;
	}
	
	
}
