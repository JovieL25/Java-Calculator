package eternity_V_1;

public class ten_to_x {
	
	public static double ln(double x) {
		double SP=0;
		
		String a = String.format("%1.30e",x);
		a=a.split("e")[0];
		int n = String.valueOf(Double.parseDouble(a)).length()-2;
		double y = Double.parseDouble(a);
		y = (y-1)/(y+1);
		
		for(int i =0;i<10;i++)
			SP+=2*(XtoN(y,2*i+1)/(2*i+1));
		
		return n*2.302585092994046+SP;
	}
	
	public static boolean isRational(double d){
		return !(d%1==0);
	}
	
	public static double taylor_expand(double x,double n){
		double result = 1;
		for(int i =1;i<20;i++){
			result += XtoN(n*ln(x),i)/factorial(i);
		}
		return result;
	}
	
	public static int factorial(int x){
		if(x == 0 || x==1)
			return 1;
		else
			return x*factorial(x-1);
	}
	
	public static double XtoN(double x ,double n){
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
}
