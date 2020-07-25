
package project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/*
 * This class implements the interface functionality of the "eternity"
 * @version clean_code branch July 2020
 * @author Derek Liu, Jingyi Lin
 */
public class CalculatorInterface {
	/** 
	 * "history" is an array list which keeps track 
	 * of user's math expression input and their results
	 */
	public static ArrayList<memory_node> history = new ArrayList<memory_node>();
	
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

			switch (mainMenuInput) {
			case 1:
				boolean keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					double x = sc.nextDouble();
					if(rad)
					// replace with calculator function
						System.out.println(Sin.sinforR(x));
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
					System.out.println(CalculatorFunctions.xPowY(x, y));

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
				while (keepGoing) { // user interface
					System.out.print("Write your math equation\n>>>");
					userinput = sc.next();
					double result;
					if (userinput.contains("quit")) {
						keepGoing = false;
					} else {
						try {
							result = Shunting_yard_algorithm.shunting_yard_algorithm(userinput);
							history.add(new memory_node(userinput,result));
							System.out.println(history.get(history.size()-1).getResult());
						}
						catch(Exception e){
							System.out.println(e.getMessage());
						}
					}
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

	public static boolean isnumber(String a) {
		return a.matches("-?\\d+(\\.\\d+)?");
	}
	
}