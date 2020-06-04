import java.lang.Math;

public class CalculatorFunctions {
	private static int maxIterations = 200; // Increase for higher precision outputs
	private static final double accuracy = 0.00001;
	public static final double e = 2.718281828459;

	public static double abs(double arg) {
		double output = arg;
		if(arg < 0) {
			return -output;
		}
		return output;
	}
	
	public static double ln(double arg) {

		double output = 0;
		double realValue = Math.log(arg);

		// Using Taylor series approximation for values < 1 as trapezoidal sums diverge
		// at ln(0)

		double curValue = arg - 1;
		if (arg < 1) {
			int i = 1;
			// Converge the Taylor series until we hit our desired accuracy or we hit a max number of iterations
			while (CalculatorFunctions.abs(CalculatorFunctions.abs(realValue) - CalculatorFunctions.abs(output)) >= accuracy || i == maxIterations) {
				// change Math.pow to our own pow function
				output += Math.pow(-1, (i + 1) % 2) * curValue / i;
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
			while (CalculatorFunctions.abs(CalculatorFunctions.abs(realValue) - CalculatorFunctions.abs(output)) >= accuracy || i == maxIterations) {
				// change Math.pow to our own pow function
				output += Math.pow(base, i) / i;
				i++;
			}
		}
		return output;
	}
}
