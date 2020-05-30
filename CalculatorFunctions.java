import java.lang.Math;

public class CalculatorFunctions {
	private static int maxIterations = 200; // Increase for higher precision outputs
	private static final double accuracy = 0.00001;
	public static final double e = 2.718281828459;
	
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
	
	public static double ln(double arg) {

		double output = 0;
		double realValue = Math.log(arg);

		// Using Taylor series approximation for values < 1 as trapezoidal sums diverge
		// at ln(0)

		double curValue = arg - 1;
		if (arg < 1) {
			int i = 1;
			//Converge the Taylor series until we hit our desired accuracy or we hit a max number of iterations
			while (Math.abs(Math.abs(realValue) - Math.abs(output)) >= accuracy || i == maxIterations) {
				output += Math.pow(-1, (i + 1) % 2) * curValue / i;
				curValue *= arg - 1;
				i++;
				System.out.println(i);
			}
		} else if (arg == 1) {
			output = 0;
			// For values > 1 use a trapezoidal sum
		} else if (arg > 1) {
			output = Math.log(arg);
		}

		return output;
	}
	
	//parameter x : x represents the exponential of e^x
	public static double exp(double x)
	{
		//the idea behind the exp( ) function is Taylor series
		//formula: sum(from n = 0 to n = +inf) x^n/n!
		//Since the program could not go literally to infinity, limit the maximum iteration to 100
		double result = 0.0;
		for(int i = 0; i < 150; i++) {
			result += (CalculatorFunctions.posPow(x, i))/(CalculatorFunctions.factorial(i));
		}
		return result;
		
	}
	
	public static double xPowY(double x, double y) {
		// x and y are both real numbers  
		// 1. special case when x = 0 
		if(x == 0)
		{
			// 1. if y <= 0
			if(y == 0 || y < 0)
			{
				System.out.println("Math Error!");
				return(-0.9999999999);//for now the value returned is just to indicate error
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
				double temp = y * CalculatorFunctions.ln(x);
				result = CalculatorFunctions.exp(temp);
				return result;
				//return Math.pow(x, y);
			}
		}
	
		return(-0.9999999999);
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double result = CalculatorFunctions.xPowY(10, 15);
		
		
		System.out.println(result);
		
		System.out.println(Math.pow(5.1, 2));
	}
}
