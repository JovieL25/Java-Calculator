/*
 * This class implements the interface functionality of the "eternity"
 * @version clean_code branch July 2020
 * @author Derek Liu, Jingyi Lin
 */

package View;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

import Controller.Shunting_yard_algorithm;
import Model.Exp;
import Model.Ln;
import Model.Mean_absolute_deviation;
import Model.Sin;
import Model.Sinh;
import Model.XtoN;
import Model.memory_node;

public class CalculatorInterface {
	/** 
	 * "history" is an array list which keeps track 
	 * of user's math expression input and their results
	 */
	public static ArrayList<memory_node> history = new ArrayList<memory_node>(10);

	/**
	 * "rad" is a boolean value keep track of the output mode
	 * (Degree vs Radius)
	 */
	public static boolean rad=false;

	public static void main(String[] args) {
		int mainMenuInput = -1;
		Scanner sc = new Scanner(System.in);

		while (mainMenuInput != 0) {
			if(rad)
				System.out.println("Radius mode");
			else
				System.out.println("Degree mode");
			System.out.print("Please choose one of the following options "
					+ "(0 to exit): " 
					+ "\n\t1- sin(x)"
					+ "\n\t2- 10^x" 
					+ "\n\t3- ln(x)" 
					+ "\n\t4- e^x" 
					+ "\n\t5- MAD"
					+ "\n\t6- sinh(x)" 
					+ "\n\t7- x^y"
					+ "\n\t8- Change Output mode(Radius vs Degree)"
					+ "\n\t9- math arithmetic\n>>>");
			mainMenuInput = sc.nextInt();
			sc.nextLine();
			switch (mainMenuInput) {
			case 1:
				boolean keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					double x = sc.nextDouble();
					if(rad)
						try {
							System.out.println(Sin.sinforR(x));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else
						System.out.println(Sin.sin(x));

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
					try {
						System.out.println(XtoN.Xton(10, x));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

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
					try {
						System.out.println(Ln.ln(x));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
					try {
						System.out.println(Exp.EXP(x, 1));
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}

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
					System.out.println(Sinh.sinh(x, !rad));

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
					try {
						System.out.println(XtoN.Xton(x, y));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
				}
				break;

			case 8:
				rad=true;
				break;

			case 9:
				keepGoing=true;
				String userinput;
				for(int i = 0;i<10;i++) {
					history.add(new memory_node());
				}
				while (keepGoing) { // user interface
					System.out.print("Write \"ANS1-10\" for using previous result"
							+ "\nWrite \"Show History\" to see the previous expression\""
							+ "\nWrite your math equation\n>>>");
					userinput = sc.nextLine();
					//System.out.println(userinput);
					double result;
					if (userinput.contains("quit")) {
						keepGoing = false;
					} else {
						//System.out.println(userinput.contains("Show History"));
						if(userinput.contains("Show History")) {
							for(int i = 0;i<memory_node.current_head;i++) {
								System.out.println("At Index"+(i+1)+"\nExpression: "
										+history.get(i).getExpression()+"\nResult: "+history.get(i).getResult()+"\n");
							}
						}
						else {
							try {
								if(userinput.contains("ANS")) {
									for(int i = 10;i>0;i--) {
										userinput=userinput.replace("ANS"+i, 
												Double.toString(history.get(i-1).getResult()));
									}
								}
								
								result = Shunting_yard_algorithm.shunting_yard_algorithm_parse(userinput);
								
								if(memory_node.current_head<10) {
									history.get(memory_node.current_head).setExpression(userinput);
									history.get(memory_node.current_head).setResult(result);
									memory_node.current_head++;
								}
								else {
									history.remove(0);
									history.add(new memory_node(userinput,result));
								}
								System.out.println(history.get(memory_node.current_head-1).getResult());
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}

					}
					System.out.print("\nContinue? (y/n)");
					String userInput = sc.next();
					if (userInput.equals("n")) {
						keepGoing = false;
					}
					sc.nextLine();
				}
				break;
			}
		}

		System.out.println("Sucessfully exited Eternity Calculator");
		sc.close();
	}

	public static boolean isnumber(String a) {
		return a.matches("-?\\d+(\\.\\d+)?");
	}

}