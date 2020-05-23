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
}
