package project.testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.BuiltInFunctionImplementation;
import project.CalculatorFunctions;

class XtoNTest {

	@Test
	//less cases, will overflow if number is too high
	void XtoNPosInt() {
		System.out.println(CalculatorFunctions.EXP(1, 1));
		for (int i = 0; i < 20; i++) {
			assertEquals(Math.pow(10, i), CalculatorFunctions.XtoN(10, i), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	@Test
	void XtoNNegInt() {
		for(int i = 1; i < 20; i++) {
			assertEquals(Math.pow(10, -i), CalculatorFunctions.XtoN(10, -i), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	//issue with accuracy when doing positive decimals
	@Test
	void XtoNPosDecimals() {
		double i = 0.1;
		while (i < 100.0) {
			assertEquals(Math.pow(10, i), CalculatorFunctions.XtoN(10, i), 0.00001,
					"Fail at x = " + i + ".");
			i++;
		}
	}
	
	@Test
	void XtoNNegDecimals() {
		double i = -0.1;
		while (i < -100.0) {
			assertEquals(Math.pow(10, -i), CalculatorFunctions.XtoN(10, -i), 0.00001,
					"Fail at x = " + i + ".");
			i--;
		}
	}
}
