package project;

public class Sinh {

	/**
	 * Function 7: sinh by Ziqian
	 * 
	 * @param num
	 * @param isNumDegree
	 * @return sum
	 */
	public static double sinh(double num, boolean isNumDegree) {
		/* Degree to Radian */
		if (isNumDegree) {
			num = num * BuiltInFunctionImplementation.PI / 180;
		}

		double sum = num;
		double step = num;

		/* Compute until the value of step is smaller than 9 decimal places */
		int k = 2;
		while (Double.compare(step >= 0 ? step : step * (-1), BuiltInFunctionImplementation.accuracy) > 0) {
			step = step * num * num / (k * (k + 1));
			sum += step;

			if (sum == Double.POSITIVE_INFINITY) {
				throw new ArithmeticException("Positive Infinity");
			} else if (sum == Double.NEGATIVE_INFINITY) {
				throw new ArithmeticException("Negative Infinity");
			}
			k += 2;
		}

		return sum;
	}

}
