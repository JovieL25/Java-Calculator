import java.util.Scanner;
import java.lang.Math;

public class CalculatorInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(CalculatorFunctions.ln(0.1));
		int mainMenuInput = -1;
		Scanner sc = new Scanner(System.in);
		while (mainMenuInput != 0) {
			System.out.println(
					"Please choose one of the following options (0 to exit): "
					+ "\n\t1- sin function"
					+ "\n\t2- 10^x"
					+ "\n\t3- ln function"
					+ "\n\t4- e^x"
					+ "\n\t5- MAD/STD"
					+ "\n\t6- x^y"
					+ "\n\t7- sinh function"
					);
			mainMenuInput = sc.nextInt();

			switch (mainMenuInput) {
			case 1:
				boolean keepGoing = true;
				while (keepGoing) {
					System.out.print("Enter value of x: ");
					double x = sc.nextDouble();
					// replace with calculator function
					System.out.println(Math.sin(x));

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
					int x = sc.nextInt();
					// replace with calculator function
					System.out.println(Math.pow(10, x));

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
					System.out.println(CalculatorFunctions.ln(x));

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
					int x = sc.nextInt();
					// replace with calculator function
					System.out.println(Math.pow(Math.E, x));

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
					System.out.print("Enter value of x: ");
					int x = sc.nextInt();
					// replace with calculator function
					System.out.println("Put MAD or STD function here");

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
					int x = sc.nextInt();
					System.out.print("Enter value of y: ");
					int y = sc.nextInt();
					// replace with calculator function
					System.out.println(Math.pow(x, y));

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
}
