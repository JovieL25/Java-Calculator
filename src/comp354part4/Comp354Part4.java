package comp354part4;

/* implementation of e^x XOR a^x: Professor said only need to choose one, not both
 * so here is the implementation of the e^x
 * based on the formula: e^x=1+x+x^2/2!+x^3/3!+...(x^n/n!) 
 * I did 2 version of implementation, one is recursion, the other is loop
 * 
 * all the result is double, so the max value should be Double.MAX_VALUE = 1.7976931348623157E308
 * so the maximum value of x could be 709 theoretically (the result is 8.218407461554662e+307),
 * but I cannot come up with such large number
 * 
 * the recursion one: 
 * the maximum result I can get is 1.188812691963352E49,
 * so the max value of x is 113
 * 
 * the while loop one:
 * the maximum result I can get is 1.6948892444103342E28
 * so the max value of x is 65
 * 
 * Depending on the maximum result I can get, I choose the recursion one
 * */
public class Comp354Part4 {
	static double exSum = 1;
	//factorial function
	/*n=170, result = 7.257415615308004e+306 */
	public static double factorial(int n) {
		if(n>170) //1.7976931348623157e+308
			return Double.POSITIVE_INFINITY;
		if(n<=1)
			return 1;
		return n * factorial(n-1);
	}
	
	/* recursion version
	 * double x is the user input
	 * int n is always 1 when using this function
	 * maximum x = 113, result = 1.188812691963352E49
	 * */
	public static double EX(double x, int n)
	{
		if(x>709)
			return Double.POSITIVE_INFINITY;
		if(n>150) //n=150, xMAX = 113
			return exSum;
		exSum += Math.pow(x, n)/factorial(n);
		return EX(x, n+1); //n factorial
	}
	
	//while loop version
	//maximum x = 65, result is 1.6948892444103342E28
	public static double EX1(double x) {
		/* prec is the precision
		 * term is the value of x^n/n! when n is a constant
		 * denominator is the factorial part n!
		 * numerator is the exponential part x^n
		 * */
		double prec = 1e-6, term = 1, denominator = 1, numerator = 1, result=0;
		int n=1;
		while(term > prec)
		{
			result +=term;
			numerator *= x;
			denominator *= n;
			term = numerator/denominator;
			n++;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(EX(113, 1)); //113
		System.out.println(EX1(65)); //65
		System.out.println(Double.MAX_VALUE);
	}

}
