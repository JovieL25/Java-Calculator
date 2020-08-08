package project.testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.BuiltInFunctionImplementation;
import project.CalculatorFunctions;

class sinforRXTest {
	@Test
	void sinPI() {
		assertEquals(Math.sin(Math.PI), CalculatorFunctions.sinforR(BuiltInFunctionImplementation.getPi()), 0.00001,
				"Fail at x = " + Math.PI + ".");
	}
	
	@Test
	void sinPosInt() {
		for(int i = 1; i < 100; i++) {
			assertEquals(Math.sin(i), CalculatorFunctions.sinforR(i), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	@Test
	void sinNegInt() {
		for(int i = 1; i < 100; i++) {
			assertEquals(Math.sin(-i), CalculatorFunctions.sinforR(-i), 0.00001,
					"Fail at x = " + i + ".");
		}
	}
	
	@Test
	void sinPosDecimals() {
		double i = 0.1;
		while (i < 100.0) {
			assertEquals(Math.sin(i), CalculatorFunctions.sinforR(i), 0.00001,
					"Fail at x = " + i + ".");
			i++;
		}
	}
	
	@Test
	void sinNegDecimals() {
		double i = -0.1;
		while (i < -100.0) {
			assertEquals(Math.sin(i), CalculatorFunctions.sinforR(i), 0.00001,
					"Fail at x = " + i + ".");
			i--;
		}
	}

}
