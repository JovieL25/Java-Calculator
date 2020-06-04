import java.util.ArrayList;

public class CalculatorFunctions {

	private static int maxIterations = 200; // Increase for higher precision outputs, for ln()
	private static final double accuracy = 0.00001; //The accuracy for ln()
	public static final double e = 2.718281828459;
	static double exSum = 1; //The result for exp function


	/* Function 2: 10^x by Jingyi Lin
	 * 
	 * */
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
	
	
	
	/* Function 3: ln(x) by Dereck Liu
	 * 
	 * */
	public static double ln(double arg) {

		double output = 0;
		double realValue = Math.log(arg);

		// Using Taylor series approximation for values < 1 as trapezoidal sums diverge
		// at ln(0)

		double curValue = arg - 1;
		if (arg < 1) {
			int i = 1;
			// Converge the Taylor series until we hit our desired accuracy or we hit a max number of iterations
			while (BuiltInFunctionImplementation.abs(BuiltInFunctionImplementation.abs(realValue) - BuiltInFunctionImplementation.abs(output)) >= accuracy || i == maxIterations) {
				// change Math.pow to our own pow function
				output += BuiltInFunctionImplementation.posPow(-1, (i + 1) % 2) * curValue / i;
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
			while (BuiltInFunctionImplementation.abs(BuiltInFunctionImplementation.abs(realValue) - BuiltInFunctionImplementation.abs(output)) >= accuracy || i == maxIterations) {
				// change Math.pow to our own pow function
				output += BuiltInFunctionImplementation.posPow(base, i) / i;
				i++;
			}
		}
		return output;
	}
	
	
	/* Function 4: e^x by Yilu Liang
	 * double x is the user input
	 * int n is always 1 when using this function
	 * maximum x = 113, result = 1.188812691963352E49
	 * */
	public static double EXP(double x, int n)
	{
		if(x>709)
			return Double.POSITIVE_INFINITY;
		if(n>150) //n=150, xMAX = 113
			return exSum;
		exSum += Math.pow(x, n)/BuiltInFunctionImplementation.factorial(n);
		return EXP(x, n+1); //n factorial
	}
	

	
	/* Function 5: MAD by Xuan
	 * 
	 * an arrayList is used to save the input of the user,
     * will write my own structure if this lib is not allowed.
     * the counter is used to get how many numbers the user typed.
     * what to do next: add exceptions(input error), allow user change their input before calculate,allow double input
	 * */
    public static double MAD(String str){

        ArrayList<number> list=new ArrayList<>();
        int counter=0;
        int total=0;
        double defInTotal=0;

        if (str.equals(""))
            return 0;
        double result=0;
        String[] s=str.split(",");
        for(int i=0;i<s.length;i++){
            int temp=Integer.valueOf(s[i]);
            list.add(new number(temp));
            total+=temp;
            counter++;
        }

        if(counter==0)
            result=0;
        else{
            double avg=(double)total/counter;
            for(int i=0;i<list.size();i++){
                defInTotal+=BuiltInFunctionImplementation.abs(list.get(i).n-avg);
            }
            result=defInTotal/counter;
        }
        //System.out.println(total+" "+counter+" "+defInTotal);
        return result;

    }

    static class number{
        int n;
        number(int n){
            this.n=n;
        }
    }
	
	

	/* Function 8: x^y by Shiyu Lin
	 * 
	 * */
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
				result = CalculatorFunctions.EXP(temp, 1);
				return result;
				//return Math.pow(x, y);
			}
		}
	
		return(-0.9999999999);
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double result = CalculatorFunctions.xPowY(10, 15);
		
		
		System.out.println(result);
		
		System.out.println(Math.pow(5.1, 2));
	}
	
}
