package project;

/* This class implements all the built-in functions we need in the Math library */
public class BuiltInFunctionImplementation {

	/*
	 * absolute function
	 * */
	public static double abs(double arg) {
		double output = arg;
		if(arg < 0) {
			return -output;
		}
		return output;
	}
	
	//factorial function x! (by definition of factorial x can only be positive integer or 0)
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
	
	//basic functions
	//positive integer power function
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
