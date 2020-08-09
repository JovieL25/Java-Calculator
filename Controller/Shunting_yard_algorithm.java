/*
 * This class implements the math expression parser and evaluation algorithm
 * @version clean_code branch July 2020
 * @author Jingyi Lin
 */
package Controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.regex.Pattern;

import Model.BuiltInFunctions;
import Model.Cos;
import Model.Exp;
import Model.Ln;
import Model.Mean_absolute_deviation;
import Model.Sin;
import Model.Sinh;
import Model.Tan;
import Model.XtoN;
import View.SimpleCalculatorInterface;

public class Shunting_yard_algorithm {
	
	/**
	 * "operators" array stores possible operators 
	 * and their precedence level same as their index
	 */
	public static String[] operators = {"+-","*/","^"};
	private static int mad_input_size=-1;
	private static String[] TFs_labels = {"sinh","ln","exp","mad","sin","cos","tan"};

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
	
	/** 
	 * isnumber function determines if a token is a string
	 * anything that is not a string or having the 
	 * described pattern is a number
	 */
	public static boolean isnumber(String a) {
		return !((isoperator(a))||(isfunction(a))) 
					|| Pattern.compile("-?\\d+(\\.\\d+)?(e-?\\d+)?").matcher(a).find();
	}
	
	/** determine_precedence function is a helper function
	 *  that help determine the precedence between two tokens
	 *  if first token have higher precedence, the returned result will be larger 0*/
	public static int determine_precedence(String operator_head,String temp_token) throws Exception{
		int temp_token_precedence=-1;
		int operator_head_precedence=-1;

		for (int i = 0;i<operators.length;i++) {
			if (operators[i].contains(temp_token))
				temp_token_precedence=i;
			if (operators[i].contains(operator_head))
				operator_head_precedence=i;
		}

		if (temp_token_precedence==-1 || operator_head_precedence==-1) {
			throw new Exception("Error occured! Cannot determine the precedence");
		}
		return operator_head_precedence-temp_token_precedence;
	}

