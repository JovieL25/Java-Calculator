/*
 * SimpleCalculatorInterface
 * @version clean_code branch
 * @author Jingyi Lin, Yilu Liang
 * This class implements the graphical interface of the eternity
 */

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.plaf.ToolTipUI;
import javax.swing.text.BadLocationException;

import Controller.Shunting_yard_algorithm;
import Model.memory_node;

import javax.swing.JOptionPane;

public class SimpleCalculatorInterface {
	public static boolean rad_mode=true;
	public static void main(String[] args) {

		//Settup frame and panel objects
		myFrame frame = new myFrame();
		//Add exit flow
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the window
		frame.setPreferredSize(new Dimension(2000,2000));
		frame.setVisible(true);
	}
}

class Dialog extends JDialog {
	
	private String repeat(String s, int times){
		if (times <= 0) 
			return "";
	    else
	    	return s + repeat(s, times-1);
	}
    private final JScrollPane scrollPane = new JScrollPane();
    //I added the breaks to the label below to be able to scroll down.
    
    //This contains the entire help message
    //Click on "Help" button to see the message
    private final JLabel lblContent = new JLabel(
    		"<html> >>&nbsp;Manual&nbsp;for&nbsp;Eternity&nbsp;Calculator&nbsp;: "
    		+ "<br><br>>&nbsp;Description&nbsp;:&nbsp;&nbsp;Eternity&nbsp;"
    		+ "Calculator&nbsp;is&nbsp;a&nbsp;simple&nbsp;calculator&nbsp;that&nbsp; "
    		+ "<br>"+repeat("&nbsp;",26)+"supports&nbsp;many&nbsp;"
    		+ "mathematical&nbsp;functions&nbsp; "
    		+ "<br>"+repeat("&nbsp;",26)+"and&nbsp;transcendental&nbsp;functions "
    		+ "<br>"+repeat("&nbsp;",26)+"while&nbsp;"
    		+ "maintaining&nbsp;a&nbsp;simple&nbsp;user&nbsp;interface. "
    		+ "<br><br> >>&nbsp;Features&nbsp;:&nbsp; "
    		+ "<br><br>"+repeat("&nbsp;",4)
    		+ "(1)&nbsp;Support&nbsp;for&nbsp;math&nbsp;equation&nbsp;"
    		+ "evaluation&nbsp;:&nbsp;2.2^3.6+sin(5)*(10-6) <br> "
    		+ "<br>"+repeat("&nbsp;",4)+"(2)&nbsp;Memory&nbsp;of&nbsp;10&nbsp;"
    		+ "previous&nbsp;user&nbsp;input&nbsp;equations&nbsp;and&nbsp;answers <br> "
    		+ "<br>"+repeat("&nbsp;",4)+"(3)&nbsp;Change&nbsp;radius/degree&nbsp;output&nbsp;mode "
    		+ "<br><br>"+repeat("&nbsp;",4)
    		+"(4)&nbsp;Hide/Display&nbsp;transcendental&nbsp;functions "
    		+ "<br><br>"+repeat("&nbsp;",4)+"(5)&nbsp;Error&nbsp;messages&nbsp;with&nbsp;feedback "
    		+ "<br><br><br>(1)&nbsp;Supported&nbsp;Functions<br><br>"+repeat("&nbsp;",6)
    		+ "-Basic&nbsp;arithmetic&nbsp;operations&nbsp;:&nbsp;+-*/ <br><br>"+repeat("&nbsp;",6)
    		+ "-Sine&nbsp;:&nbsp;sin(x) <br><br>"+repeat("&nbsp;",6)
    		+ "-Cosine&nbsp;:&nbsp;cos(x) <br><br>"+repeat("&nbsp;",6)
    		+ "-Tangent&nbsp;:&nbsp;tan(x) <br><br>"+repeat("&nbsp;",6)
    		+ "-Exponential&nbsp;function&nbsp;:&nbsp;e^x<br><br>"+repeat("&nbsp;",6)
    		+ "-Linear&nbsp;logarithm&nbsp;:&nbsp;ln(x)<br><br>"+repeat("&nbsp;",6)
    		+ "-Square&nbsp;root&nbsp;:&nbsp;sqrt(x) <br><br>"+repeat("&nbsp;",6)
    		+ "-Power&nbsp;function&nbsp;:&nbsp;n^x <br><br>"+repeat("&nbsp;",6)
    		+ "-Ten&nbsp;to&nbsp;x&nbsp;:&nbsp;10^x <br><br>"+repeat("&nbsp;",6)
    		+ "-Factorial&nbsp;:&nbsp;fact(x)&nbsp;Where&nbsp;x&nbsp;is&nbsp;an&nbsp;Integer "
    		+ "<br><br>"+repeat("&nbsp;",6)
    		+ "-Hyperbolic&nbsp;sine&nbsp;:&nbsp;sinh(x) "
    		+ "<br><br>"+repeat("&nbsp;",6)
    		+ "-Mean&nbsp;Absolute&nbsp;Deviation&nbsp;:&nbsp;"
    		+ "MAD(x1,x2,...,xn) <br><br> <br> "
    		+ "(2)&nbsp;To&nbsp;use&nbsp;previous&nbsp;answer:<br><br>"+repeat("&nbsp;",10)
    		+"Click&nbsp;on&nbsp;"
    		+ "\"ANS\"&nbsp;button&nbsp;on&nbsp;the&nbsp;top<br><br>"+repeat("&nbsp;",10)
    		+"Answer&nbsp;from&nbsp;the&nbsp;"
    		+ "most&nbsp;recently&nbsp;calculated&nbsp;function&nbsp;<br><br>"+repeat("&nbsp;",10)
    		+ "can&nbsp;be&nbsp;directly&nbsp;used&nbsp;"
    		+ "for&nbsp;next&nbsp;function,&nbsp;simply&nbsp;carry <br><br>"+repeat("&nbsp;",10)
    		+ "on&nbsp;the&nbsp;calculation<br><br>"+repeat("&nbsp;",5)+"To&nbsp;use&nbsp;"
    		+ "the&nbsp;other&nbsp;previous&nbsp;answer:<br><br>"+repeat("&nbsp;",10)
    		+ "Step&nbsp;1.&nbsp;Click&nbsp;on&nbsp;\"Hisotry\"&nbsp;button&nbsp;&nbsp;to&nbsp;see&nbsp;"
    		+ "which&nbsp;result&nbsp;to&nbsp;retrive.<br><br>"+repeat("&nbsp;",10)
    		+ "Step&nbsp;2.&nbsp;Combine&nbsp;\"ANS1-10\"&nbsp;with&nbsp;other&nbsp;functions&nbsp;and&nbsp;operations "
    		+ "<br><br><br>(3)&nbsp;Switch&nbsp;output&nbsp;mode&nbsp;by&nbsp;click&nbsp;on&nbsp;\"Rad/Deg\"&nbsp;button <br> "
    		+ "<br><br>(4)&nbsp;Click&nbsp;\"TF&nbsp;mode\"&nbsp;button&nbsp;to&nbsp;enable/disable&nbsp;buttons&nbsp;"
    		+ "for<br><br>"+repeat("&nbsp;",5)+"transcendental&nbsp;functions <br><br> <br> Author&nbsp;:&nbsp; "
    		+ "<br>"+repeat("&nbsp;",12)+"Written&nbsp;by&nbsp;Jingyi&nbsp;Lin.<br></html>");

