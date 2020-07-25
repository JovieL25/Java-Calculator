package project;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.regex.Pattern;

public class Shunting_yard_algorithm {

	public static String[] operators = {"+-","*/","^"};

	public static boolean isoperator(String a) {
		return Pattern.compile("[-+*/()^]").matcher(a).find();
	}
	public static boolean isfunction(String a){
		return Pattern.compile("[a-z]").matcher(a).find();
	}

	public static boolean isnumber(String a) {
		return !((isoperator(a))||(isfunction(a))) || Pattern.compile("(^\\-)\\d+(\\.\\d+)?").matcher(a).find();
	}

	public static int determine_precedence(String operator_head,String temp) {
		int temp_precedence=-1;
		int operator_head_precedence=-1;

		for(int i = 0;i<operators.length;i++) {
			if(operators[i].contains(temp))
				temp_precedence=i;
			if(operators[i].contains(operator_head))
				operator_head_precedence=i;
		}

		if(temp_precedence==-1 || operator_head_precedence==-1) {
			//System.out.println(operator_head+" "+temp);
			System.out.println("Error occured! Cannot determine the precedence");
			return -1;
		}
		return operator_head_precedence-temp_precedence;
	}

	public static double shunting_yard_algorithm(String expression){
		String filtered = expression.replace(" ", "");
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
						if(operation_index>0)
							operator_head = operation_stack.get(operation_index);
						while(operation_index>0 && (isoperator(operator_head) && isoperator(temp)) 
								&& !operator_head.contains("(") && !operator_head.contains("^")) {
							//System.out.println(operator_head + " vs "+temp);
							if(determine_precedence(operator_head,temp)>=0) {
								number_stack.add(operator_head);
								operation_stack.remove(operation_index);
							}
							else {
								break;
							}
							operator_head=operation_stack.get(--operation_index);
						}
						//System.out.println("After while loop "+temp);
						operation_stack.add(temp);
					}
					last_operator = false;
					temp = head;
				}
				//if it is first character
				else if(temp.equals("")) {
					temp=head;
				}
				//else adding to temp
				else
					temp+=head;
			}
			else{
				last_operator = true;
				//System.out.println(head+" "+i);
				if(head.equals("-") && (i==0 || (isoperator(filtered.substring(i-1, i)) && !filtered.substring(i-1, i).equals(")")))) {
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
					if(operation_index>0)
						operator_head = operation_stack.get(operation_index);

					while(operation_index>0 && (isoperator(operator_head) && isoperator(temp))
							&& !operator_head.contains("(") && !operator_head.contains("^")) {
						if(determine_precedence(operator_head,temp)>=0) {
							number_stack.add(operator_head);
							operation_stack.remove(operation_index);
						}
						else {
							break;
						}
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

	public static double RPN(ArrayList<String> stack) {
		Deque<Double> processing_stack = new ArrayDeque<Double>();
		int index=0;
		double temp1,temp2;
		while(stack.size()>0) {
			//System.out.println(stack);
			//System.out.println(processing_stack);
			if(stack.get(index).equals("")) {
				stack.remove(index);
				continue;
			}
			else if(isnumber(stack.get(index))) {
				processing_stack.push(Double.parseDouble(stack.remove(index)));
			}
			else {
				//System.out.println(stack.get(index));
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
					processing_stack.push(Sin.sin(temp1));
					break;
				case "ln":
					temp1 = processing_stack.pop();
					processing_stack.push(Ln.ln(temp1));
					break;
				case "MAD":
					String str_temp="";
					while(processing_stack.size()>1) {
						str_temp+=Double.toString(processing_stack.pop()) + ",";
					}
					str_temp+=Double.toString(processing_stack.pop());
					processing_stack.push(Mean_absolute_deviation.MAD(str_temp));
					break;
				default:
					System.out.println("Error!");
					break;
				}
				stack.remove(index);
			}
		}
		return processing_stack.pop();
	}
}
