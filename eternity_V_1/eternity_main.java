/*
* Eternity main
 * 
 * Version 1.0
 * 
 */
package eternity_V_1;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class eternity_main {

	/**
	 * method main is a simple user interface
	 * @param args String
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner user_scan = null;
		boolean flag = true;
		String userinput;
		while(flag){ 			 // user interface
			System.out.print("Write your math equation\n>>>");
			user_scan = new Scanner(System.in);
			userinput = user_scan.nextLine();
			if(userinput.contains("quit")) {
				System.out.print("Bye!");
				flag = false;
			}
			else
				command_parser(userinput);
		}
		user_scan.close();
		
	}
	
	/**
	 * method command_parser
	 * @param command string
	 */
	public static void command_parser(String command) {
		String filtered = command.replace(" ", "");
		String processed = process_paren(filtered);
		
		if(processed.contains("Invalid"))
			System.out.println("Invalid command!");
		else
			System.out.println(parse_command(processed));
	}
	
	/**
	 * method command_parser
	 * @param command string
	 */
	public static String process_paren(String str) {
		if (!parens_check(str))
			return "Invalid input! Please check your parentheses";
		else {
			ArrayList<String> split_result = new ArrayList<String>();
			
			for (String a : str.split("\\(")) {
				for (String b : a.split("\\)"))
					split_result.add(b);
			}
			if (split_result.size() == 2) {
				return split_result.get(1);
			}
			else
				return process_paren(split_result);
		}
	}
	
	/**
	 * method parens_check
	 * @param text string
	 * @return boolean 
	 */
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
	
	/**
	 * method process_paren 
	 * @param command_list ArrayList
	 * @return middle String
	 */
	public static String process_paren(ArrayList<String> command_list) {
		
		String middle = command_list.get(command_list.size() / 2);
		
		middle = Double.toString(parse_command(middle));
		
		for(int i=1,j=command_list.size()/2;(j+i)<command_list.size();i++) {
			if(command_list.get(j-i).length()==0)
				middle = Double.toString(parse_command(middle+command_list.get(j+i)));
			else if(command_list.get(j+i).length()==0)
				middle = Double.toString(parse_command(command_list.get(j-i)+middle));
			else
				middle = Double.toString(parse_command(command_list.get(j-i)+middle+command_list.get(j+i)));
		}
		return middle;
	}

	/**
	 * method parse_command 
	 * @param user_command string
	 * @return user_command double
	 */
	public static double parse_command(String user_command) {
		
		/*
		 * The order of arithmetic 
		 * Parentheses 
		 * Exponents 
		 * Multiplication and Division
		 * Addition and Subtraction
		 */
		
		
		//System.out.println(user_command);
	
		String[] temp;
		Pattern pattern_mul_exp = Pattern.compile("(\\*\\*\\d+(\\.\\d+)?(E\\d+)?\\*\\*\\d+(\\.\\d+)?(E\\\\d+)?)");
		Pattern pattern_exp = Pattern.compile("(\\d+(\\.\\d+)?(E\\d+)?\\*\\*\\d+(\\.\\d+)?(E\\d+)?)");
		Pattern pattern_mul = Pattern.compile("(\\d+(\\.\\d+)?(E\\d+)?\\*\\d+(\\.\\d+)?(E\\d+)?)");
		Pattern pattern_div = Pattern.compile("(\\d+(\\.\\d+)?(E\\d+)?\\/\\d+(\\.\\d+)?(E\\d+)?)");
		Pattern pattern_add = Pattern.compile("(\\d+(\\.\\d+)?(E\\d+)?\\+\\d+(\\.\\d+)?(E\\d+)?)");
		Pattern pattern_sub = Pattern.compile("(\\d+(\\.\\d+)?(E\\d+)?\\-\\d+(\\.\\d+)?(E\\d+)?)");
		
		Matcher matcher_mul_exp = pattern_mul_exp.matcher(user_command);
		while(matcher_mul_exp.find()) {
			temp = matcher_mul_exp.group().split("\\*\\*");
			user_command=user_command.replace(matcher_mul_exp.group(), "**"+
					Double.toString(ten_to_x.XtoN(Double.parseDouble(temp[1]), Double.parseDouble(temp[2]))));
			matcher_mul_exp = pattern_mul_exp.matcher(user_command);
		}
		
		Matcher matcher_exp = pattern_exp.matcher(user_command);
		while(matcher_exp.find()) {
			temp = matcher_exp.group().split("\\*\\*");
			user_command=user_command.replace(matcher_exp.group(), 
					Double.toString(ten_to_x.XtoN(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]))));
			matcher_exp = pattern_exp.matcher(user_command);
		}
		
		Matcher matcher_mul = pattern_mul.matcher(user_command);
		while(matcher_mul.find()) {
			temp = matcher_mul.group().split("\\*");
			user_command=user_command.replace(matcher_mul.group(),
					Double.toString(Double.parseDouble(temp[0])*Double.parseDouble(temp[1])));
			matcher_mul = pattern_mul.matcher(user_command);
		}
		Matcher matcher_div = pattern_div.matcher(user_command);
		while(matcher_div.find()) {
			temp = matcher_div.group().split("\\/");
			user_command=user_command.replace(matcher_div.group(),
					Double.toString(Double.parseDouble(temp[0])/Double.parseDouble(temp[1])));
			matcher_div = pattern_div.matcher(user_command);
		}
		Matcher matcher_add = pattern_add.matcher(user_command);
		while(matcher_add.find()) {
			temp = matcher_add.group().split("\\+");
			user_command=user_command.replace(matcher_add.group(),
					Double.toString(Double.parseDouble(temp[0])+Double.parseDouble(temp[1])));
			matcher_add = pattern_add.matcher(user_command);
		}
		
		Matcher matcher_sub = pattern_sub.matcher(user_command);
		while(matcher_sub.find()) {
			temp = matcher_sub.group().split("\\-");
			user_command=user_command.replace(matcher_sub.group(),
					Double.toString(Double.parseDouble(temp[0])-Double.parseDouble(temp[1])));
			matcher_sub = pattern_sub.matcher(user_command);
		}
		//System.out.println(user_command);
		return Double.parseDouble(user_command);
	}

}
