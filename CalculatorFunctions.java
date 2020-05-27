import java.lang.Math;

public class CalculatorFunctions {
	private static int maxIterations = 200; // Increase for higher precision outputs
	private static final double accuracy = 0.00001;
	public static final double e = 2.718281828459;

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
	
	public static double xExpY(double x, double y) {
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
			//don't know if we could use Math lib directly
			if(y > 0)// exponential is greater than zero, use Math.pow() to return a value
			{
				return Math.pow(x, y);
			}
			if(y < 0)// exponential is less than zero, use Math.pow() then inverse the result
			{
				return(1/(Math.pow(x, y)));
			}
		}
	
		return(-0.9999999999);
		
	}
}
