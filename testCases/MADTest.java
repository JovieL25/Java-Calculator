package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Mean_absolute_deviation;

import java.util.ArrayList;
import java.util.Random;

class MADTest {

	static double Mean(ArrayList<Double> arr, int n) {
		// Calculate sum of all elements.
		double sum = 0;

		for (int i = 0; i < n; i++)
			sum = sum + arr.get(i);

		return sum / n;
	}

	static double meanAbsDevtion(ArrayList<Double> arr, int n) {
// Calculate the sum of absolute 
// deviation about mean. 
		double absSum = 0;

		for (int i = 0; i < n; i++)
			absSum = absSum + Math.abs(arr.get(i) - Mean(arr, n));

// Return mean absolute  
// deviation about mean. 
		return absSum / n;
	}

	@Test
	void test() {
		Random r = new Random();
		int arrSize = r.nextInt(19) + 1;
		String s = "";
		for (int i = 0; i < arrSize; i++) {
			s = s + Integer.toString(r.nextInt(1000)) + ",";
		}
		s = s.substring(0, s.length() - 1);

		ArrayList<Double> list = new ArrayList<>();
		String[] str = s.split(",");
		for (int i = 0; i < str.length; i++) {
			double temp = Double.valueOf(str[i]);
			list.add(temp);
		}
		assertEquals(meanAbsDevtion(list, list.size()), Mean_absolute_deviation.MAD(s), 0.00001);
	}

}
