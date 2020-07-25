package project;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.regex.Pattern;

/*
 * This class implements the math expression parser and evaluation algorithm
 * @version clean_code branch July 2020
 * @author Jingyi Lin
 */
public class Shunting_yard_algorithm {
	
	/**"operators" array stores possible operators and their precedence level same as their index*/
	public static String[] operators = {"+-","*/","^"};

	/**
	 * isoperator function determines if a token is operators
	 * operators includes "+,-,*,/,(,),^"
	 */
	public static boolean isoperator(String a) {
		return Pattern.compile("[-+*/()^]").matcher(a).find();
	}
	
	/**
	 * isfunction function determines if a token is a function
	 * determines by finding any alphabets in the token
	 * */
	public static boolean isfunction(String a){
		return Pattern.compile("[a-z]").matcher(a).find();
	}
	
	/** isnumber function determines if a token is a string
	 * anything that is not a string or having the described pattern is a number*/
	public static boolean isnumber(String a) {
		return !((isoperator(a))||(isfunction(a))) || Pattern.compile("(^\\-)\\d+(\\.\\d+)?").matcher(a).find();
	}
	
	/** determine_precedence function is a helper function
	 *  that help determine the precedence between two tokens
	 *  if first token have higher precedence, the returned result will be larger 0*/
	public static int determine_precedence(String operator_head,String temp) throws Exception{
		int temp_precedence=-1;
		int operator_head_precedence=-1;

		for(int i = 0;i<operators.length;i++) {
			if(operators[i].contains(temp))
				temp_precedence=i;
			if(operators[i].contains(operator_head))
				operator_head_precedence=i;
		}

		if(temp_precedence==-1 || operator_head_precedence==-1) {
			throw new Exception("Error occured! Cannot determine the precedence");
		}
		return operator_head_precedence-temp_precedence;
	}

	/** shunting yard algorithm takes a string and identify token.
	 *  This function will call RPN get the final result of the math expression.
	 */
	public static double shunting_yard_algorithm(String expression) throws Exception{
		String filtered = expression.replace(" ", "");
		filtered = filtered.toLowerCase();
		filtered = filtered.replace("pi", Double.toString(BuiltInFunctionImplementation.PI));
		String head="";
		boolean last_number=false;
		boolean last_operator=false;
		boolean parenthesis = false;
		String temp = "";

		ArrayList<String> number_stack = new ArrayList<String>();
		ArrayList<String> operation_stack = new ArrayList<String>();

		//for each character in the expression
		for(int i =0;i<filtered.length();i++) {
			//System.out.println(number_stack);
			//System.out.println(operation_stack);
			//Get current character
			head = filtered.substring(i, i+1);
			if(head.contains(",")){
				if(last_number) {
					number_stack.add(temp);
					last_number=false;
				}
				else {
					operation_stack.add(temp);
					last_operator=false;
				}
				temp="";
			}
			else if(head.equals(".") || !(isoperator(head) || isfunction(head))) {
				last_number= true;
				//if previous character is part of operator
				if(last_operator){
					if(temp.equals(""))
						last_operator=false;
					else if(isfunction(temp) || temp.contains("(")) {
						operation_stack.add(temp);
					}
					else {
						
						int operation_index=operation_stack.size()-1;
						String operator_head="";
						if(operation_index>-1)
							operator_head = operation_stack.get(operation_index);
						while(operation_index>-1 && (isoperator(operator_head) && isoperator(temp)) 
								&& !operator_head.contains("(") && !temp.contains("^")) {
							if(determine_precedence(operator_head,temp)>=0) {
								number_stack.add(operator_head);
								operation_stack.remove(operation_index);
							}
							else {
								break;
							}
							if(operation_index==0)
								break;
							operator_head=operation_stack.get(--operation_index);
						}
						operation_stack.add(temp);
					}
					last_operator = false;
					temp = head;
				}
				else if(temp.equals("")) {
					temp=head;
				}
				//else adding to temp
				else
					temp+=head;
			}
			else{
				last_operator = true;
				if(head.equals("-") && (i==0 || (isoperator(filtered.substring(i-1, i)) && !filtered.substring(i-1, i).equals(")")))) {
					operation_stack.add(temp);
					last_number=true;
					temp = "-";
					last_operator=false;
				}
				else if(last_number) {
					last_number=false;
					number_stack.add(temp);
					if(head.contains("(")) {
						operation_stack.add(head);
						temp="";
						parenthesis = true;
					}
					else if(head.contains(")")){
						parenthesis = true;
						int operation_index=operation_stack.size()-1;
						String operator_head = operation_stack.get(operation_index);
						while(!operator_head.contains("(")) {
							number_stack.add(operator_head);
							operation_stack.remove(operation_index);
							if(operation_index==0)
								throw new Exception("Error in the expression, parenthesis not matching");
							operator_head = operation_stack.get(--operation_index);
						}
						operation_stack.remove(operation_index);
						if(isfunction(operation_stack.get(--operation_index)))
							number_stack.add(operation_stack.remove(operation_index));
						temp="";
					}
					else
						temp=head;
				}
				else if(head.contains("(")) {
					parenthesis = true;
					int operation_index=operation_stack.size()-1;
					String operator_head="";
					if(operation_index>-1)
						operator_head = operation_stack.get(operation_index);

					while(operation_index>-1 && (isoperator(operator_head) && isoperator(temp))
							&& !operator_head.contains("(") && !operator_head.contains("^")) {
						if(determine_precedence(operator_head,temp)>=0) {
							number_stack.add(operator_head);
							operation_stack.remove(operation_index);
						}
						else {
							break;
						}
						if(operation_index==0)
							break;
						operator_head=operation_stack.get(--operation_index);
					}
					operation_stack.add(temp);
					operation_stack.add(head);
					temp="";
				}
				else if(head.contains(")")){
					parenthesis = true;
					int operation_index=operation_stack.size()-1;
					String operator_head = operation_stack.get(operation_index);

					while(!operator_head.contains("(")) {
						number_stack.add(operator_head);
						operation_stack.remove(operation_index);
						if(operation_index==0)
							throw new Exception("Error in the expression, parenthesis not matching");
						operator_head = operation_stack.get(--operation_index);
					}
					operation_stack.remove(operation_index);
					if(isfunction(operation_stack.get(--operation_index)))
						number_stack.add(operation_stack.remove(operation_index));
				}
				else if(temp.equals("")) {
					temp=head;
				}
				else {
					if(isfunction(head) && !temp.equals("") && !isfunction(temp)) {
						operation_stack.add(temp);
						temp = head;
					}
					else
						temp+=head;
				}
					
			}
		}

		if(isoperator(temp) || isfunction(temp))
			operation_stack.add(temp);
		else
			number_stack.add(temp);
		for(int i =operation_stack.size()-1;i>=0;i--)
			number_stack.add(operation_stack.get(i));

		return RPN(number_stack);

	}

