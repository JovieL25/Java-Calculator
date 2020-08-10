/*
 * This class implements the transcendental function, ln(x) function 
 * @version clean_code branch July 2020
 * @author Derek Liu
 */

package Model;

public class Ln {

	/**
	 * Function 3: ln(x) method ln uses Talyor series approximations to find the
	 * value of ln(x)
	 * 
	 * @param x
	 *            is user input
	 * @return output
	 */
	public static double ln(double x) throws Exception {
		double output = 0;
		double curValue = x - 1;
		if (x <= 0) {
			// Could potentially implement error catching
			throw new Exception("Ln input out of range");
		} else if (x < 1) {
			// Using Taylor series approximation for values < 1 as trapezoidal
			// sums diverge
			// at ln(0)

			for (int i = 1; i < BuiltInFunctions.maxIterations_small; i++) {
				output += BuiltInFunctions.posPow(-1, (i + 1) % 2) * curValue
						/ i;
				curValue *= x - 1;
			}
		} else if (x == 1) {
			output = 0;
		} else if (x > 1) {
			// Return 1 if x is very close to e
			if (BuiltInFunctions
					.abs(x - BuiltInFunctions.e) <= BuiltInFunctions.accuracy) {
				return 1;
			}
			// Converge the Taylor series until we hit our desired accuracy or
			// we hit a max
			// number of iterations
			double base = (x - 1) / x;
			
			for (int i = 1; i < BuiltInFunctions.maxIterations_large; i++) {
				output += BuiltInFunctions.posPow(base, i) / i;
			}
		}
		return output;
	}

	/**
	 * Function 3: ln2(x) is a quicker version of original ln function
	 * the value of ln(x)
	 * 
	 * @author Jingyi Lin
	 * @param x
	 *            is user input
	 * @return output
	 */
	public static double ln2(double x) throws Exception {
		double output = 0;
		double curValue = x - 1;
		if (x <= 0) {
			// Could potentially implement error catching
			throw new Exception("Ln input out of range");
		} else if (x < 1) {
			// Using Taylor series approximation for values < 1 as trapezoidal
			// sums diverge
			// at ln(0)

			for (int i = 1; i < BuiltInFunctions.maxIterations_small; i++) {
				output += BuiltInFunctions.posPow(-1, (i + 1) % 2) * curValue
						/ i;
				curValue *= x - 1;
			}
		} else if (x == 1) {
			output = 0;
		} else if (x > 1) {
			// Return 1 if x is very close to e
			if (BuiltInFunctions
					.abs(x - BuiltInFunctions.e) <= BuiltInFunctions.accuracy) {
				return 1;
			}
			// Converge the Taylor series until we hit our desired accuracy or
			// we hit a max
			// number of iterations
			double base = (x - 1) / x;
			
			for (int i = 1; i < BuiltInFunctions.maxIterations_middle; i++) {
				output += BuiltInFunctions.posPow(base, i) / i;
			}
		}
		return output;
	}
}
