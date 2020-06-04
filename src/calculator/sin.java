package calculator;

public class sin {

	private double input;
	private int precise = 10;
	private final double pi = 3.14159265359;
	double result;
	
	/*
	 * use Taylor series to implement sine,
	 * precise represents the nth term in the series, the default is 10
	 * it also represents the accuracy of the function
	 * 
	 */	
	
public double sin(double x){
		
		result = 0;
		
		int fac;
		
		input = x*pi/180;
		
		for (int i = 0; i<=precise; i++){
			fac = 1;
			for (int j =2; j<=2*i+1; j++){
				fac*=j;
			}
			
			result += mypow(-1.0,i)*mypow(input, 2*i+1)/fac;
			
		}
		return result;
	}
	
public double sinforR(double x){
		
		result = 0;
		
		int fac;
		
		input = x;
		
		for (int i = 0; i<=precise; i++){
			fac = 1;
			for (int j =2; j<=2*i+1; j++){
				fac*=j;
			}
			
			result += mypow(-1.0,i)*mypow(input, 2*i+1)/fac;
			
		}
		return result;
	}
	
	public double mypow (double x, int index){
		
		double r = 1;
		
		for (int i = 0; i< index; i++){
			
			r *= x;
		}
		
		return r;
	}	
}



