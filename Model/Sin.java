/*
 * This class implements the transcendental function, sin(x) function
 * @version clean_code branch July 2020
 * @author Long Zhen
 */
package Model;

public class Sin {

	/**
	 * 
	 * Use Taylor series to perform an approximation of sin(x) in degrees
	 * 
	 * @param x
	 * @return result
	 */
	public static double sin(double x) {

		double result = 0;

		double fac;
		double input;

		if ((x*BuiltInFunctions.PI/180)%BuiltInFunctions.PI== 0 )
			 return 0;
		else
			input = (x*BuiltInFunctions.PI / 180)%(2*BuiltInFunctions.PI);
		
		if ((30*BuiltInFunctions.PI/180)% input== 0 
				||(150*BuiltInFunctions.PI/180)%input== 0) // avoid some small inaccuracy
			return result = 0.5;
		
		if ( (210*BuiltInFunctions.PI/180)% input== 0 
				||(330*BuiltInFunctions.PI/180)% input== 0)
			return result = -0.5;

		for (int i = 0; i <= BuiltInFunctions.precise; i++) {
			fac = 1;
			for (int j = 2; j <= 2 * i + 1; j++) {
				fac *= j;
			}

			result += BuiltInFunctions.posPow(-1.0, i)
					* BuiltInFunctions.posPow(input, 2 * i + 1) / fac;

		}
		return result;
	}

	/**
	 * Use Taylor series to perform an approximation of sin(x) in radians
	 * 
	 * @param x
	 * @return result
	 * @throws Exception 
	 */
	public static double sinforR(double x) throws Exception {

		double result = 0;
		double input;
		double fac;

		if (x%BuiltInFunctions.PI== 0 )
			 input = 0;
		else
			
			input = x %(2*BuiltInFunctions.PI);

		for (int i = 0; i <= 200; i++) {
			fac = BuiltInFunctions.factorial(2*i+1);
			result += BuiltInFunctions.posPow(-1.0, i)
					* BuiltInFunctions.posPow(input, 2 * i + 1) / fac;
		}

		return result;
	}

}
