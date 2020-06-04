package eternity_V_1;
/*
 * 
 */

import java.util.ArrayList;
import java.util.Stack;

public class eternity_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		command_parser("");
	}

	public static void command_parser(String command) {
		String filtered = command.replace(" ", "");
		/*
		 * The order of arithmetic Parentheses Exponents Multiplication and Division
		 * Addition and Subtraction
		 */
		String str = "20 + 10 + a+b +b + 20".replace(" ", "");
		String processed = process_paren(str);
		double result;
		if(processed.contains("Invalid"))
			System.out.println("Invalid command!");

		// System.out.println(parens_check(str));
		// System.out.println(str.substring(str.indexOf("(")+1,str.indexOf(")")));
	}

	public static String process_paren(String str) {
		if (!parens_check(str))
			return "Invalid input! Please check your parentheses";
		else {

			ArrayList<String> split_result = new ArrayList<String>();

			for (String a : str.split("\\(")) {
				for (String b : a.split("\\)"))
					split_result.add(b);
			}
			if (split_result.size() == 1)
				return split_result.get(0);
			else if (split_result.size() % 2 == 0)
				return "Invalid input! Please check your parentheses";
			else
				return process_paren(split_result);
		}
	}

	public static String process_paren(ArrayList<String> command_list) {
		double result = 0;
		double d;
		String middle = command_list.get(command_list.size() / 2);
		
		middle = Double.toString(parse_command(middle));
		
		for(int i=1,j=command_list.size() / 2;i<command_list.size() / 2;i++) {
			if(command_list.get(j-i).length()==0)
				System.out.println(command_list.get(j+i));
			else if(command_list.get(j+i).length()==0)
				System.out.println(command_list.get(j+i));
			else
				middle = Double.toString(parse_command(command_list.get(j-i)+middle+command_list.get(j+i)));
		}
		
		return middle;
	}

	public static double parse_command(String a) {
		boolean hascommand=false;
		int begin = 0;
		int end = 0;
		ArrayList<String> symbols = new ArrayList<String>();
		symbols.add("*");
		symbols.add("/");
		symbols.add("+");
		symbols.add("-");
		
		for(boolean command=false,int i = 0;i<a.length();i++) {
			if(a.charAt(i)=='*') {
				 //exponent case
				 if(a.charAt(i+1)=='*'){
					 
				 }
				 //mul case
				 else {
					 
				 }
			}
			else if(a.charAt(i)=='/') {
				
			}
			else if(a.charAt(i)=='+'){
				
			}
			else if(a.charAt(i)=='-') {
				
			}
			else {
				
			}
			
				
			System.out.println();
			a.subSequence(begin, end)
		}
		// exponent case
		/*
		 * if(par_result[0] && !(par_result[1] || par_result[2] || par_result[3]||
		 * par_result[4])){ String[] command_split = middle.split("\\**"); d =
		 * ten_to_x.XtoN(Double.parseDouble(command_split[0]),Double.parseDouble(
		 * command_split[1])); for(int i =2;i<command_split.length;i++) d =
		 * ten_to_x.XtoN(d,Double.parseDouble(command_split[i])); } //mul,div,add,sub
		 * else {
		 * 
		 * }
		 * 
		 * for(int i=command_list.size()/2,j =1;j<command_list.size()/2;j++) {
		 * System.out.println(command_list.get(i-j)+" "+command_list.get(i+j));
		 * System.out.println(command_list.get(i-j).equals(command_list.get(i+j))); }
		 * return String.valueOf(result);
		 */
		return 0;
	}

	public static boolean[] parse_string(String a) {
		boolean exp = false, mul = false, div = false, add = false, sub = false;
		boolean[] result = { exp, mul, div, add, sub };
		if (a.contains("**"))
			result[0] = true;
		else if (a.contains("*"))
			result[1] = true;
		if (a.contains("/"))
			result[2] = true;
		if (a.contains("+"))
			result[2] = true;
		if (a.contains("-"))
			result[2] = true;
		return result;
	}

	public static double plus(String a) {
		return Double.parseDouble(a.split("\\+")[0]) + Double.parseDouble(a.split("\\+")[1]);
	}

	public static double minus(String a) {
		return Double.parseDouble(a.split("\\-")[0]) - Double.parseDouble(a.split("\\-")[1]);
	}

	public static double mul(String a) {
		return Double.parseDouble(a.split("\\*")[0]) * Double.parseDouble(a.split("\\*")[1]);
	}

	public static double div(String a) {
		return Double.parseDouble(a.split("\\/")[0]) / Double.parseDouble(a.split("\\/")[1]);
	}

	public static boolean parens_check(String text) {
		Stack<Character> openParens = new Stack<>();
		for (Character ch : text.toCharArray()) {
			if (ch == '(') {
				openParens.push(ch);
			} else if (ch == ')') {
				if (openParens.empty()) {
					return false; // unbalanced
				} else {
					openParens.pop();
				}
			}
		}
		return true;
	}

}
