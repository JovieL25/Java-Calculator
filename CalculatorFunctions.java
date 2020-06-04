import java.lang.Math;

public class CalculatorFunctions {
	private static int maxIterations = 200; // Increase for higher precision outputs
	private static final double accuracy = 0.00001;
	public static final double e = 2.718281828459;
	private static final double PI = 3.14159265359;

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
	
	public static double sinh(double num, boolean isNumDegree) {

        /* Degree to Radian */
        if (isNumDegree) {
            num = num * PI / 180;
        }

        double sum = num;
        double step = num;

        /* Compute until the value of step is smaller than 9 decimal places */
        int k = 2;
        while (Double.compare(step >= 0 ? step : step * (-1), ACCURACY) > 0) {
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double result = CalculatorFunctions.xPowY(10, 15);
		
		
		System.out.println(result);
		
		System.out.println(Math.pow(5.1, 2));
	}

	/*
	 * This class contains all methods required to compute the TF 10^x
	 * The methods include
	 * 
	 * XtoN
	 * ln
	 * isRational
	 * taylor_expand
	 * factorial
	 * abs
	 */
	
	/**
	 * method ln compute and return ln(x)
	 * @param x can be an integer or rational number
	 * @return ln(x) in double
	 */
	public static double ln_jingyi(double x) {
		/*
		 * this method takes the approximation method of finding ln
		 * The procedure is:
		 * 1. write x in A*10^(n-1) 1<=A<=10
		 * 2. compute y = (A-1)/(A+1)
		 * 3. calculate SP = 2*sigma_k=0 k->inf ((y^(2k+1))/(2k+1))
		 * 4. ln(x) = n*log(10)+Sp
		 * 
		 * Notice ln(10) = 2.302585092994046
		 * src: https://math.stackexchange.com/questions/977586/is-there-an-approximation-to-the-natural-log-function-at-large-values
		 */
		
		double SP=0;
		String a = String.format("%1.30e",x);  //format double to scientific notation
		a=a.split("e")[0];
		int n = String.valueOf(Double.parseDouble(a)).length()-2; 
		/*integer n being the number of decimal after turn into scientific notation */
		
		double y = Double.parseDouble(a);
		y = (y-1)/(y+1);
		
		for(int i =0;i<10;i++)
			SP+=2*(XtoN(y,2*i+1)/(2*i+1));
		
		return n*2.302585092994046+SP;
	}

	/**
	 * method isRational determine
	 * @param d can be an integer or rational number
	 * @return true if d is rational
	 */
	public static boolean isRational(double d){
		return !(d%1==0);
	}
	
	/**
	 * method taylor_expand compute and return the taylor expansion of x^n
	 * @param x the base number
	 * @param n the exponent number
	 * @return taylor expansion of the inputs
	 */
	public static double taylor_expand(double x,double n){
		double result = 1;
		for(int i =1;i<20;i++){
			result += XtoN(n*ln(x),i)/factorial(i);
		}
		return result;
	}
	
	/**
	 * method factorial compute and return the factorial(x)
	 * @param x integer
	 * @return x!
	 */
	public static int factorial(int x){
		if(x == 0 || x==1)
			return 1;
		else
			return x*factorial(x-1);
	}
	
	/**
	 * method XtoN compute and return the x^n
	 * @param x double
	 * @param n double could be integer or rational number
	 * @return x^n
	 */
	public static double XtoN(double x ,double n){
		/*
		 * if n is an integer, we can iterate to get the result
		 * if n is a rational number, we have to use the taylor expansion
		 * */
		if(isRational(n)){
			return taylor_expand(x,n);
		}
		else if(n < 0)
			return XtoN(1/x, -n);
		else if(n==0)
			return 1;
		else if(n==1)
			return x;
		else if(n%2==0)
			return XtoN(x*x,n/2);
		else
			return x*XtoN(x*x,(n-1)/2);

	}
	
	/**
	 * method abs is to return the absolute of the value of a
	 * @param a double
	 * @return a
	 */
	public static double abs_jingyi(double a) {
        return (a <= 0.0D) ? 0.0D - a : a;
    }
}
