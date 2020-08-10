/**
 * This function implements the transcendental function x^n
 * @version clean_code branch
 * @author Jingyi Lin, Shiyu Lin
 * @throws Exception 
 */

package Model;

public class XtoN {

	public static double Xton(double x, double n) throws Exception {
		/*
		 * if n is an integer, we can iterate to get the result if n is a
		 * rational number, we have to use the taylor expansion
		 */
		if (BuiltInFunctions.isRational(n)) {
			return BuiltInFunctions.taylor_expand(x, n);
		} else if (n < 0) {

			if (n % 2 == 0) {
				return Xton(1 / (x * x), -n / 2);
			}
			return Xton(1 / x, -n);

		} else if (n == 0) {
			return 1;
		} else if (n == 1) {
			return x;
		} else if (n % 2 == 0) {
			return Xton(x * x, n / 2);
		} else {
			return x * Xton(x * x, (n - 1) / 2);
		}

	}
}
