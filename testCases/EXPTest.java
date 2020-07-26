package project.testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.CalculatorFunctions;

class EXPTest {

	@Test
	//less cases, will overflow if number is too high
	//issue with static variable existing outside the scope of the function
	void XtoNPosInt() {
		System.out.println(CalculatorFunctions.EXP(1, 1));
		for (int i = 1; i < 20; i++) {
			assertEquals(Math.pow(Math.E, i), CalculatorFunctions.EXP(i, 1), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	@Test
	void XtoNNegInt() {
		for(int i = 1; i < 20; i++) {
			assertEquals(Math.pow(Math.E, -i), CalculatorFunctions.EXP(-i, 1), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	

	@Test
	void XtoNPosDecimals() {
		double i = 0.1;
		while (i < 100.0) {
			assertEquals(Math.pow(Math.E, i), CalculatorFunctions.EXP(i, 1), 0.00001,
					"Fail at x = " + i + ".");
			i++;
		}
	}
	
	@Test
	void XtoNNegDecimals() {
		double i = -0.1;
		while (i < -100.0) {
			assertEquals(Math.pow(Math.E, -i), CalculatorFunctions.EXP(-i, 1), 0.00001,
					"Fail at x = " + i + ".");
			i--;
		}
	}

}
