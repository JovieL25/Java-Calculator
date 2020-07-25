package project;

public class memory_node {
	private String expression;
	private double result;
	
	public memory_node(String a,double b) {
		expression=a;
		result = b;
	}

	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
}
