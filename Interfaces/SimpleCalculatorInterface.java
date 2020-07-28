package Interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.EventHandler;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.plaf.ToolTipUI;

import project.Shunting_yard_algorithm;
import project.memory_node;

import javax.swing.JOptionPane;

/*
 * SimpleCalculatorInterface
 * @version clean_code branch
 * @author Yilu Liang, Jingyi Lin
 * This class implements the graphical interface of the eternity
 */
public class SimpleCalculatorInterface {

	public static boolean rad=true;
	
	public static void main(String[] args) {
		myFrame frame = new myFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the window
		frame.setVisible(true);
	}
}

class myFrame extends JFrame {
	public myFrame() {
		setTitle("Calculator");
		add(new myPanel(this));
		pack();
	}
}

//myFrame object add myPanel.
class myPanel extends JPanel {
	//JTextArea display;
	JTextField display,display2,display3; //display result
	JPanel panel1,panel2,panel3;//panel1
	String nowButton;//current button being pressed
	
	boolean equal = false,TF = false;
	ArrayList<memory_node> history = new ArrayList<memory_node>(10);
	ArrayList<JButton> Buttons = new ArrayList<JButton>();
	String[] TFs = {"sin(x)","10^x","ln(x)","e^x","MAD","sinh(x)","x^y"};

	public myPanel(myFrame parent) {
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout(3,1));
		
		
		for(int i = 0;i<10;i++) {
			history.add(new memory_node());
		}
		setLayout(new BorderLayout());

		//add display text area
		display = new JTextField("");
		display.setPreferredSize(new Dimension(40,60));
		display.setEnabled(true);

		display2 = new JTextField("Radius mode");
		display2.setEnabled(false);
		display2.setPreferredSize(new Dimension(10,20));

		display3 = new JTextField("Result");
		display3.setEnabled(false);
		display3.setPreferredSize(new Dimension(40,60));

		ActionListener command = new commandAction();
		JButton button = new JButton("Help");
		button.addActionListener(command);
		panel3.add(button,BorderLayout.LINE_START);
		
		//panel1 have the textfield for userinput ouput.
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(3,1));
		
		//panel2 have all the buttons
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(7, 4));

		panel1.add(display2, BorderLayout.PAGE_START);
		panel1.add(display,BorderLayout.CENTER);
		panel1.add(display3,BorderLayout.SOUTH);

		display.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					getresult();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		add(panel3,BorderLayout.NORTH);
		add(panel1,BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
		addbuttons(command);

	}

	public void addButton1(String label ,ActionListener listener) {

		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel2.add(button,BorderLayout.LINE_START);
		Buttons.add(button);
		for(String c : TFs){
			if(label.equals(c)){
				button.setVisible(false);
				break;
			}
		}

	}

	//event listener
	class commandAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			nowButton = event.getActionCommand();
			if(nowButton.equals("RAD")){
				SimpleCalculatorInterface.rad=!SimpleCalculatorInterface.rad;
				if(SimpleCalculatorInterface.rad)
					display2.setText("Radius mode");
				else
					display2.setText("Degree mode");
			}
			else if(nowButton.equals("Result")){
				String message = "";
				for(int i = 0;i<memory_node.current_head;i++) {
					message += "ANS"+(i+1)+"\nExpression: "
							+history.get(i).getExpression()+"\nResult: "+history.get(i).getResult()+"\n";
				}
				JOptionPane.showMessageDialog(null, message);
			}
			else if(nowButton.equals("TF mode")){
				if(!TF){
					for(JButton b:Buttons)
						b.setVisible(true);
					TF=true;
				}
				else{
					for(JButton b:Buttons){
						for(String c:TFs){
							if(b.getText().equals(c)){
								b.setVisible(false);
								break;
							}
						}
					}
					TF=false;
				}

			}
			else if (nowButton != "=") {
				if(equal){
					display3.setText("");
					display.setText("");
					equal=false;
				}
				display.setText(display.getText() + nowButton);
				//display3.setText(display3.getText() + nowButton);
			}

			if (nowButton.equals("=")) {
				//return the result
				getresult();
			}

			if (nowButton.equals("C")) {
				//clear
				display3.setText("");
				display.setText("");
			}
		}
	}

	public void addbuttons(ActionListener command){
		addButton1("sin(x)", command);
		addButton1("10^x", command);
		addButton1("ln(x)", command);
		addButton1("e^x", command);
		addButton1("mad", command);
		addButton1("sinh(x)", command);
		addButton1("x^y", command);
		addButton1("TF mode",command);

		addButton1("7", command);
		addButton1("8", command);
		addButton1("9", command);
		addButton1("+",command);


		addButton1("4", command);
		addButton1("5", command);
		addButton1("6", command);
		addButton1("-", command);


		addButton1("1", command);
		addButton1("2", command);
		addButton1("3", command);
		addButton1("*", command);


		addButton1("C", command);
		addButton1("0", command);
		addButton1(".", command);
		addButton1("/", command);



		addButton1("Result",command);
		addButton1("RAD",command);
		addButton1("=", command);

	}

	public void getresult(){
		String userinput = display.getText();
		String filtered = userinput;
		double result;
		try {
			if(userinput.contains("ANS")) {
				for(int i = 10;i>0;i--) {
					filtered=userinput.replace("ANS"+i, Double.toString(history.get(i-1).getResult()));
				}
			}
			filtered=filtered.replace("e^", "EXP");
			result = Shunting_yard_algorithm.shunting_yard_algorithm(filtered);
			if(memory_node.current_head<10) {
				history.get(memory_node.current_head).setExpression(userinput);
				history.get(memory_node.current_head).setResult(result);
				memory_node.current_head++;
			}
			else {
				history.remove(0);
				history.add(new memory_node(userinput,result));
			}

			display3.setText(Double.toString(history.get(memory_node.current_head-1).getResult()));
		}
		catch(Exception e){
			display3.setText(e.getMessage());
		}
		equal = true;
	}
}




