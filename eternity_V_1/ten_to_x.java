/*
 * ten_to_x
 * 
 * Version 1.1
 * 
 */
package eternity_V_1;

public class ten_to_x {
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
	public static double ln(double x) {
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
	public static double abs(double a) {
        return (a <= 0.0D) ? 0.0D - a : a;
    }
	
}
