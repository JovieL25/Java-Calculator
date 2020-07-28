
package TFs;
/*
 * This class implements the transcendental function, tan(x) function
 * @version clean_code branch July 2020
 * @author Jingyi Lin
 */

public class Tan {
	
	public static double tan(double x){
		return Sin.sin(x)/Cos.cos(x);
	}
	
	public static double tanforR(double x){
		return Sin.sinforR(x)/Cos.cosforR(x);
	}
	
}