	/** shunting yard algorithm takes a string and identify token.
	 *  This function will call RPN get the final result of the math expression.
	 */
	public static double shunting_yard_algorithm_parse(String expression) throws Exception{
		//df.setRoundingMode(RoundingMode.CEILING);
		//Formatting the user input to elimiate space
		//Put the string to lowercase
		//Replace string "PI" with a double value
		String filtered_input_string = expression.replace(" ", "");
		filtered_input_string = filtered_input_string.toLowerCase();
		int string_index_begin=0;
		int string_index_end=0;
		boolean all_check=false;
		while (filtered_input_string.contains("-(") && !all_check){
			string_index_begin = filtered_input_string.indexOf("-(",string_index_begin)-1;
			if (string_index_begin==-1)
				break;
			if (Pattern.compile("[-+*/^]").matcher(
					filtered_input_string.substring(string_index_begin, string_index_begin+1)).find())
			{
				if (filtered_input_string.substring(string_index_begin, string_index_begin+1).equals("-")){
					filtered_input_string=filtered_input_string.replace
							(filtered_input_string.substring(string_index_begin, string_index_begin+1)+"-(", 
							filtered_input_string.substring(string_index_begin, string_index_begin+1)+"-1*(");
				}
				else {
					filtered_input_string=filtered_input_string.replace
							(filtered_input_string.substring(string_index_begin, string_index_begin+1)+"-(", 
							filtered_input_string.substring(string_index_begin, string_index_begin+1)+"(-1*(");
						string_index_end=filtered_input_string.indexOf(")", string_index_begin);
						filtered_input_string=filtered_input_string.replace(
								filtered_input_string.substring(string_index_begin,string_index_end), 
								filtered_input_string.substring(string_index_begin,string_index_end)+")");
				}
			}
				
				
		}
		
		//Verifying if functions are followed by a parenthesis
		function_parenthesis_check(filtered_input_string);
		
		String head="";
		boolean last_number=false;
		boolean last_operator=false;
		boolean parenthesis = false;
		String temp_token = "";

		ArrayList<String> number_stack = new ArrayList<String>();
		ArrayList<String> operation_stack = new ArrayList<String>();
		
		//for each character in the expression
		for (int i =0;i<filtered_input_string.length();i++) {
			
			//System.out.println(number_stack);
			//System.out.println(operation_stack);
			//Get current character
			head = filtered_input_string.substring(i, i + 1);
			
			//Tokenization process
			//To tokenize the whole user's input
			//I have to verify the current character and previous character
			//for example in the following equation: 34+44
			//The tokens are 34,+,44
			//Therefore, I need to read character, put it in a temperary string
			//If the previous character is number and current character is also number
			//Then we put the new character in the temp_tokenerary string
			
			if (head.contains(",")){					//if current head contains a comma
													//possible number token(1.34)...
				if (last_number) {
					number_stack.add(temp_token);
					last_number=false;
				}
				else {
					operation_stack.add(temp_token);
					last_operator=false;
				}
				temp_token="";
			}
			else if (head.equals(".") || !(isoperator(head) || isfunction(head))) {
				
				//If current char is a "." or it does not belong to operators or functions
				//This character belong to a number token
				last_number= true;
				
				//if previous character is part of operator
				if (last_operator){
					
					if (temp_token.equals(""))			//if this is starting character
						last_operator=false;
					else if (isfunction(temp_token) || temp_token.contains("(")) {
						
						//if the temp_token is part of function or is "("
						//Add the token to operation stack
						operation_stack.add(temp_token);
					}
					else {
						
						//If the last token is operator
						//I need to verify the precedence between new token and top of operator stack
						//If Top of operator stack has more priority than current token
						//Then pop the stack and pass the element to number stack
						//Continue untill seeing a "(" on top of stack or pushed if token have higher precedence
						int operation_index=operation_stack.size()-1;
						String operator_head="";
						if (operation_index>-1)
							operator_head = operation_stack.get(operation_index);
						while (operation_index>-1 && (isoperator(operator_head) && isoperator(temp_token)) 
								&& !operator_head.contains("(") && !temp_token.contains("^")) {
							if (determine_precedence(operator_head,temp_token)>=0) {
								number_stack.add(operator_head);
								operation_stack.remove(operation_index);
							}
							else {
								break;
							}
							if (operation_index==0)
								break;
							operator_head=operation_stack.get(--operation_index);
						}
						operation_stack.add(temp_token);
					}
					last_operator = false;
					temp_token = head;
				}
				else if (temp_token.equals("")) {
					temp_token=head;
				}
				
				//else adding to temp_token
				else
					temp_token+=head;
			}
			else{
				
				/*
				 * If the current char is an operator
				 * There are several cases where:
				 * 
				 * case1: temp_token is a number, example 34+56
				 * when head is "+", temp_token has "34". 
				 * Simply push temp_token to number_stack
				 * 
				 * case2: temp_token has another operator,
				 * example 34+(5+6), when head is "(", temp_token has "+".
				 * This case push temp_token to operator_stack
				 * 
				 * extreme case: 34+-2, different from the above case,
				 * this case "-" is consider to be a part of number
				 * 
				 * 
				 */
				last_operator = true;
				if (head.equals("e") && !filtered_input_string.substring(i+1,i+2).equals("x")){
					last_operator=false;
					last_number=true;
					temp_token+=head;
				}
				else if (head.equals("-") 
						&& (i==0 || (isoperator(filtered_input_string.substring(i-1, i)) 
						&& !filtered_input_string.substring(i-1, i).equals(")")))) {
					
					//enter here if current char is "-"
					//I need to make sure if this is not part of an negative number
					int operation_index=operation_stack.size()-1;
					String operator_head="";
					if (operation_index>-1)
						operator_head = operation_stack.get(operation_index);
					
					//Otherwise, treated it as an normal minus operator
					//and do the routine described above(checking precedence and pop to number stack)
					while (operation_index>-1 && (isoperator(operator_head) && isoperator(temp_token)) 
							&& !operator_head.contains("(") && !temp_token.contains("^")) {
						if (determine_precedence(operator_head,temp_token)>=0) {
							number_stack.add(operator_head);
							operation_stack.remove(operation_index);
						}
						else {
							break;
						}
						if (operation_index==0)
							break;
						operator_head=operation_stack.get(--operation_index);
					}
					
					operation_stack.add(temp_token);
					last_number=true;
					temp_token = "-";
					last_operator=false;
				}
				else if (last_number) {
					last_number=false;
					number_stack.add(temp_token);
					if (head.contains("(")) {
						operation_stack.add(head);
						temp_token="";
						parenthesis = true;
					}
					else if (head.contains(")")){
						parenthesis = true;
						int operation_index=operation_stack.size()-1;
						String operator_head = operation_stack.get(operation_index);
						while (!operator_head.contains("(")) {
							number_stack.add(operator_head);
							operation_stack.remove(operation_index);
							if (operation_index==0)
								throw new Exception("Error in the expression, parenthesis not matching");
							operator_head = operation_stack.get(--operation_index);
						}
						operation_stack.remove(operation_index);
						if (isfunction(operation_stack.get(--operation_index)))
							number_stack.add(operation_stack.remove(operation_index));
						temp_token="";
					}
					else
						temp_token=head;
				}
				else if (head.contains("(")) {
					parenthesis = true;
					int operation_index=operation_stack.size()-1;
					String operator_head="";
					if (operation_index>-1)
						operator_head = operation_stack.get(operation_index);

					while (operation_index>-1 && (isoperator(operator_head) && isoperator(temp_token))
							&& !operator_head.contains("(") && !temp_token.contains("^")) {
						if (determine_precedence(operator_head,temp_token)>=0) {
							number_stack.add(operator_head);
							operation_stack.remove(operation_index);
						}
						else {
							break;
						}
						if (operation_index==0)
							break;
						operator_head=operation_stack.get(--operation_index);
					}
					operation_stack.add(temp_token);
					operation_stack.add(head);
					temp_token="";
				}
				else if (head.contains(")")){
					parenthesis = true;
					int operation_index=operation_stack.size()-1;
					String operator_head = operation_stack.get(operation_index);

					while (!operator_head.contains("(")) {
						number_stack.add(operator_head);
						operation_stack.remove(operation_index);
						if (operation_index==0)
							throw new Exception("Parenthesis not matching");
						operator_head = operation_stack.get(--operation_index);
					}
					operation_stack.remove(operation_index);
					if (isfunction(operation_stack.get(--operation_index)))
						number_stack.add(operation_stack.remove(operation_index));
				}
				else if (temp_token.equals("")) {
					temp_token=head;
				}
				else {
					if (isfunction(head) && !temp_token.equals("") && !isfunction(temp_token)) {
						int operation_index=operation_stack.size()-1;
						String operator_head="";
						if (operation_index>-1)
							operator_head = operation_stack.get(operation_index);
						
						while (operation_index>-1 && (isoperator(operator_head) && isoperator(temp_token))
								&& !operator_head.contains("(") && !temp_token.contains("^")) {
							if (determine_precedence(operator_head,temp_token)>=0) {
								number_stack.add(operator_head);
								operation_stack.remove(operation_index);
							}
							else {
								break;
							}
							if (operation_index==0)
								break;
							operator_head=operation_stack.get(--operation_index);
						}
						operation_stack.add(temp_token);
						temp_token = head;
					}
					else
						temp_token+=head;
				}
					
			}
		}

		if (isoperator(temp_token) || isfunction(temp_token))
			operation_stack.add(temp_token);
		else
			number_stack.add(temp_token);
		for (int i =operation_stack.size()-1;i>=0;i--)
			number_stack.add(operation_stack.get(i));

		return RPN(number_stack);

	}

