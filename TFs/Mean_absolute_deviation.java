

package TFs;
import java.util.ArrayList;

import project.BuiltInFunctionImplementation;

/*
 * This class implements the transcendental function,MAD function
 * @version clean_code branch July 2020
 * @author Xuan
 */
public class Mean_absolute_deviation {

	/*
	 * Function 5: MAD by Xuan
	 *
	 * the string is first divided into several strings
	 * then an arrayList is used to save the input of the user,
	 * will write my own structure if this lib is not allowed.
	 * the counter is used to get how many
	 * numbers the user typed.
	 * what to do next: add exceptions(input error),
	 * allow user change their input before calculating
	 * save the equation for further check(to be decide)
	 * 
	 * @param str the user input
	 * @return the MAD result
	 */
	public static double MAD(String str) {
		ArrayList<Double> list = new ArrayList<>();
		int counter = 0;
		double total = 0;
		double difInTotal = 0;
		if (str.equals(""))
			return 0;
		double result = 0;
		String[] s = str.split(",");
		for (int i = 0; i < s.length; i++) {
			double temp = Double.valueOf(s[i]);
			list.add(temp);
			total += temp;
			counter++;
		}

		if (counter == 0)
			result = 0;
		else {
			double avg =  total / counter;
			for (int i = 0; i < list.size(); i++) {
				difInTotal += BuiltInFunctionImplementation.abs(list.get(i) - avg);
			}
			result = difInTotal / counter;
		}
		return result;

	}

}
