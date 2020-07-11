package project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorInterface {

	public static void main(String[] args) {
		int mainMenuInput = -1;
		Scanner sc = new Scanner(System.in);
		while (mainMenuInput != 0) {
			System.out.print("Please choose one of the following options "
					+ "(0 to exit): " 
					+ "\n\t1- sin(x)"
					+ "\n\t2- 10^x" 
					+ "\n\t3- ln(x)" 
					+ "\n\t4- e^x" 
					+ "\n\t5- MAD"
					+ "\n\t6- sinh(x)" 
					+ "\n\t7- x^y"
					+ "\n\t8- math arithmetic\n>>>");
			mainMenuInput = sc.nextInt();

			switch (mainMenuInput) {
			case 1:
				boolean keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					double x = sc.nextDouble();
					// replace with calculator function
					System.out.println(Sin.sinforR(x));

					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;
			case 2:
				keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					double x = sc.nextDouble();
					// replace with calculator function
					System.out.println(CalculatorFunctions.XtoN(10, x));

					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;
			case 3:
				keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					double x = sc.nextDouble();
					System.out.println(Ln.ln(x));
					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;
			case 4:
				keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					double x = sc.nextDouble();
					// replace with calculator function
					System.out.println(Exp.EXP(x, 1));
					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;
			case 5:
				keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter the values, use \",\" to separate: ");
					String x = sc.next();
					// replace with calculator function
					System.out.println(Mean_absolute_deviation.MAD(x));

					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;
			case 6:
				keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					double x = sc.nextDouble();
					// replace with calculator function
					System.out.println(Sinh.sinh(x, false));

					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;

			case 7:
				keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					int x = sc.nextInt();
					System.out.print("Enter value of y: ");
					int y = sc.nextInt();
					// replace with calculator function
					System.out.println(CalculatorFunctions.xPowY(x, y));

					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;

			case 8:
				keepGoing=true;
				String userinput;
				while (keepGoing) { // user interface
					System.out.print("Write your math equation\n>>>");
					userinput = sc.next();
					if (userinput.contains("quit")) {
						keepGoing = false;
					} else
						command_parser(userinput);
					
					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;
			}
		}

		System.out.println("Sucessfully exited Eternity Calculator");
		sc.close();
	}

	/**
	 * method command_parser will filter user's command, then first use
	 * process_paren to process the items in parentheses finally get the result by
	 * using parse_command function
	 * 
	 * @param command
	 *            string
	 */
	public static void command_parser(String command) {
		// remove all spaces in the user's command
		String filtered = command.replace(" ", "");

		// remove unwanted parentheses to reduce errors
		if (filtered.charAt(0) == '(' && filtered.charAt(filtered.length() - 1) == ')')
			filtered = filtered.substring(1, filtered.length() - 1);

		// process parentheses
		String processed = process_paren(filtered);
		if (processed.contains("Invalid"))
			System.out.println("Invalid command!"); // if process parentheses is unsuccessful
		else
			System.out.println(parse_command(processed)); // otherwise compute the rest of command
	}

	/**
	 * method process_paren will process parentheses. This method use parens_check
	 * to check user's commands' parentheses structure First it will retrieve items
	 * in the parentheses and put in split_result array list. Next depends on the
	 * structure of user's command we will process it differently It will use
	 * parse_command function to compute items in the parentheses
	 * 
	 * @param command
	 *            string return the value string after processed
	 */
	public static String process_paren(String str) {
		// parens_check, check if parentheses are in good structures
		if (!parens_check(str))
			return "Invalid input! Please check your parentheses";
		else {
			ArrayList<String> split_result = new ArrayList<String>();
			for (String a : str.split("\\(")) {
				for (String b : a.split("\\)"))
					split_result.add(b);
			}
			
			for(String c:split_result)
				System.out.println(c);
			
			if (split_result.size() == 2) {
				if (split_result.get(0).length() > 0)
					return split_result.get(0) + parse_command(split_result.get(1));
				else
					return split_result.get(1);
			} else {
				if (split_result.size() % 2 == 0) {
					split_result.set(split_result.size() / 2 + 1, split_result.get(split_result.size() / 2)
							+ Double.toString(parse_command(split_result.get(split_result.size() / 2 + 1))));
					split_result.remove(split_result.size() / 2);
					return process_paren(split_result);
				} else
					return process_paren(split_result);
			}

		}
	}

	/**
	 * method parens_check check if the parentheses follow the correct structure
	 * 
	 * @param text
	 *            string
	 * @return boolean true if the parenthese structure is correct
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
	 * method process_paren will process the array list that has user's commands
	 * break down into list. The middle item is the most inner parenthese's item.
	 * Therefore we first convert that parenthese item into a single double number.
	 * Then, we proceed to the left side of the equation or right side depends on
	 * the position of parenthese.
	 * 
	 * @param command_list
	 *            ArrayList contains arithmetic statment breaks into pieces
	 * @return middle String return the double value in string
	 */
	public static String process_paren(ArrayList<String> command_list) {
		/*
		 * Example of an equation converted to command_list
		 * 
		 * a+(b+c)+d has the following list structure [a+,b+c,+d]. Therefore we start
		 * with middle, if length of both left and right side is not 0 we compute both
		 * side.
		 * 
		 * (b+c)+d will have ["",b+c,+d] where the first item being empty
		 * 
		 * a+((b+c)+d) will have [a+,"",b+c,+d,""]
		 */
		String middle = command_list.get(command_list.size() / 2);
		middle = Double.toString(parse_command(middle));
		for (int i = 1, j = command_list.size() / 2; (j + i) < command_list.size(); i++) {
			if (command_list.get(j - i).length() == 0)
				middle = Double.toString(parse_command(middle + command_list.get(j + i)));
			else if (command_list.get(j + i).length() == 0)
				middle = Double.toString(parse_command(command_list.get(j - i) + middle));
			else
				middle = Double.toString(parse_command(command_list.get(j - i) + middle + command_list.get(j + i)));
		}
		return middle;
	}

	/**
	 * method parse_command using Pattern and Mathcer class to find specific
	 * substring by using regular expression. The pattern can be split into cases
	 * where we have exponents,multiplication,division,addition and substraction.
	 * Notice that parenthese are already removed in the previous step process_paren
	 * function.
	 * 
	 * @param user_command
	 *            string contains arithmetic commands
	 * @return user_command to a double value
	 */
	public static double parse_command(String user_command) {

		/*
		 * The order of arithmetic Parentheses Exponents Multiplication and Division
		 * Addition and Subtraction
		 */

		String[] temp;
		Pattern pattern_neg = Pattern.compile("(^\\-)\\d+(\\.\\d+)?");
		Pattern pattern_mul_exp = Pattern.compile("(\\*\\*(\\-)?\\d+(\\.\\d+)?(E\\d+)?\\*\\*(\\-)?\\d+(\\.\\d+)?(E\\\\d+)?)");
		Pattern pattern_exp = Pattern.compile("((\\-)?\\d+(\\.\\d+)?(E\\d+)?\\*\\*(\\-)?\\d+(\\.\\d+)?(E\\d+)?)");
		Pattern pattern_mul = Pattern.compile("((\\-)?\\d+(\\.\\d+)?(E\\d+)?\\*(\\-)?\\d+(\\.\\d+)?(E\\d+)?)");
		Pattern pattern_div = Pattern.compile("((\\-)?\\d+(\\.\\d+)?(E\\d+)?\\/(\\-)?\\d+(\\.\\d+)?(E\\d+)?)");
		Pattern pattern_add = Pattern.compile("((\\-)?\\d+(\\.\\d+)?(E\\d+)?\\+(\\-)?\\d+(\\.\\d+)?(E\\d+)?)");
		Pattern pattern_sub = Pattern.compile("((\\-)?\\d+(\\.\\d+)?(E\\d+)?\\-(\\-)?\\d+(\\.\\d+)?(E\\d+)?)");

		// The following part deals with a specific case where the input is
		// a**b**c -> a^(b^(c))
		// This needs special treatment
		Matcher matcher_mul_exp = pattern_mul_exp.matcher(user_command);
		while (matcher_mul_exp.find()) {
			temp = matcher_mul_exp.group().split("\\*\\*");
			user_command = user_command.replace(matcher_mul_exp.group(),
					"**" + Double.toString(CalculatorFunctions.XtoN(Double.parseDouble(temp[1]), Double.parseDouble(temp[2]))));
			// After replacing the first match to a double value, we have to work on the
			// remaining
			// This remind true for every condition
			matcher_mul_exp = pattern_mul_exp.matcher(user_command);
		}

		Matcher matcher_exp = pattern_exp.matcher(user_command);
		while (matcher_exp.find()) {
			temp = matcher_exp.group().split("\\*\\*");
			user_command = user_command.replace(matcher_exp.group(),
					Double.toString(CalculatorFunctions.XtoN(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]))));
			matcher_exp = pattern_exp.matcher(user_command);
		}

		Matcher matcher_mul = pattern_mul.matcher(user_command);
		Matcher matcher_div = pattern_div.matcher(user_command);
		/*
		 * Mul and Div have the same priorities
		 * So we need to verify both's operation's position to determine which execute first
		 * */
		while (matcher_mul.find()) {
			if(matcher_div.find()) {
				int mul_index = user_command.indexOf(matcher_mul.group());
				int div_index = user_command.indexOf(matcher_div.group());
				if(mul_index < div_index){
					temp = matcher_mul.group().split("\\*");
					user_command = user_command.replace(matcher_mul.group(),
							Double.toString(Double.parseDouble(temp[0]) * Double.parseDouble(temp[1])));
					matcher_mul = pattern_mul.matcher(user_command);
				}
				else {
					temp = matcher_div.group().split("\\/");
					user_command = user_command.replace(matcher_div.group(),
							Double.toString(Double.parseDouble(temp[0]) / Double.parseDouble(temp[1])));
					matcher_div = pattern_div.matcher(user_command);
					
					temp = matcher_mul.group().split("\\*");
					user_command = user_command.replace(matcher_mul.group(),
							Double.toString(Double.parseDouble(temp[0]) * Double.parseDouble(temp[1])));
					matcher_mul = pattern_mul.matcher(user_command);
				}
				
			}
			else {
				temp = matcher_mul.group().split("\\*");
				user_command = user_command.replace(matcher_mul.group(),
						Double.toString(Double.parseDouble(temp[0]) * Double.parseDouble(temp[1])));
				matcher_mul = pattern_mul.matcher(user_command);
			}
		}
		
		while (matcher_div.find()) {
			temp = matcher_div.group().split("\\/");
			user_command = user_command.replace(matcher_div.group(),
					Double.toString(Double.parseDouble(temp[0]) / Double.parseDouble(temp[1])));
			matcher_div = pattern_div.matcher(user_command);
		}
		
		
		Matcher matcher_add = pattern_add.matcher(user_command);
		Matcher matcher_sub = pattern_sub.matcher(user_command);
		while (matcher_add.find()) {
			if(matcher_sub.find()) {
				int add_index = user_command.indexOf(matcher_add.group());
				int sub_index = user_command.indexOf(matcher_sub.group());
				
				if(add_index<sub_index) {
					temp = matcher_add.group().split("\\+");
					user_command = user_command.replace(matcher_add.group(),
							Double.toString(Double.parseDouble(temp[0]) + Double.parseDouble(temp[1])));
					matcher_add = pattern_add.matcher(user_command);
				}
				else {
					temp = matcher_sub.group().split("\\-");
					int first=-1,second=-1;
					for(int i =0;i<temp.length;i++)
						if(temp[i].length()==0) {
							temp[i+1]="-"+temp[i+1];
							if(first==-1)
								first=i+1;
							else
								second=i+1;
						}
					if(first==-1)
						user_command = user_command.replace(matcher_sub.group(),
								Double.toString(Double.parseDouble(temp[0]) - Double.parseDouble(temp[1])));
					else if(second==-1)
						if(first==1)
							user_command = user_command.replace(matcher_sub.group(),
									Double.toString(Double.parseDouble(temp[first]) - Double.parseDouble(temp[first+1])));
						else
							user_command = user_command.replace(matcher_sub.group(),
									Double.toString(Double.parseDouble(temp[0]) - Double.parseDouble(temp[first])));
					else
						user_command = user_command.replace(matcher_sub.group(),
								Double.toString(Double.parseDouble(temp[first]) - Double.parseDouble(temp[second])));
					matcher_sub = pattern_sub.matcher(user_command);
					
					temp = matcher_add.group().split("\\+");
					user_command = user_command.replace(matcher_add.group(),
							Double.toString(Double.parseDouble(temp[0]) + Double.parseDouble(temp[1])));
					matcher_add = pattern_add.matcher(user_command);
				}
			}
			else {
				temp = matcher_add.group().split("\\+");
				user_command = user_command.replace(matcher_add.group(),
						Double.toString(Double.parseDouble(temp[0]) + Double.parseDouble(temp[1])));
				matcher_add = pattern_add.matcher(user_command);
			}
			
		}
		
		while (matcher_sub.find()) {
			//System.out.println(matcher_sub.group());
			temp = matcher_sub.group().split("\\-");
			int first=-1,second=-1;
			for(int i =0;i<temp.length;i++)
				if(temp[i].length()==0) {
					temp[i+1]="-"+temp[i+1];
					if(first==-1)
						first=i+1;
					else
						second=i+1;
				}
			if(first==-1)
				user_command = user_command.replace(matcher_sub.group(),
						Double.toString(Double.parseDouble(temp[0]) - Double.parseDouble(temp[1])));
			else if(second==-1)
				if(first==1)
					user_command = user_command.replace(matcher_sub.group(),
							Double.toString(Double.parseDouble(temp[first]) - Double.parseDouble(temp[first+1])));
				else
					user_command = user_command.replace(matcher_sub.group(),
							Double.toString(Double.parseDouble(temp[0]) - Double.parseDouble(temp[first])));
			else
				user_command = user_command.replace(matcher_sub.group(),
						Double.toString(Double.parseDouble(temp[first]) - Double.parseDouble(temp[second])));
			matcher_sub = pattern_sub.matcher(user_command);
		}
		return Double.parseDouble(user_command);
	}

}