    public Dialog() { 
        setBounds(100, 100, 500, 500);
        getContentPane().setLayout(new BorderLayout(0, 0));

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        scrollPane.setViewportView(lblContent);

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
/**
 * @author lingzhimo
 *
 */
class myPanel extends JPanel {
	
	JTextField Input_textfield;			//Input_textfield for user's entered equation
	JTextField Mode_textfield;          //output_textfield  
	
	//displaying Radius or Degree mode for Sin,Cos,Tan functions
	JTextField Result_textfield; 		//Result_textfield displaying result of an entered equation

	//panels can wrap elements including buttons, textfield

	//panel_north contains calculator modes buttons and help button
	JPanel panel_north;
	
	//panel_center contains input and output Textfield
	JPanel panel_center;
	
	//panel_south contains input buttons, 1-9, operators and TF functions
	JPanel panel_south;

	//This string variable stores last pressed buttons' label
	String Last_pressed_button;

	//Boolean variable return true if "equal" button was pressed
	boolean equal_button_pressed = false;

	//Boolean variable return true if "TF" button was pressed
	//Pressed for second time make it false
	boolean displaying_TF = false;

	//ArrayList of memory_mode contains the history of user's input
	//For "memory_mode" data structure see source file Model.memory_node.java
	ArrayList<memory_node> history = new ArrayList<memory_node>(10);

	//ArrayList of JButton contains all Buttons of the GUI
	ArrayList<JButton> List_Buttons = new ArrayList<JButton>();

	//Array of Strings contains all transcendental functions
	//Used to hide TF buttons if displaying_TF variable is false
	String[] TF_labels = {"sin(x)","10^x","ln(x)","e^(x)",
			"MAD","sinh(x)","x^y","cos(x)","tan(x)"};

	/**
	 * Event Listener
	 * When a button is pressed, based on certain conditions,
	 * the event listener will generate different view or execute some functions.
	 * This event listener is only linked to "button" object.
	 */
	class commandAction implements ActionListener {
		//When an action is performed(A button is pressed)
		@Override
		public void actionPerformed(ActionEvent event) {
			//String variable store event's label(Button's label)
			Last_pressed_button = event.getActionCommand();

			if (Last_pressed_button.equals("Rad/Deg")){
				//Changing calculator's calculation mode for sin,cos, and other functions
				SimpleCalculatorInterface.rad_mode=!SimpleCalculatorInterface.rad_mode;

				//Change text in GUI
				if (SimpleCalculatorInterface.rad_mode)
					Mode_textfield.setText("Radius mode");
				else
					Mode_textfield.setText("Degree mode");
			}
			else if (Last_pressed_button.equals("ANS")){
				if(equal_button_pressed){
					Input_textfield.setText("ANS"+memory_node.current_head);
					equal_button_pressed=false;
				}
				else
					Input_textfield.setText(Input_textfield.getText()+"ANS"+memory_node.current_head);
			}
			else if (Last_pressed_button.equals("History")){

				//We will pop a message dialog containing the history of user inputs.
				String message = "";
				for (int i = 0;i<memory_node.current_head;i++) {
					message += "ANS"+(i+1)+"\nExpression: "
							+history.get(i).getExpression()
							+"\nResult: "+history.get(i).getResult()+"\n";
				}
				JOptionPane.showMessageDialog(null, message);
			}
			else if (Last_pressed_button.equals("TF mode")){

				//If TF mode button is pressed, 
				//we need to hide/show TF buttons depending on the current boolean

				//If currently not displaying TF buttons
				if (!displaying_TF){
					for (JButton b:List_Buttons)
						b.setVisible(true);
					displaying_TF=true;
				}
				else{

					//else TF buttons
					for (JButton b:List_Buttons){
						for (String c:TF_labels){
							if (b.getText().equals(c)){
								b.setVisible(false);
								break;
							}
						}
					}
					displaying_TF=false;
				}

			}
			else if (Last_pressed_button.equals("Help")){

				//if "Help" button was pressed
				
				/*
				JOptionPane.showMessageDialog(null, 
						">> Manual for Eternity Calculator"
						+"\n"
						+"Press “Rad/Deg” to shift "
						+"between radius mode and degree mode."
						+ "\nPress “TF mode” to enable buttons for transcendental functions."
						+"\nPress \"ANS\" to use the previous stored answer."
						
							+"\nPress “History” to see the equations entered "
							+"and their result," 
							+" currently able to see the last 10 inputs."

							+"\nAnswer from the most recently calculated function "
							+"can be directly used for next function, "
							+"simply carry on the calculation.");
				*/
				Dialog dialog = new Dialog();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
			}
			else if (Last_pressed_button.equals("\u221A"))							/*Special case for square root button*/
				Input_textfield.setText(Input_textfield.getText() + "sqrt(x)");

			else if (Last_pressed_button.equals("\u03c0"))							/*Special case for pi button*/
				Input_textfield.setText(Input_textfield.getText() + "pi");
			else if (Last_pressed_button.equals("DEL")){
				try {
					Input_textfield.setText(Input_textfield.getText(0, Input_textfield.getText().length()-1));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (Last_pressed_button != "=") {

				if (equal_button_pressed){

					//If equal button was pressed
					//And user pressed an operator button
					//concatenate last stored result with that operator
					if (Pattern.compile("[-+*/()^]").matcher(Last_pressed_button).find()){
						Input_textfield.setText("ANS"+memory_node.current_head);
					}
					else{
						//if equal button was pressed
						//and other buttons are pressed
						//Set input textfield to empty
						Input_textfield.setText("");
					}
					Result_textfield.setText("");
					equal_button_pressed=false;
				}

				if (Last_pressed_button.equals("x!"))							/*Special case for factorial button*/			
					Input_textfield.setText(Input_textfield.getText() + "fact(x)");
				else
					Input_textfield.setText(Input_textfield.getText() + Last_pressed_button);
			}

			if (Last_pressed_button.equals("=")) {
				//return the result
				getresult();
			}

			if (Last_pressed_button.equals("C")) {
				//clear
				Result_textfield.setText("");
				Input_textfield.setText("");
			}
		}
	}

	/**
	 * myPanel object constructor.
	 * In this constructor function
	 * we are setting up everything we need in the GUI.
	 * This include 3 panels, some textfield and buttons.
	 */
	public myPanel(myFrame parent) {
		panel_north = new JPanel();
		panel_north.setLayout(new GridLayout(0, 5));

		//Initialize memory object, stores only 10 inputs
		for (int i = 0;i<10;i++) {
			history.add(new memory_node());
		}
		setLayout(new BorderLayout());

		//Initializing Input_textfield with size and initial text
		Input_textfield = new JTextField("");
		Input_textfield.setPreferredSize(new Dimension(200,100));
		Input_textfield.setEnabled(true);

		//Adding fonts object for output
		Font f;
		f = new Font("input font", Font.PLAIN, 20);
		Input_textfield.setFont(f);

		Mode_textfield = new JTextField("Radius mode");
		Mode_textfield.setEnabled(false);
		Mode_textfield.setPreferredSize(new Dimension(5,20));

		Result_textfield = new JTextField("Result");
		Result_textfield.setEnabled(false);
		Result_textfield.setPreferredSize(new Dimension(200,100));
		Result_textfield.setFont(f);

		//
		ActionListener command = new commandAction();
		JButton button = new JButton("Help");
		button.addActionListener(command);
		button.setPreferredSize(new Dimension(40,40));
		panel_north.add(button,BorderLayout.LINE_START);

		//panel_center have the textfield for userinput ouput.
		panel_center = new JPanel();
		panel_center.setLayout(new BorderLayout(3,1));

		//panel_south have all the buttons
		panel_south = new JPanel();
		panel_south.setLayout(new GridLayout(0, 4));

		panel_center.add(Mode_textfield, BorderLayout.PAGE_START);
		panel_center.add(Input_textfield,BorderLayout.CENTER);
		panel_center.add(Result_textfield,BorderLayout.SOUTH);
		panel_center.setPreferredSize(new Dimension(450,200));

		//Input textfield adding keyboard listener
		Input_textfield.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					//Press "Enter" key is similar as pressing "equal" button
					getresult();
				}
				else{
					
					//If other keys are pressed
					char a = e.getKeyChar();
					
					//If "Equal" button or "Enter" key was pressed before
					//And user immediately pressed another operator
					//Use the last stored result concatenate with the operator
					if (equal_button_pressed && e.getKeyCode()!=16){
						if ((a=='+' || a=='-' || a=='*' || a=='/' || a=='^')){
							Input_textfield.setText("ANS"+memory_node.current_head);
						}
						else if (Character.isDigit(a) || Character.isAlphabetic(a)){
							//Otherwise clear the input textfield and fill in new character
							Input_textfield.setText("");
						}
						equal_button_pressed=false;
					}
					
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		//add 3 panels to the frame
		add(panel_north,BorderLayout.NORTH);
		add(panel_center,BorderLayout.CENTER);
		add(panel_south,BorderLayout.SOUTH);

		//Wrapper function for adding buttons in panel_south
		addbuttons(command);

	}

	/**
	 * add_one_Button function add button to specific panel with the listener
	 * @param label
	 * @param listener
	 * @param panel
	 */
	public void add_one_Button(String label ,ActionListener listener,JPanel panel) {

		//Creating a button object
		//Add to desired panel
		//If the button belongs to a TF button, set visibility to false
		JButton button = new JButton(label);
		button.setPreferredSize(new Dimension(80,40));
		if (label.equals(""))
			button.setVisible(false);
		button.addActionListener(listener);
		panel.add(button,BorderLayout.LINE_START);
		List_Buttons.add(button);
		for (String c : TF_labels){
			if (label.equals(c)){
				button.setVisible(false);
				break;
			}
		}


	}

	/**
	 * Sequence of adding each button
	 * @param command is the button ActionListener
	 */
	public void addbuttons(ActionListener command){
		add_one_Button("sin(x)", command,panel_south);
		add_one_Button("cos(x)", command,panel_south);
		add_one_Button("tan(x)", command,panel_south);
		add_one_Button("10^x", command,panel_south);
		add_one_Button("ln(x)", command,panel_south);
		add_one_Button("e^(x)", command,panel_south);
		add_one_Button("MAD", command,panel_south);
		add_one_Button("sinh(x)", command,panel_south);
		add_one_Button("x^y", command,panel_south);

		add_one_Button("x!", command,panel_south);
		add_one_Button("\u03c0", command,panel_south);
		add_one_Button(",", command,panel_south);
		
		add_one_Button("\u221A",command,panel_south);
		add_one_Button("( )", command,panel_south);
		add_one_Button(".", command,panel_south);
		add_one_Button("DEL",command,panel_south);
		
		add_one_Button("7", command,panel_south);
		add_one_Button("8", command,panel_south);
		add_one_Button("9", command,panel_south);
		add_one_Button("+",command,panel_south);


		add_one_Button("4", command,panel_south);
		add_one_Button("5", command,panel_south);
		add_one_Button("6", command,panel_south);
		add_one_Button("-", command,panel_south);


		add_one_Button("1", command,panel_south);
		add_one_Button("2", command,panel_south);
		add_one_Button("3", command,panel_south);
		add_one_Button("*", command,panel_south);

		add_one_Button("C", command,panel_south);
		add_one_Button("0", command,panel_south);
		add_one_Button("=", command,panel_south);
		add_one_Button("/", command,panel_south);
		
		

		add_one_Button("Rad/Deg",command,panel_north);
		add_one_Button("TF mode",command,panel_north);
		add_one_Button("ANS", command,panel_north);
		add_one_Button("History", command,panel_north);
	}

	/**
	 * getresult() function will first filtered the user input by
	 * replacing ANS1-10 to history result(double).
	 * Also, it will replace certain label such as e^ to EXP.
	 * Next, the function parse the input command to 
	 * the Controller.Shunting_yard_algorithm_parse function.
	 * If current history object have 10 nodes, then the function will delete the oldest one,
	 * and push the newest one on the top of stack.
	 * It will also change view (Result_textfield) based on the result returned from controller.
	 */
	public void getresult(){
		String userinput = Input_textfield.getText();
		String filtered = userinput;
		double result;
		try {
			//Surrounded with try catch block to handle exceptions
			//Exceptions can come from all functions executions
			//or incorrect user input
			if (userinput.contains("ANS")) {
				//Replace ANS1-10 to the double value
				for (int i = 10;i>0;i--) {
					filtered=filtered.replace("ANS"+i, 
							Double.toString(history.get(i-1).getResult()));
				}
			}
			filtered=filtered.replace("e^", "EXP");

			//Parse to controller
			result = Shunting_yard_algorithm.shunting_yard_algorithm_parse(filtered);
			if (memory_node.current_head<10) {
				history.get(memory_node.current_head).setExpression(userinput);
				history.get(memory_node.current_head).setResult(result);
				memory_node.current_head++;
			}
			else {
				history.remove(0);
				history.add(new memory_node(userinput,result));
			}
			double last_result = history.get(memory_node.current_head-1).getResult();
			Result_textfield.setText(Double.toString(last_result));
		}
		catch(Exception e){
			//If an exception is caught, put the message in GUI.
			Result_textfield.setText(e.getMessage());
		}

		//Set the boolean value to true as user pressed "Enter" key 
		//or "=" button.
		equal_button_pressed = true;
	}

}