	/** RPN function determine the result of the math expression
	 *  that is in reverse polish notation. 
	 *  Shunting yard algorithm transfer a normal math expression to reverse polish notation.
	 */
	public static double RPN(ArrayList<String> stack) throws Exception{
		Deque<Double> processing_stack = new ArrayDeque<Double>();
		int index=0;
		double temp1,temp2;
		while(stack.size()>0) {
			if(stack.get(index).equals("")) {
				stack.remove(index);
				continue;
			}
			else if(isnumber(stack.get(index))) {
				processing_stack.push(Double.parseDouble(stack.remove(index)));
			}
			else {
				switch(stack.get(index)) {
				case "+":
					temp1 = processing_stack.pop();
					temp2 = processing_stack.pop();
					processing_stack.push(temp1+temp2);
					break;
				case "-":
					temp1 = processing_stack.pop();
					temp2 = processing_stack.pop();
					processing_stack.push(temp2-temp1);
					break;
				case "*":
					temp1 = processing_stack.pop();
					temp2 = processing_stack.pop();
					processing_stack.push(temp1*temp2);
					break;
				case "/":
					temp1 = processing_stack.pop();
					temp2 = processing_stack.pop();
					processing_stack.push(temp2/temp1);
					break;
				case "^":
					temp1 = processing_stack.pop();
					temp2 = processing_stack.pop();
					processing_stack.push(BuiltInFunctionImplementation.XtoN(temp2, temp1));
					break;
				case "sin":
					temp1 = processing_stack.pop();
					if(CalculatorInterface.rad)
						processing_stack.push(Sin.sinforR(temp1));
					else
						processing_stack.push(Sin.sin(temp1));
					break;
				case "ln":
					temp1 = processing_stack.pop();
					processing_stack.push(Ln.ln(temp1));
					break;
				case "mad":
					String str_temp="";
					while(processing_stack.size()>1) {
						str_temp+=Double.toString(processing_stack.pop()) + ",";
					}
					str_temp+=Double.toString(processing_stack.pop());
					processing_stack.push(Mean_absolute_deviation.MAD(str_temp));
					break;
				case "sinh":
					temp1 = processing_stack.pop();
					processing_stack.push(Sinh.sinh(temp1, !CalculatorInterface.rad));
					break;
				default:
					System.out.println("Error!");
					throw new Exception("Operator not found");
				}
				stack.remove(index);
			}
		}
		return processing_stack.pop();
	}
}