	/** RPN function determine the result of the math expression
	 *  that is in reverse polish notation. 
	 *  Shunting yard algorithm transfer a normal math expression to reverse polish notation.
	 */
	public static double RPN(ArrayList<String> stack) throws Exception{
		System.out.println(stack);
		Deque<Double> processing_stack = new ArrayDeque<Double>();
		int index=0;
		double temp_token1,temp_token2;
		while (stack.size()>0) {
			if (stack.get(index).equals("")) {
				stack.remove(index);
				continue;
			}
			else if (isnumber(stack.get(index))) {
				processing_stack.push(Double.parseDouble(stack.remove(index)));
			}
			else {
				//In RPN, the order is determined and we simply need to pop
				//required number for execution
				//For example, doing an addition require 2 numbers
				//So we pop the stack two times.
				//Some functions require only 1 number
				//MAD function require infinite number
				switch(stack.get(index)) {
				case "+":
					temp_token1 = processing_stack.pop();
					temp_token2 = processing_stack.pop();
					processing_stack.push(temp_token1+temp_token2);
					break;
					
				case "-":
					temp_token1 = processing_stack.pop();
					temp_token2 = processing_stack.pop();
					processing_stack.push(temp_token2-temp_token1);
					break;
					
				case "*":
					temp_token1 = processing_stack.pop();
					temp_token2 = processing_stack.pop();
					processing_stack.push(temp_token1*temp_token2);
					break;
					
				case "/":
					temp_token1 = processing_stack.pop();
					temp_token2 = processing_stack.pop();
					processing_stack.push(temp_token2/temp_token1);
					break;
					
				case "^":
					temp_token1 = processing_stack.pop();
					temp_token2 = processing_stack.pop();
					processing_stack.push(XtoN.Xton(temp_token2, temp_token1));
					break;
					
				case "sin":
					temp_token1 = processing_stack.pop();
					if (SimpleCalculatorInterface.rad_mode)
						processing_stack.push(Sin.sinforR(temp_token1));
					else
						processing_stack.push(Sin.sin(temp_token1));
					break;
					
				case "ln":
					temp_token1 = processing_stack.pop();
					processing_stack.push(Ln.ln(temp_token1));
					break;
					
				case "mad":
					String str_temp_token="";
					for (int i =1;i<mad_input_size;i++) {
						str_temp_token+=Double.toString(processing_stack.pop()) + ",";
					}
					str_temp_token+=Double.toString(processing_stack.pop());
					processing_stack.push(Mean_absolute_deviation.MAD(str_temp_token));
					break;
					
				case "sinh":
					temp_token1 = processing_stack.pop();
					processing_stack.push(Sinh.sinh(temp_token1, !SimpleCalculatorInterface.rad_mode));
					break;
					
				case "exp":
					temp_token1 = processing_stack.pop();
					processing_stack.push(Exp.EXP(temp_token1, 1));
					Model.Exp.exSum=1;
					break;
					
				case "cos":
					temp_token1 = processing_stack.pop();
					if (SimpleCalculatorInterface.rad_mode)
						processing_stack.push(Cos.cosforR(temp_token1));
					else
						processing_stack.push(Cos.cos(temp_token1));
					break;
					
				case "tan":
					temp_token1 = processing_stack.pop();
					if (SimpleCalculatorInterface.rad_mode)
						processing_stack.push(Tan.tanforR(temp_token1));
					else
						processing_stack.push(Tan.tan(temp_token1));
					break;
					
				case "fact":
					temp_token1=processing_stack.pop();
					processing_stack.push(BuiltInFunctions.factorial((int)temp_token1));
					break;
					
				case "sqrt":
					temp_token1=processing_stack.pop();
					processing_stack.push(BuiltInFunctions.sqrt(temp_token1));
					break;
					
				default:
					throw new Exception("Unrecoginzed operators or functions");
				}
				stack.remove(index);
			}
		}
		
		double result = processing_stack.pop();
		System.out.println(result);
		return Double.parseDouble(String.format("%.9f", result));
	}

	private static void function_parenthesis_check(String a) throws Exception{
		//For each string in pre_defined String Array
		//if user's input contains such string 
		//and the following character is not a "("
		//Then it's not good and we throw exception to the main thread
		boolean has_sinh=a.contains("sinh");
		for (String c:TFs_labels){
			if (a.contains(c) && a.charAt(a.indexOf(c)+c.length())!='(' 
					&& (!has_sinh && c.equals("sin")))
				throw new Exception("Function must followed by parenthesis");
			if (a.contains("mad")){
				Shunting_yard_algorithm.mad_input_size=
						a.substring(a.indexOf("mad")+3, 
								a.indexOf(")", a.indexOf("mad")+3)).split(",").length;
			}
		}
	}
}
