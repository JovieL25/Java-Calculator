package Model;

public class Cos {
	public static double cos(double x){
		return Sin.sin(90-x);
	}
	
	public static double cosforR(double x) throws Exception{
		return Sin.sinforR(BuiltInFunctionImplementation.PI/2-x);
	}
	
}
