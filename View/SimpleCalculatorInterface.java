package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.regex.Pattern;

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

import Controller.Shunting_yard_algorithm;
import Model.memory_node;

import javax.swing.JOptionPane;

/*
 * SimpleCalculatorInterface
 * @version clean_code branch
 * @author Jingyi Lin, Yilu Liang
 * This class implements the graphical interface of the eternity
 */
public class SimpleCalculatorInterface {

	public static boolean rad=true;

	public static void main(String[] args) {
		myFrame frame = new myFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the window
		frame.setPreferredSize(new Dimension(1600,1600));
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
	String[] TFs = {"sin(x)","10^x","ln(x)","e^(x)",
			"MAD","sinh(x)","x^y","cos(x)","tan(x)"};

	public myPanel(myFrame parent) {
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(0, 5));

		for(int i = 0;i<10;i++) {
			history.add(new memory_node());
		}
		setLayout(new BorderLayout());

		//add display text area
		display = new JTextField("");
		display.setPreferredSize(new Dimension(200,100));
		display.setEnabled(true);
		Font f;
		f = new Font("input font", Font.PLAIN, 20);
		display.setFont(f);

		display2 = new JTextField("Radius mode");
		display2.setEnabled(false);
		display2.setPreferredSize(new Dimension(5,20));
		
		display3 = new JTextField("Result");
		display3.setEnabled(false);
		display3.setPreferredSize(new Dimension(200,100));
		display3.setFont(f);

		ActionListener command = new commandAction();
		JButton button = new JButton("Help");
		button.addActionListener(command);
		button.setPreferredSize(new Dimension(40,40));
		panel3.add(button,BorderLayout.LINE_START);

		//panel1 have the textfield for userinput ouput.
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(3,1));

		//panel2 have all the buttons
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 4));

		panel1.add(display2, BorderLayout.PAGE_START);
		panel1.add(display,BorderLayout.CENTER);
		panel1.add(display3,BorderLayout.SOUTH);
		panel1.setPreferredSize(new Dimension(450,200));

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
				else{
					char a = e.getKeyChar();
					if(equal){
						if((a=='+' || a=='-' || a=='*' || a=='/' || a=='^')){
							display.setText("ANS"+memory_node.current_head);
							equal=false;
						}
						else if(Character.isDigit(a) || Character.isAlphabetic(a)){
							display.setText("");
							equal=false;
						}
					}
					
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

	public void addButton1(String label ,ActionListener listener,JPanel panel) {

		JButton button = new JButton(label);
		button.setPreferredSize(new Dimension(80,40));
		if(label.equals(""))
			button.setVisible(false);
		button.addActionListener(listener);
		panel.add(button,BorderLayout.LINE_START);
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
			if(nowButton.equals("Rad/Deg")){
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
			else if(nowButton.equals("Help")){
				JOptionPane.showMessageDialog(null, "Help message here");
			}
			else if(nowButton.equals("\u221A"))
				display.setText(display.getText() + "sqrt(x)");
			else if(nowButton.equals("\u03c0"))
				display.setText(display.getText() + "pi");
			else if (nowButton != "=") {
				if(equal){
					if(Pattern.compile("[-+*/()^]").matcher(nowButton).find()){
						display.setText("ANS"+memory_node.current_head);
					}
					else{
						display.setText("");
					}
					display3.setText("");
					equal=false;
				}
				
				if(nowButton.equals("x!"))
					display.setText(display.getText() + "fact(x)");
				else
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
		addButton1("sin(x)", command,panel2);
		addButton1("cos(x)", command,panel2);
		addButton1("tan(x)", command,panel2);
		addButton1("10^x", command,panel2);
		addButton1("ln(x)", command,panel2);
		addButton1("e^(x)", command,panel2);
		addButton1("MAD", command,panel2);
		addButton1("sinh(x)", command,panel2);
		addButton1("x^y", command,panel2);
		
		addButton1("( )", command,panel2);
		addButton1("\u221A",command,panel2);
		addButton1("x!", command,panel2);
		
		addButton1("7", command,panel2);
		addButton1("8", command,panel2);
		addButton1("9", command,panel2);
		addButton1("+",command,panel2);


		addButton1("4", command,panel2);
		addButton1("5", command,panel2);
		addButton1("6", command,panel2);
		addButton1("-", command,panel2);


		addButton1("1", command,panel2);
		addButton1("2", command,panel2);
		addButton1("3", command,panel2);
		addButton1("*", command,panel2);

		addButton1("C", command,panel2);
		addButton1("0", command,panel2);
		addButton1(".", command,panel2);
		addButton1("/", command,panel2);

		addButton1("\u03c0", command,panel2);
		addButton1(",", command,panel2);
		addButton1("=", command,panel2);
		addButton1("Result",command,panel2);

		addButton1("Rad/Deg",command,panel3);
		addButton1("TF mode",command,panel3);
		addButton1("0.0+", command,panel3);
		addButton1("0.0-", command,panel3);
	}

	public void getresult(){
		String userinput = display.getText();
		String filtered = userinput;
		double result;
		try {
			if(userinput.contains("ANS")) {
				for(int i = 10;i>0;i--) {
					filtered=filtered.replace("ANS"+i, Double.toString(history.get(i-1).getResult()));
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




