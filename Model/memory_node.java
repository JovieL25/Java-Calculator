package Model;

/*
 * This class implements the data structure of math expression history log
 * @version clean_code branch July 2020
 * @author Jingyi Lin
 */

public class memory_node {
	/**expression stores the historical user's input math expression*/
	private String expression;
	
	/**result stores the historical user's input math expression's result*/
	private double result;
	
	/**current_head stores last item's index*/
	public static int current_head=0;
	
	/**
	 * Constructor function
	 */
	public memory_node() {
		expression="";
		result=-1;
	}
	
	/**
	 * Constructor function
	 * @param a is the original math expression in string
	 * @param b is the result in double
	 */
	public memory_node(String a,double b) {
		expression=a;
		result = b;
	}
	
	/**
	 * Getter function of the expression variable
	 * @return expression
	 */
	public String getExpression() {
		return expression;
	}
	
	/**
	 * Setter function of the expression variable
	 * @return expression
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	/**
	 * Getter function of the result variable
	 * @return expression
	 */
	public double getResult() {
		return result;
	}
	
	/**
	 * Setter function of the result variable
	 * @return expression
	 */
	public void setResult(double result) {
		this.result = result;
	}
}
