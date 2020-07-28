package TFs;

/*
 * This class implements the transcendental function, cos(x) function
 * @version clean_code branch July 2020
 * @author Jingyi Lin
 */
import project.BuiltInFunctionImplementation;

public class Cos {
	public static double cos(double x){
		return Sin.sin(90-x);
	}
	
	public static double cosforR(double x){
		return Sin.sinforR(BuiltInFunctionImplementation.PI/2-x);
	}
	
}
