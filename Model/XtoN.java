package Model;

public class XtoN {
	
	
	/**
	 * This function implements the transcendental function x^n
	 * @version clean_code branch
	 * @author Jingyi Lin
	 * @throws Exception 
	 * */
	public static double Xton(double x, double n) throws Exception{
		/*
		 * if n is an integer, we can iterate to get the result if n is a rational
		 * number, we have to use the taylor expansion
		 */
<<<<<<< HEAD
		if (BuiltInFunctionImplementation.isRational(n)) {
			return BuiltInFunctionImplementation.taylor_expand(x, n);
=======
		if (BuiltInFunctions.isRational(n)) {
			return BuiltInFunctions.taylor_expand(x, n);
>>>>>>> master
		} else if (n < 0)
			return Xton(1 / x, -n);
		else if (n == 0) {
			return 1;	
		}
		else if (n == 1)
			return x;
		else if (n % 2 == 0)
			return Xton(x * x, n / 2);
		else
			return x * Xton(x * x, (n - 1) / 2);

	}

	/**
	 * Function 8: x^y by Shiyu Lin
	 * 
	 * @param x
	 * @param y
	 * @return result
	 * @throws Exception 
	 */
	public static double xPowY(double x, double y) throws Exception {
		// x and y are both real numbers  
		// 1. special case when x = 0
		int test = (int)y;
		if(y == test && y != 0 && x != 0)
		{
			double result = 1.0;
			if(test < 0) {
				x = 1/x;
			}
<<<<<<< HEAD
			for(int i = 0; i < BuiltInFunctionImplementation.abs(y); i++) {
=======
			for(int i = 0; i < BuiltInFunctions.abs(y); i++) {
>>>>>>> master
				result = result * x;
			}
			return result;
		}
		else {
			if(x == 0)
			{
				// 1. if y <= 0
				if(y == 0 || y < 0)
				{
					System.out.println("Math Error!");
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
<<<<<<< HEAD
					double temp = y * BuiltInFunctionImplementation.ln(x);
=======
					double temp = y * BuiltInFunctions.ln(x);
>>>>>>> master
					try {
						result = Exp.EXP(temp,1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return result;
				}
			}
		}
		return(0.0);
		}
	
}
