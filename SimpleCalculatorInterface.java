package project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class SimpleCalculatorInterface {
	public static void main(String[] args) {
		myFrame frame = new myFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the window
		frame.setVisible(true);
	}
}
 
class myFrame extends JFrame {
	public myFrame() {
		setTitle("计算器");
		add(new myPanel());
		pack();
	}
}
 
class myPanel extends JPanel {
	JTextField display; //display result
	JPanel panel1;//panel1
	String nowButton;//current button being pressed
	
	public myPanel() {
		
		setLayout(new BorderLayout());
		
		//add display text area
		display = new JTextField("");
		display.setEnabled(true);
		add(display, BorderLayout.NORTH);
		
		//监听操作按钮
		ActionListener command = new commandAction();
		
		//add button to panel1
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(6, 4));
		
		addButton1("sin(x)", command);
		addButton1("ln(x)", command);
		addButton1("e^x", command);
		addButton1("MAD", command);
		addButton1("10^x", command);
		addButton1("sinh(x)", command);
		addButton1("x^y", command);
		addButton1("/", command);

		addButton1("7", command);
		addButton1("8", command);
		addButton1("9", command);
		addButton1("+", command);

		addButton1("4", command);
		addButton1("5", command);
		addButton1("6", command);
		addButton1("-", command);

		addButton1("1", command);
		addButton1("2", command);
		addButton1("3", command);
		addButton1("*", command);

		addButton1(".", command);
		addButton1("0", command);
		addButton1("C", command);
		addButton1("=", command);


	
		add(panel1, BorderLayout.CENTER);
		
	}
	

	public void addButton1(String label ,ActionListener listener) {
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel1.add(button);
	}
		
	
	//event listener
	class commandAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			nowButton = event.getActionCommand();
			
			if (nowButton != "=") {
				display.setText(display.getText() + nowButton);
			}
			
			if (nowButton.equals("=")) {
				//return the result
				display.setText(calculator(display.getText()));
			}
			
			if (nowButton.equals("C")) {
				//clear
				display.setText("");
			}
		}
	}
	
	//用来计算的方法
	public String calculator(String string) {
		StringBuffer sb = new StringBuffer(string);
		int commandCount = 0;
		
		int j = 0;//counter
		//there will be n+1 number if we have n command
		for (j = 0; j < sb.length(); j++) {
			if (sb.charAt(j) <= '9' && sb.charAt(j) >= '0' || sb.charAt(j) == '.') {
				continue;
			}else {
				commandCount++;
			}
		}
		
		//initialize command array
		char[] command = new char[commandCount];
		//initialize num array(string)
		String[] num = new String[commandCount+1];
		for (j = 0; j < num.length; j++) {
			num[j] = "";
		}
 
		//put every number into num array, put every symbol into command array
		int k = 0;
		for (j = 0; j < sb.length(); j++) {
			if (sb.charAt(j) <= '9' && sb.charAt(j) >= '0' || sb.charAt(j) == '.') {
				num[k] += sb.charAt(j);
				continue;
			}else {
				command[k] = sb.charAt(j);
				k++;
			}
		}
		
		//计算结果
		double result = 0;
		for (int i = 0; i < commandCount; i++) {
			
			//取前两个数，和第一个操作符，运算
			double num1 = Double.parseDouble(num[i]);
			double num2 = Double.parseDouble(num[i+1]);
			char cc = command[i];
			
			//计算
			switch (cc) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				result = num1 / num2;
				break;
			default:
				break;
			}
			num[i+1] = String.valueOf(result);
		}
		
		return String.valueOf(result);
	}
}
